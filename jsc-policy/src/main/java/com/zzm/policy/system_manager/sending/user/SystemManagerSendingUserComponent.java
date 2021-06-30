package com.zzm.policy.system_manager.sending.user;

import com.zzm.policy.system_manager.sending.system.SystemManagerSendingSystemPolicyService;
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
public class SystemManagerSendingUserComponent {

    public static Map<String, SystemManagerSendingUserPolicyService> systemManagerSendingUserPolicyServiceMap = new HashMap<>();

    public SystemManagerSendingUserComponent(List<SystemManagerSendingUserPolicyService> systemManagerSendingUserPolicyServices) {
        for (SystemManagerSendingUserPolicyService systemManagerSendingUserPolicyService : systemManagerSendingUserPolicyServices) {
            systemManagerSendingUserPolicyServiceMap.put(systemManagerSendingUserPolicyService.policyType(), systemManagerSendingUserPolicyService);
        }
    }
}
