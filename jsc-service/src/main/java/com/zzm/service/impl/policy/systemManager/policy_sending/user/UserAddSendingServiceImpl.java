package com.zzm.service.impl.policy.systemManager.policy_sending.user;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.SystemBO;
import com.zzm.pojo.bo.UserBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.system.SystemManagerSendingSystemPolicyService;
import com.zzm.policy.system_manager.sending.user.SystemManagerSendingUserPolicyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class UserAddSendingServiceImpl implements SystemManagerSendingUserPolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "user-add";
    }

    public JSONObject handle(UserBO userBO) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(userBO.getParam()));
        jsonObject.put("m_strUserName", Base64.getEncoder().encodeToString(jsonObject.getString("m_strUserName").getBytes()));
        jsonObject.put("m_strBasicUserName", Base64.getEncoder().encodeToString(jsonObject.getString("m_strBasicUserName").getBytes()));
        jsonObject.put("m_strPassword", Base64.getEncoder().encodeToString(jsonObject.getString("m_strPassword").getBytes()));

        return jsonObject;
    }

    @Override
    public Object configDataEncapsulation(UserBO userBO) {
        JSONObject sendData = handle(userBO);
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.USER.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.USER_CONFIG.getCode(),
                userBO.getUsername(),
                userBO.getDomainId(),
                userBO.getDomainType());
        sendSystemManagerDTO.setMessageCode(MessageCodeEnum.USER_ADD.getReqCode());
        sendSystemManagerDTO.setData(sendData);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

    @Override
    public Object getDataEncapsulation(UserBO userBO) {
        return null;
    }

}
