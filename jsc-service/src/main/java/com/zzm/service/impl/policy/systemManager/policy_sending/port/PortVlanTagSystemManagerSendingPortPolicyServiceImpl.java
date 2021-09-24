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
public class PortVlanTagSystemManagerSendingPortPolicyServiceImpl implements SystemManagerSendingPortPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "port-vlan-tag";
    }

    public JSONArray handle(PortBO portBO) {
        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam()));;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(i)));
            int m_u32VlanTag =  BaseConversionUtils.hex2int(jsonObject.getString("m_u32VlanTag"));
            jsonObject.put("m_u32VlanTag", m_u32VlanTag);
            jsonArray.set(i, jsonObject);
        }
        return jsonArray;
    }

    @Override
    public Object dataEncapsulation(PortBO portBO) throws Exception {
        Object sendData = handle(portBO);
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.INTERFACE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.INTERFACE_CONFIG.getCode(),
                MessageCodeEnum.INTERFACE_SHOW_VLAN_TAG.getReqCode(),
                portBO.getUsername(),
                portBO.getDomainId(),
                portBO.getDomainType(),
                sendData);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public void recordUserLog(PortBO portBO) {
        StringBuilder content = new StringBuilder("端口配置 >>> " + MessageCodeEnum.INTERFACE_SHOW_VLAN_TAG.getMsg());
        try {
            JSONObject params = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam())).getJSONObject(0);
            content.append("【")
                    .append("端口ID:").append(params.getInteger("m_u32PortId")).append("、")
                    .append("VlanTag值:").append(params.getString("m_u32VlanTag"))
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(portBO.getUsername())
                .operationTitle("端口管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
