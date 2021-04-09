package com.zzm.snmp;

import com.zzm.enums.DeviceWarningCategoryEnum;
import com.zzm.enums.DeviceWarningRegexEnum;
import com.zzm.pojo.dto.ErrorMessageToEachBranchDTO;
import com.zzm.resource.SnmpResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.*;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.*;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * @author zhuzhaoman
 * @date 2020/8/25 0025
 * @description 接收Snmp主动推送的异常信息，然后进行一系列逻辑处理
 */
@Component
@SuppressWarnings("all")
public class ErrorMessagePush implements CommandResponder {

    public static final Logger log = LoggerFactory.getLogger(ErrorMessagePush.class);

    private MultiThreadedMessageDispatcher dispatcher;
    private Snmp snmp = null;
    private Address listenAddress;
    private ThreadPool threadPool;

    @Resource
    private SnmpResource snmpResource;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 启动SNMP Client
     */
    @Async
    @PostConstruct
    public void run() {
        try {
            init();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
    }


    /**
     * SNMP初始化
     *
     * @throws UnknownHostException
     * @throws IOException
     */
    private void init() throws IOException {

        // 创建接收SnmpTrap的线程池，参数： 线程名称及线程数
        threadPool = ThreadPool.create("Trap", 2);
        dispatcher = new MultiThreadedMessageDispatcher(threadPool,
                new MessageDispatcherImpl());


        listenAddress = GenericAddress.parse(System.getProperty(
                "snmp4j.listenAddress", snmpResource.getListenAddress())); // 监听指定端口

        // 对TCP与UDP协议进行处理
        final TransportMapping[] transport = {null};
        try {
            if (listenAddress instanceof UdpAddress) {
                transport[0] = new DefaultUdpTransportMapping((UdpAddress) listenAddress);
            } else {
                transport[0] = new DefaultTcpTransportMapping((TcpAddress) listenAddress);
            }
            log.info("--------------------> 连接 snmp trap 成功 <--------------------");
            snmpListen(transport[0]);
        } catch (IOException e) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        transport[0] = new DefaultUdpTransportMapping((UdpAddress) listenAddress);
                    } catch (IOException ex) {
                        log.info("--------------------> 连接 snmp trap 失败，10秒后重试 <--------------------");
                        return;
                    }
                    log.info("--------------------> 连接 snmp trap 成功 <--------------------");
                    timer.cancel();
                    snmpListen(transport[0]);
                }
            }, 2000L, 10000L);
        }

    }

    /**
     * 开启snmp trap监听
     *
     * @param transport
     */
    public void snmpListen(TransportMapping transport) {
        try {
            snmp = new Snmp(dispatcher, transport);
            snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
            snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
            snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());


            USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3
                    .createLocalEngineID()), 0);

            SecurityModels.getInstance().addSecurityModel(usm);
            SecurityProtocols.getInstance().addDefaultProtocols();

            OctetString userName = new OctetString(snmpResource.getUsername());
            OctetString authPass = new OctetString(snmpResource.getAuthPassword());
            OctetString privPass = new OctetString(snmpResource.getPrivPassword());
            UsmUser usmUser = new UsmUser(userName, AuthMD5.ID, authPass, PrivDES.ID, privPass);

            UsmUserEntry userEnty = new UsmUserEntry(userName, usmUser);
            UsmUserTable userTable = snmp.getUSM().getUserTable();
            userTable.addUser(userEnty);

            snmp.listen();
            snmp.addCommandResponder(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 捕获SNMP推送的异常
     *
     * @param respEvnt
     */
    @Override
    public void processPdu(CommandResponderEvent respEvnt) {
        if (respEvnt != null && respEvnt.getPDU() != null) {
            Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getPDU().getVariableBindings();
            businessProcess(recVBs);
        }
    }

    /**
     * 业务逻辑处理，判别此时错误类型
     *
     * @param recVBs
     */
    public synchronized void businessProcess(Vector<VariableBinding> recVBs) {
        System.out.println("==============收到异常消息================");
        for (VariableBinding variableBinding : recVBs) {

            Variable variable = variableBinding.getVariable();
            String warning = variable.toString();
            System.out.println("异常消息内容：" + warning);

            // 过滤无用信息
            if (warning.length() > 20) {
                for (DeviceWarningRegexEnum deviceWarningRegexEnum : DeviceWarningRegexEnum.values()) {

                    if (warning.matches(deviceWarningRegexEnum.getRegex())) {
                        System.out.println("匹配导数据：" + warning);

                        // 错误名称
                        String errorName = deviceWarningRegexEnum.getErrorName();

                        DeviceWarningCategoryEnum deviceWarningCategoryEnum = DeviceWarningCategoryEnum.fromValue(deviceWarningRegexEnum.getCategory());

                        // 错误分类，一个错误分类可能对应多个错误名称
                        int category = deviceWarningCategoryEnum.getCode();
                        String title = deviceWarningCategoryEnum.getMsg();
                        this.sendAndSaveWarning(title, warning, category, errorName);

                        break;
                    }
                }
            }
        }
    }


    /**
     * 推送异常信息和保存异常信息
     *
     * @param title
     * @param content
     * @param category
     */
    public void sendAndSaveWarning(String title, String content, Integer category, String errorName) {

        /**
         * 接收到异常信息，推送给不同的监听，执行不同的业务逻辑
         */
        ErrorMessageToEachBranchDTO eachBranchDTO = new ErrorMessageToEachBranchDTO(title, content, category, errorName);

        ErrorMessagePushEvent errorMessagePushEvent = new ErrorMessagePushEvent(eachBranchDTO);
        applicationContext.publishEvent(errorMessagePushEvent);
    }
}

