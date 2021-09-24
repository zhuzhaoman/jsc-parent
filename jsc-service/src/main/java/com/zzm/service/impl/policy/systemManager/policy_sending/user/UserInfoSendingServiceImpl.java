package com.zzm.service.impl.policy.systemManager.policy_sending.user;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.UserBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.user.SystemManagerSendingUserPolicyService;
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
public class UserInfoSendingServiceImpl implements SystemManagerSendingUserPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

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

        return clientServerSync.sendMessage(content);
    }

    @Override
    @Transactional
    public void recordUserLog(UserBO userBO) {
        OperationLog operationLog = OperationLog.builder().username(userBO.getUsername())
                .operationTitle("用户管理")
                .operationContent(MessageCodeEnum.USER_GET.getMsg())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
