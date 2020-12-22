package com.zzm.service.impl.policy.systemManager.policy_sending.system;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.SystemBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.system.SystemManagerSendingSystemPolicyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class FixCharGlobalBeginPosSystemManagerSendingSystemPolicyServiceImpl implements SystemManagerSendingSystemPolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "fix-char-global-begin-pos";
    }

    @Override
    public Object configDataEncapsulation(SystemBO systemBO){


        SendSystemManagerDTO sendSystemManagerDTO = getSendData(systemBO);
        sendSystemManagerDTO.setMessageCode(MessageCodeEnum.SYSTEM_CONFIG_FIX_CHAR_GLOBAL_BEGIN_POS.getReqCode());
        sendSystemManagerDTO.setData(systemBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;

    }

    @Override
    public Object getDataEncapsulation(SystemBO systemBO){
        return null;
    }


    private SendSystemManagerDTO getSendData(SystemBO systemBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.SYSTEM_CONFIG.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.SYSTEM_CONFIG.getCode(),
                systemBO.getUsername(),
                systemBO.getDomainId(),
                systemBO.getDomainType());

        return sendSystemManagerDTO;
    }
}
