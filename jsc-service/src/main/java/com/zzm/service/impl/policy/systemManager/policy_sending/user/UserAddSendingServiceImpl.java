package com.zzm.service.impl.policy.systemManager.policy_sending.user;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.SysLogBO;
import com.zzm.pojo.bo.UserBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.user.SystemManagerSendingUserPolicyService;
import com.zzm.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Date;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class UserAddSendingServiceImpl implements SystemManagerSendingUserPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

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

        return clientServerSync.sendMessage(content);
    }

    @Override
    public Object getDataEncapsulation(UserBO userBO) {
        return null;
    }

    @Override
    @Transactional
    public void recordUserLog(UserBO userBO) {
        StringBuilder content = new StringBuilder(MessageCodeEnum.USER_ADD.getMsg());
        try {
            JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(userBO.getParam()));
            content.append("【")
                    .append("基础用户:").append(params.getString("m_strBasicUserName")).append("、")
                    .append("新增用户:").append(params.getString("m_strUserName"))
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(userBO.getUsername())
                .operationTitle("用户管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
