package com.zzm.service.impl.policy.systemManager.policy_sending.service_profile;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.ServiceProfileBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.service_profile.SystemManagerSendingServiceProfilePolicyService;
import com.zzm.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.apache.groovy.json.internal.Chr.add;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class ConfigServiceProfileSystemManagerSendingServiceProfilePolicyServiceImpl implements SystemManagerSendingServiceProfilePolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "config-service-profile";
    }

    @Override
    public Object dataEncapsulation(ServiceProfileBO serviceProfileBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.SERVICE_PROFILE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.SERVICE_PROFILE_CONFIG.getCode(),
                MessageCodeEnum.SERVICE_PROFILE_CONFIG_RELAY.getReqCode(),
                serviceProfileBO.getUsername(),
                serviceProfileBO.getDomainId(),
                serviceProfileBO.getDomainType(),
                serviceProfileBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    @Transactional
    public void recordUserLog(ServiceProfileBO serviceProfileBO) {
        StringBuilder content = new StringBuilder(MessageCodeEnum.SERVICE_PROFILE_CONFIG_RELAY.getMsg());
        try {
            JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(serviceProfileBO.getParam()));
            content.append("【")
                    .append("业务策略ID:").append(params.getInteger("m_u32ServiceProfileId")).append("、")
                    .append("转发动作:").append(params.getInteger("m_u32Action"))
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(serviceProfileBO.getUsername())
                .operationTitle("业务策略管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
