package com.zzm.policy.system_manager.sending.rule;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-25
 * @description:
 **/
@Component
public class SystemManagerSendingRuleComponent {

    public static Map<String, SystemManagerSendingRulePolicyService> systemManagerSendingRulePolicyServiceMap = new HashMap<>();

    public SystemManagerSendingRuleComponent(List<SystemManagerSendingRulePolicyService> systemManagerSendingRulePolicyServices) {
        for (SystemManagerSendingRulePolicyService systemManagerSendingRulePolicyService : systemManagerSendingRulePolicyServices) {
            systemManagerSendingRulePolicyServiceMap.put(systemManagerSendingRulePolicyService.policyType(), systemManagerSendingRulePolicyService);
        }
    }
}
