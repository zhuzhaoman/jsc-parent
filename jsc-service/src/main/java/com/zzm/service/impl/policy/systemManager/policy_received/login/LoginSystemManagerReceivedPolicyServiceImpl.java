package com.zzm.service.impl.policy.systemManager.policy_received.login;

import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import org.springframework.stereotype.Service;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:38
 * @description 用户登录，systemManager返回数据
 */
@Service
@SuppressWarnings("all")
public class LoginSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.CONNECT_REQUEST.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        if(receiveSystemManagerDTO.getCode() == 200) {
            receiveSystemManagerDTO.setData(null);
            String username = receiveSystemManagerDTO.getUsername();
            receiveSystemManagerDTO.setMsg(username + " 登录成功！！！");
        }

        return receiveSystemManagerDTO;
    }
}
