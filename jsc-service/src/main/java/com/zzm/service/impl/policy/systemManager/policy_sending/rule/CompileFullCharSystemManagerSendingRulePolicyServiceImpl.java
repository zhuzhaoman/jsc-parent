package com.zzm.service.impl.policy.systemManager.policy_sending.rule;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.DeviceBO;
import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.LogService;
import com.zzm.service.impl.policy.systemManager.policy_sending.BaseSystemManagerSendingPolicyServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class CompileFullCharSystemManagerSendingRulePolicyServiceImpl extends BaseSystemManagerSendingPolicyServiceImpl implements SystemManagerSendingRulePolicyService {


    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "compile-full-char";
    }


    @Override
    public Object addDataEncapsulation(RuleBO ruleBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_ADD.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_ADD.getCode(),
                MessageCodeEnum.COMPILE_FULL_CHAR.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                ruleBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public Object delDataEncapsulation(RuleBO ruleBO) {
        return null;
    }

    @Override
    public Object findDataEncapsulation(RuleBO ruleBO) {
      return null;
    }

    @Override
    @Transactional
    public void recordUserLog(RuleBO ruleBO) {
        StringBuilder content = new StringBuilder(MessageCodeEnum.COMPILE_FULL_CHAR.getMsg());
        try {
            JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(ruleBO.getParam()));
            content.append("【")
                    .append("规则ID:").append(params.getInteger("m_u32AclIndex"))
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }

        OperationLog operationLog = OperationLog.builder().username(ruleBO.getUsername())
                .operationTitle("规则管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
