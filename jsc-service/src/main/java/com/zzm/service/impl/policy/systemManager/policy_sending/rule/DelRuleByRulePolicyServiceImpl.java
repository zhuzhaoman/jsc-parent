package com.zzm.service.impl.policy.systemManager.policy_sending.rule;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.*;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-21
 * @description:
 **/
@Service
public class DelRuleByRulePolicyServiceImpl {

    @Resource
    private ClientServerSync clientServerSync;

    public Object delAllRule(RuleBO ruleBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_DEL.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_DEL.getCode(),
                MessageCodeEnum.RULE_ALL_DEL.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                ruleBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

    public Object delRuleByRuleType(RuleBO ruleBO) {

        String ruleType = ruleBO.getRuleType();

        RuleTypeDelRuleEnum ruleTypeDelRuleEnum = RuleTypeDelRuleEnum.fromCode(ruleType);

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_DEL.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_DEL.getCode(),
                ruleTypeDelRuleEnum.getMessageCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                ruleBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

}
