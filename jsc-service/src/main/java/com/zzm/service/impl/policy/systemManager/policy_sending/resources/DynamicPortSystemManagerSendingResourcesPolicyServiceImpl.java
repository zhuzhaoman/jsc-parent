package com.zzm.service.impl.policy.systemManager.policy_sending.resources;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.ResourcesBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.resources.SystemManagerSendingResourcesPolicyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class DynamicPortSystemManagerSendingResourcesPolicyServiceImpl implements SystemManagerSendingResourcesPolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "dynamic-port";
    }

    @Override
    public Object configDataEncapsulation(ResourcesBO resourcesBO) {

        SendSystemManagerDTO sendSystemManagerDTO = getSendData(resourcesBO);
        sendSystemManagerDTO.setMessageCode(MessageCodeEnum.DYNAMIC_PORT_RESOURCES_CONFIG.getReqCode());
        sendSystemManagerDTO.setData(resourcesBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

    @Override
    public Object getDataEncapsulation(ResourcesBO resourcesBO) {
        return null;
    }

    @Override
    public Object releaseDataEncapsulation(ResourcesBO resourcesBO) {
        SendSystemManagerDTO sendSystemManagerDTO = getSendData(resourcesBO);
        sendSystemManagerDTO.setMessageCode(MessageCodeEnum.DYNAMIC_PORT_RESOURCES_CONFIG.getReqCode());
        sendSystemManagerDTO.setData(resourcesBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

    private SendSystemManagerDTO getSendData(ResourcesBO resourcesBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RESOURCES_CONFIG.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.RESOURCES_CONFIG.getCode(),
                resourcesBO.getUsername(),
                resourcesBO.getDomainId(),
                resourcesBO.getDomainType());

        return sendSystemManagerDTO;
    }
}
