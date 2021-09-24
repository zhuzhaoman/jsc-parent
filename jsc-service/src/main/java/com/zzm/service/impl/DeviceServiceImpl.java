package com.zzm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.*;
import com.zzm.exception.GraceException;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.bo.DeviceBO;
import com.zzm.pojo.bo.DeviceThresholdConfigBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.device.SystemManagerSendingDeviceComponent;
import com.zzm.policy.system_manager.sending.device.SystemManagerSendingDevicePolicyService;
import com.zzm.service.DeviceService;
import com.zzm.service.impl.policy.module.upgrade.Upgrade;
import com.zzm.service.impl.policy.module.upgrade.systemManager.SystemManagerCompleteMachineInstall;
import com.zzm.service.impl.policy.module.upgrade.systemManager.SystemManagerCompleteMachineUpdate;
import com.zzm.service.impl.policy.module.upgrade.systemManager.SystemManagerSingleInstall;
import com.zzm.service.impl.policy.module.upgrade.systemManager.SystemManagerSingleUpdate;
import com.zzm.utils.LinuxUtil;
import com.zzm.utils.WebSocketSendMessage;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 15:14
 * @description 设备信息实现
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    public static final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    public static List<String> importParamsTemplate = Arrays.asList("127.0.0.1",
            "admin", "JSC@3pass0k", "enable", "JSC@3pass0k",
            "configure terminal");

    public static List<String> configDefaultTemplate = Arrays.asList("127.0.0.1",
            "admin", "JSC@3pass0k", "enable", "JSC@3pass0k",
            "configure terminal", "device", "config resume-default", "y");

    @Resource
    private ClientServerSync clientServerSync;
    @Value("${jsc.config.python-url}")
    private String pythonUrl;
    @Value("${jsc.config.config-default}")
    private String configDefault;
    @Value("${jsc.config.export-procedure}")
    private String exportProcedure;
    @Value("${jsc.config.import-procedure}")
    private String importProcedure;
    @Value("${jsc.config.save-path}")
    private String savePath;
    @Value("${jsc.config.linux-ip}")
    private String linuxIp;
    @Value("${jsc.config.upgrade-path}")
    private String upgradePath;
    private int tryCount = 14;
    private int fileIndex = 0;
    private StringBuilder cardStatusError = new StringBuilder();

    @Async
    @Override
    public Future<String> importConfigFile(MultipartFile[] files, String user) {

        if (files == null || files.length <= 0) {
            GraceException.display("上传的配置文件不能为空");
        }

        boolean checkFlag = checkImportFileName(files, user);
        if (!checkFlag) {
            GraceException.display("文件名称校验不通过");
        }

        try {
            for (MultipartFile file : files) {
                // 获得文件上传的文件名称
                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {

                    String[] fileNameArr = fileName.split("\\.");
                    String suffix = fileNameArr[fileNameArr.length - 1];

                    if (!"txt".equalsIgnoreCase(suffix)) {
                        GraceException.display("配置文件格式有误");
                    }

                    try {
                        String filePath = savePath + fileName;
                        File savedFile = new File(filePath);
                        file.transferTo(savedFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            GraceException.display("配置文件导入失败");
        }

        loadConfigFile(user, files);

        return new AsyncResult<>("任务完成");
    }

    private void loadConfigFile(String user, MultipartFile[] files) {
        List<String> fileName = new ArrayList<>();

        for (MultipartFile file : files) {
            fileName.add(file.getOriginalFilename());
        }

        List<String> fileList = UserFileConfig.getFileList(user);

        if (user.equals("admin")) {
            if (fileName.size() == 2) {
                importConfigWaitHandle(user);
            } else {
                executeImportConfig(user, fileList.get(2));
            }
        } else {
            executeImportConfig(user, fileList.get(0));
        }
    }

    private void importConfigWaitHandle(String user) {

        if (!"admin".equals(user)) {
            GraceException.display("当前用户不是admin用户");
        }

        boolean isDefault = restoreDefaultConfig();

        if (!isDefault) {
            GraceException.display("恢复默认出厂失败");
        }

        fileIndex = 0;
        tryCount = 14;
        List<String> fileList = new ArrayList<>();
        fileList.addAll(UserFileConfig.getFileList(user));
        fileList.remove(2);

        executeImportConfig(user, fileList.get(fileIndex));
        fileIndex++;

        Timer timer = new Timer();
        try {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    if (tryCount <= 0) {
                        tryCount = 14;
                        checkCardStatus(user, true);
                        sendCardStatus();
                        timer.cancel();
                        return;
                    }

                    boolean cardStatus = checkCardStatus(user, false);
                    if (!cardStatus) {
                        tryCount--;
                        System.out.println("尝试" + tryCount);
                    } else {

                        if (fileIndex >= fileList.size()) {
                            fileIndex = 0;
                            tryCount = 14;
                            timer.cancel();
                            System.out.println("结束");
                        } else {
                            executeImportConfig(user, fileList.get(fileIndex));
                            fileIndex++;
                            tryCount = 14;
                            System.out.println("执行发送");
                        }
                    }
                }
            }, 1000L, 60000L);
        } catch (Exception e) {
            e.printStackTrace();
            timer.cancel();
            WebSocketSendMessage.sendTopicMessage("导入失败");
        }
    }

    private boolean restoreDefaultConfig() {
        configDefaultTemplate.set(0, linuxIp);
        List<String> template = new ArrayList<>(configDefaultTemplate);
        String result = LinuxUtil.executeCommandExplicit(pythonUrl, configDefault, template);

        System.out.println(result);
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(result);
        result = m.replaceAll("");

        if (result.contains("success")) {
            return true;
        } else {
            return false;
        }
    }

    private void executeImportConfig(String user, String fileName) {
        List<String> template = new ArrayList<>(generationTemplate(user, fileName));
        String result = LinuxUtil.executeCommandExplicit(pythonUrl, importProcedure, template);

        WebSocketSendMessage.sendTopicImportMessage(result);
    }

    private void sendCardStatus() {
        String error = cardStatusError.toString();
        cardStatusError.delete(0, cardStatusError.length());
        WebSocketSendMessage.sendTopicImportMessage(error);
    }

    private List<String> generationTemplate(String user, String fileName) {
        List<String> template = new ArrayList<>(importParamsTemplate);
        String password = UserPasswordEnum.getUserPassword(user);

        template.set(0, linuxIp);
        template.set(1, user);
        template.set(2, password);
        template.set(4, password);
        template.add("load file " + fileName);

        return template;
    }

    private boolean checkCardStatus(String user, boolean isRecord) {
        ReceiveSystemManagerDTO info = null;

        boolean status = true;
        try {
            info = this.info(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (info.getData() == null) {
            return false;
        }

        JSONObject result = JSONObject.parseObject(JSONObject.toJSONString(info.getData()));

        JSONArray cardList = JSONArray.parseArray(JSONObject.toJSONString(result.get("m_tCardStatusMsg")));
        if (cardList.size() <= 0) {
            return false;
        }

        for (Object card : cardList) {
            JSONObject cardObj = JSONObject.parseObject(JSONObject.toJSONString(card));
            if (cardObj.getInteger("m_u32RunStatus") != 4 && cardObj.getInteger("m_u32SlotId") <= 8) {
                status = false;
            }

            if (isRecord) {
                cardStatusError.append("slot " + cardObj.get("m_u32SlotId") + " abnormal state!");
                cardStatusError.append("\n");
            }
        }

        return status;
    }


    private boolean checkImportFileName(MultipartFile[] files, String user) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            fileNames.add(file.getOriginalFilename());
        }

        boolean userFlag = LinuxUtil.checkUserFileName(fileNames, user);

        return userFlag;
    }

    @Override
    public Map<String, Object> exportConfigFile(String user) {
        Map<String, Object> result = new HashMap<>();
        String flag = LinuxUtil.exportFile(user, linuxIp, pythonUrl, exportProcedure);
        List<String> fileList = UserFileConfig.getFileList(user);

        result.put("status", flag);
        result.put("fileList", fileList);

        return result;
    }

    @Override
    @SneakyThrows
    public void downloadConfigFile(String fileName, HttpServletResponse response) {

        File scFile = new File(savePath + fileName);
        String srcFileName = scFile.getName();

        if (scFile.exists()) {

            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(srcFileName, "UTF-8"));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(scFile);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                response.setStatus(HttpStatus.OK.value());
                os.flush();
            } catch (Exception e) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public Upgrade SystemManagerUpgradeUpload(MultipartFile[] files, Integer type, Integer mode) {
        Upgrade upgrade = null;

        if (mode.equals(UpgradeModeEnum.INSTALL.value())) {
            if (type.equals(UpgradeTypeEnum.SINGLE_BOARD.value())) {
                upgrade = new SystemManagerSingleInstall();
            } else if (type.equals(UpgradeTypeEnum.COMPLETE_MACHINE.value())) {
                upgrade = new SystemManagerCompleteMachineInstall();
            }
        } else if (mode.equals(UpgradeModeEnum.UPDATE.value())) {
            if (type.equals(UpgradeTypeEnum.SINGLE_BOARD.value())) {
                upgrade = new SystemManagerSingleUpdate();
            } else if (type.equals(UpgradeTypeEnum.COMPLETE_MACHINE.value())) {
                upgrade = new SystemManagerCompleteMachineUpdate();
            }
        }

        if (upgrade != null) {
            upgrade.fileUpload(files, upgradePath);
            return upgrade;
        }

        return null;
    }

    @Async
    @Override
    public Future<String> SystemManagerUpgradeAsync(Upgrade upgrade, String sort) {
        if (upgrade != null) {
            upgrade.exchange(sort);
        }
        return new AsyncResult<>("任务完成");
    }

    /**
     * 查询设备信息
     *
     * @param username 当前操作用户
     * @throws InterruptedException
     */
    @Override
    public ReceiveSystemManagerDTO info(String username) throws Exception {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DEVICE_INFO.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DEVICE_INFO.getCode(),
                MessageCodeEnum.DEVICE_INFO.getReqCode(),
                username, 0, 0, null);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) clientServerSync.sendMessage(content);

        return receiveSystemManagerDTO;
    }

    /**
     * 获取设备槽位数
     *
     * @param username
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public int getDeviceSlotTotal(String username) throws Exception {

        ReceiveSystemManagerDTO info = info(username);
        Object data = JSONObject.toJSON(info.getData());
        JSONObject jsonObject = JSON.parseObject(data.toString());
        int chassisType = (int) jsonObject.get("m_u32EquType");

        ChassisTypeEnum chassisTypeEnum = ChassisTypeEnum.fromValue(chassisType);

        return chassisTypeEnum.getSlotNumber();
    }

    /**
     * 根据槽位号获取槽位板卡端口数
     *
     * @param slotNumber
     * @return
     */
    @Override
    public int getSlotPortTotal(String username, int slotNumber) throws Exception {

        ReceiveSystemManagerDTO info = info(username);
        Object data = JSONObject.toJSON(info.getData());
        JSONObject jsonObject = JSON.parseObject(data.toString());
        JSONArray cardSlotList = jsonObject.getJSONArray("m_tCardStatusMsg");


        for (int i = 0; i < cardSlotList.size(); i++) {
            JSONObject slot = cardSlotList.getJSONObject(i);
            if ((int) slot.get("m_u32SlotId") == slotNumber) {
                int slotType = (int) slot.get("m_u32CardType");
                CardTypeEnum cardTypeEnum = CardTypeEnum.fromValue(slotType);
                return cardTypeEnum.getPortNumber();
            }
        }

        return 0;
    }


    /**
     * 根据槽位获取当前板卡的类型
     *
     * @param username
     * @param slotNumber
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public int getSlotType(String username, int slotNumber) throws Exception {

        ReceiveSystemManagerDTO info = info(username);
        Object data = JSONObject.toJSON(info.getData());
        JSONObject jsonObject = JSON.parseObject(data.toString());
        JSONArray cardSlotList = jsonObject.getJSONArray("m_tCardStatusMsg");


        for (int i = 0; i < cardSlotList.size(); i++) {
            JSONObject slot = cardSlotList.getJSONObject(i);
            if ((int) slot.get("m_u32SlotId") == slotNumber) {
                int slotType = (int) slot.get("m_u32CardType");
                return slotType;
            }
        }

        return 0;
    }

    /**
     * 获取设备阈值信息
     */
    @Override
    public ReceiveSystemManagerDTO getThreshold(String username) throws Exception {
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DEVICE_THRESHOLD_CONFIG_OR_GET.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DEVICE_THRESHOLD_GET.getCode(),
                MessageCodeEnum.DEVICE_THRESHOLD_GET.getReqCode(),
                username, 0, 0, null);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) clientServerSync.sendMessage(content);

        return receiveSystemManagerDTO;
    }

    /**
     * 配置设备的阈值
     *
     * @param deviceThresholdConfigBO 传递的参数
     */
    @Override
    public ReceiveSystemManagerDTO configThreshold(DeviceThresholdConfigBO deviceThresholdConfigBO) throws Exception {

        Map<String, Object> data = new HashMap<>();
        data.put("thresholdName", deviceThresholdConfigBO.getThresholdName());
        data.put("thresholdValue", deviceThresholdConfigBO.getThresholdValue());
        data.put("thresholdPower", deviceThresholdConfigBO.getThresholdPower());

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DEVICE_THRESHOLD_CONFIG_OR_GET.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DEVICE_THRESHOLD_CONFIG.getCode(),
                MessageCodeEnum.DEVICE_THRESHOLD_CONFIG.getReqCode(),
                deviceThresholdConfigBO.getUsername(), null, null, data);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) clientServerSync.sendMessage(content);
        return receiveSystemManagerDTO;
    }

    @SneakyThrows
    @Override
    public ReceiveSystemManagerDTO operation(DeviceBO deviceBO) {
        SystemManagerSendingDevicePolicyService systemManagerSendingDevicePolicyService
                = SystemManagerSendingDeviceComponent.systemManagerSendingPolicyServiceMap.get(deviceBO.getDeviceType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingDevicePolicyService.dataEncapsulation(deviceBO);
        systemManagerSendingDevicePolicyService.recordUserLog(deviceBO);

        return receiveSystemManagerDTO;
    }

}
