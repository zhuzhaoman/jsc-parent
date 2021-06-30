package com.zzm.policy.system_manager.sending.service_profile;

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
public class SystemManagerSendingServiceProfileComponent {

    public static Map<String, SystemManagerSendingServiceProfilePolicyService> systemManagerSendingServiceProfilePolicyServiceMap = new HashMap<>();

    public SystemManagerSendingServiceProfileComponent(List<SystemManagerSendingServiceProfilePolicyService> systemManagerSendingServiceProfilePolicyServices) {
        for (SystemManagerSendingServiceProfilePolicyService systemManagerSendingServiceProfilePolicyService : systemManagerSendingServiceProfilePolicyServices) {
            systemManagerSendingServiceProfilePolicyServiceMap.put(systemManagerSendingServiceProfilePolicyService.policyType(), systemManagerSendingServiceProfilePolicyService);
        }
    }
}
