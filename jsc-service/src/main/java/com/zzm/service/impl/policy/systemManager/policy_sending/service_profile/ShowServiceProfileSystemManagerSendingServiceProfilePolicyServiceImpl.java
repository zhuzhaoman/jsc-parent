package com.zzm.service.impl.policy.systemManager.policy_sending.service_profile;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.ServiceProfileBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.service_profile.SystemManagerSendingServiceProfilePolicyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class ShowServiceProfileSystemManagerSendingServiceProfilePolicyServiceImpl implements SystemManagerSendingServiceProfilePolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "show-service-profile";
    }

    @Override
    public Object dataEncapsulation(ServiceProfileBO serviceProfileBO) {


        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.SERVICE_PROFILE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.SERVICE_PROFILE_GET.getCode(),
                MessageCodeEnum.SERVICE_PROFILE_GET.getReqCode(),
                serviceProfileBO.getUsername(),
                serviceProfileBO.getDomainId(),
                serviceProfileBO.getDomainType(),
                serviceProfileBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }
}
