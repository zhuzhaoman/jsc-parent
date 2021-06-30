package com.zzm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.exception.GraceException;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.UserAddBO;
import com.zzm.pojo.bo.UserBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRuleComponent;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.policy.system_manager.sending.user.SystemManagerSendingUserComponent;
import com.zzm.policy.system_manager.sending.user.SystemManagerSendingUserPolicyService;
import com.zzm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author zhuzhaoman
 * @date 2020/8/19 0019 15:39
 * @description 用户基本操作业务实现类
 */
@SuppressWarnings("all")
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ClientServerSync clientServerSync;

    /**
     * 用户登录
     *
     * @param userBO
     * @throws InterruptedException
     */
    @Override
    public ReceiveSystemManagerDTO login(UserBO userBO) throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("username", userBO.getUsername());
        param.put("password", userBO.getPassword());

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.LOGIN.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.LOGIN.getCode(),
                MessageCodeEnum.CONNECT_REQUEST.getReqCode(),
                userBO.getUsername(), 0, 0, param);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) clientServerSync.sendMessage(content);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO guestLogin(UserBO userBO) {

        if (!userBO.getUsername().equals("guest") || !userBO.getPassword().equals("guest")) {
            GraceException.display("用户名或密码错误");
        }

        ReceiveSystemManagerDTO receiveSystemManagerDTO = new ReceiveSystemManagerDTO();
        receiveSystemManagerDTO.setMsg("登录成功");
        receiveSystemManagerDTO.setMessageCode(0);
        receiveSystemManagerDTO.setDomainId(0);
        receiveSystemManagerDTO.setUsername(userBO.getUsername());
        receiveSystemManagerDTO.setDomainType(0);
        receiveSystemManagerDTO.setCode(200);
        receiveSystemManagerDTO.setData(null);
        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO config(UserBO userBO) {
        SystemManagerSendingUserPolicyService systemManagerSendingUserPolicyService =
                SystemManagerSendingUserComponent.systemManagerSendingUserPolicyServiceMap.get(userBO.getUserType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingUserPolicyService.configDataEncapsulation(userBO);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO getInfo(UserBO userBO) {
        SystemManagerSendingUserPolicyService systemManagerSendingUserPolicyService =
                SystemManagerSendingUserComponent.systemManagerSendingUserPolicyServiceMap.get(userBO.getUserType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingUserPolicyService.getDataEncapsulation(userBO);

        return receiveSystemManagerDTO;
    }
}
