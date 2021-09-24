package com.zzm.service.impl.policy.systemManager.policy_sending.port_group;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.port_group.SystemManagerSendingPortGroupPolicyService;
import com.zzm.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class ConfigOutputGroupWeightSystemManagerSendingPortGroupPolicyServiceImpl implements SystemManagerSendingPortGroupPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "config-group-weight";
    }

    @Override
    public Object dataEncapsulation(PortGroupBO portGroupBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.PORT_GROUP.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.PORT_GROUP_CONFIG.getCode(),
                MessageCodeEnum.PORT_GROUP_CONFIG_OUTPUT_GROUP_WEIGHT.getReqCode(),
                portGroupBO.getUsername(),
                portGroupBO.getDomainId(),
                portGroupBO.getDomainType(),
                portGroupBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    @Transactional
    public void recordUserLog(PortGroupBO portGroupBO) {
        StringBuilder content = new StringBuilder(MessageCodeEnum.PORT_GROUP_CONFIG_OUTPUT_GROUP_WEIGHT.getMsg());
        try {
            JSONObject params = JSONArray.parseArray(JSONObject.toJSONString(portGroupBO.getParam())).getJSONObject(0);

            content.append("【")
                    .append("端口组ID:").append(params.getInteger("m_u32PortGroup")).append("、")
                    .append("端口ID:").append(params.getJSONArray("m_tPortWeightMsg")
                    .getJSONObject(0).getInteger("m_u32PortId")).append("、")
                    .append("权重:").append(params.getJSONArray("m_tPortWeightMsg")
                    .getJSONObject(0).getInteger("m_u32PortWeight")).append("、")
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(portGroupBO.getUsername())
                .operationTitle("端口组管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
