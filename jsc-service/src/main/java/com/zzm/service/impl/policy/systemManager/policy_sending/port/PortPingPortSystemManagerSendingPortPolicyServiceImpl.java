package com.zzm.service.impl.policy.systemManager.policy_sending.port;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.PortBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.port.SystemManagerSendingPortPolicyService;
import com.zzm.service.LogService;
import com.zzm.utils.BaseConversionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class PortPingPortSystemManagerSendingPortPolicyServiceImpl implements SystemManagerSendingPortPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "ping-port";
    }

    private Object addHandle(PortBO portBO) {
        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam()));
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(i)));

            String ipv4SipStr = jsonObject.getString("m_u32Ipv4Sip");
            String ipv4DipStr = jsonObject.getString("m_u32Ipv4Dip");

            jsonObject.put("m_u32Ipv4Sip", BaseConversionUtils.ip2Long(ipv4SipStr));
            jsonObject.put("m_u32Ipv4Dip", BaseConversionUtils.ip2Long(ipv4DipStr));

            jsonArray.set(i, jsonObject);
        }

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.INTERFACE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.INTERFACE_CONFIG.getCode(),
                MessageCodeEnum.INTERFACE_PING_PORT.getReqCode(),
                portBO.getUsername(),
                portBO.getDomainId(),
                portBO.getDomainType(),
                jsonArray);

        String content = JSONArray.toJSONString(sendSystemManagerDTO);
        return clientServerSync.sendMessage(content);
    }

    private Object delHandle(PortBO portBO) {

        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam()));
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(i)));

            String ipv4DipStr = jsonObject.getString("m_u32Ipv4Dip");
            jsonObject.put("m_u32Ipv4Dip", BaseConversionUtils.ip2Long(ipv4DipStr));

            jsonArray.set(i, jsonObject);
        }

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.INTERFACE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.INTERFACE_CONFIG.getCode(),
                MessageCodeEnum.INTERFACE_PING_PORT.getReqCode(),
                portBO.getUsername(),
                portBO.getDomainId(),
                portBO.getDomainType(),
                jsonArray);

        String content = JSONArray.toJSONString(sendSystemManagerDTO);
        return clientServerSync.sendMessage(content);
    }

    private Object getHandle(PortBO portBO) {
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.INTERFACE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.INTERFACE_CONFIG.getCode(),
                MessageCodeEnum.INTERFACE_PING_PORT.getReqCode(),
                portBO.getUsername(),
                portBO.getDomainId(),
                portBO.getDomainType(),
                portBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public Object dataEncapsulation(PortBO portBO) throws Exception {
        if ("get".equals(portBO.getPortAction())) {
            return this.getHandle(portBO);
        } else if ("add".equals(portBO.getPortAction())) {
            return this.addHandle(portBO);
        } else if ("del".equals(portBO.getPortAction())) {
            return this.delHandle(portBO);
        }
        return null;
    }

    @Override
    public void recordUserLog(PortBO portBO) {
        StringBuilder content = new StringBuilder("端口配置 >>> " + MessageCodeEnum.INTERFACE_PING_PORT.getMsg());
        OperationLog operationLog = OperationLog.builder().username(portBO.getUsername())
                .operationTitle("端口管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
