package com.zzm.policy.system_manager.sending.system;

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
public class SystemManagerSendingSystemComponent {

    public static Map<String, SystemManagerSendingSystemPolicyService> systemManagerSendingResourcesPolicyServiceMap = new HashMap<>();

    public SystemManagerSendingSystemComponent(List<SystemManagerSendingSystemPolicyService> systemManagerSendingSystemPolicyServices) {
        for (SystemManagerSendingSystemPolicyService systemManagerSendingSystemPolicyService : systemManagerSendingSystemPolicyServices) {
            systemManagerSendingResourcesPolicyServiceMap.put(systemManagerSendingSystemPolicyService.policyType(), systemManagerSendingSystemPolicyService);
        }
    }
}
