package com.zzm.policy.system_manager.sending.port;

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
public class SystemManagerSendingPortComponent {

    public static Map<String, SystemManagerSendingPortPolicyService> systemManagerSendingPolicyServiceMap = new HashMap<>();

    public SystemManagerSendingPortComponent(List<SystemManagerSendingPortPolicyService> systemManagerSendingPortPolicyServices) {
        for (SystemManagerSendingPortPolicyService systemManagerSendingPortPolicyService : systemManagerSendingPortPolicyServices) {
            systemManagerSendingPolicyServiceMap.put(systemManagerSendingPortPolicyService.policyType(), systemManagerSendingPortPolicyService);
        }
    }
}
