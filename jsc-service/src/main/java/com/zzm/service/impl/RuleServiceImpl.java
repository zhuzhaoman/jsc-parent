package com.zzm.service.impl;

import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRuleComponent;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.RuleService;
import com.zzm.service.impl.policy.systemManager.policy_sending.rule.DelRuleByRulePolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private DelRuleByRulePolicyServiceImpl delRuleByRulePolicyService;

    @Override
    public ReceiveSystemManagerDTO addRule(RuleBO ruleBO) {

        SystemManagerSendingRulePolicyService systemManagerSendingRulePolicyService =
                SystemManagerSendingRuleComponent.systemManagerSendingRulePolicyServiceMap.get(ruleBO.getRuleType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingRulePolicyService.addDataEncapsulation(ruleBO);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO delRule(RuleBO ruleBO){
        SystemManagerSendingRulePolicyService systemManagerSendingRulePolicyService =
                SystemManagerSendingRuleComponent.systemManagerSendingRulePolicyServiceMap.get(ruleBO.getRuleType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingRulePolicyService.delDataEncapsulation(ruleBO);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO delAllRule(RuleBO ruleBO) {

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) delRuleByRulePolicyService.delAllRule(ruleBO);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO delRuleByRuleType(RuleBO ruleBO) {
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) delRuleByRulePolicyService.delRuleByRuleType(ruleBO);

        return receiveSystemManagerDTO;
    }
}
