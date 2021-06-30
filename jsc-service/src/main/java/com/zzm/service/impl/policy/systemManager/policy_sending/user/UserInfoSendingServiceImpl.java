package com.zzm.service.impl.policy.systemManager.policy_sending.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.PortBO;
import com.zzm.pojo.bo.UserBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.user.SystemManagerSendingUserPolicyService;
import com.zzm.utils.BaseConversionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class UserInfoSendingServiceImpl implements SystemManagerSendingUserPolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "user-get";
    }

    @Override
    public Object configDataEncapsulation(UserBO userBO) {
        return null;
    }

    @Override
    public Object getDataEncapsulation(UserBO userBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.USER.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.USER_GET.getCode(),
                userBO.getUsername(),
                userBO.getDomainId(),
                userBO.getDomainType());
        sendSystemManagerDTO.setMessageCode(MessageCodeEnum.USER_GET.getReqCode());
        sendSystemManagerDTO.setData(userBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

}
