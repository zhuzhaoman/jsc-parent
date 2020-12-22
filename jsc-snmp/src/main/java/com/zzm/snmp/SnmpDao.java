package com.zzm.snmp;

import com.zzm.resource.SnmpResource;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SnmpDao {

    private Snmp snmp;
    private CommunityTarget target;

    @Resource
    private SnmpResource snmpResource;

    public void init() {
        try {
            DefaultUdpTransportMapping dm = new DefaultUdpTransportMapping();
            snmp = new Snmp(dm);
            snmp.listen();
            target = new CommunityTarget();
            target.setCommunity(new OctetString(snmpResource.getCommunityName()));
            target.setVersion(snmpResource.getVersion()); //  snmp版本
            target.setAddress(new UdpAddress(snmpResource.getHostIp() + "/" + snmpResource.getPort())); // 设置管理进程的IP和端口
            target.setTimeout(1000); // 超时时间
            target.setRetries(1); // 通信不成功时的重试次数
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取指定OID对应的table值
     *
     * @param oid
     * @return
     */
    public List<String> walkByTableToList(String oid) {
        if (snmp == null || target == null) {
            init();
        }

        List<String> result = new ArrayList<String>();

        try {

            TableUtils tableUtils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));
            OID[] columns = new OID[1];
            columns[0] = new VariableBinding(new OID(oid)).getOid();
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);

            for (TableEvent e : list) {
                VariableBinding[] vb = e.getColumns();
                if (null == vb) continue;
                result.add(vb[0].getVariable().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                snmp.close();
                snmp = null;
                target = null;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 获取指定OID单个值
     *
     * @param oid
     * @return
     */
    public List<String> walkByTableToOne(String oid) {
        if (snmp == null || target == null) {
            init();
        }

        PDU pdu = new PDU();
        List<String> result = new ArrayList<String>();

        try {
            pdu.add(new VariableBinding(new OID(oid)));
            pdu.setType(PDU.GET);

            ResponseEvent responseEvent = snmp.send(pdu, target);

            if (responseEvent != null) {
                PDU response = responseEvent.getResponse();

                for (VariableBinding variableBinding : response.getVariableBindings()) {
                    Variable variable = variableBinding.getVariable();
                    result.add(variable.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                snmp.close();
                snmp = null;
                target = null;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return result;
    }
}


