package com.zzm.policy.system_manager.sending.sys_log;

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
public class SystemManagerSendingSysLogComponent {

    public static Map<String, SystemManagerSendingSysLogPolicyService> systemManagerSendingSysLogPolicyServiceMap = new HashMap<>();

    public SystemManagerSendingSysLogComponent(List<SystemManagerSendingSysLogPolicyService> systemManagerSendingSysLogPolicyServices) {
        for (SystemManagerSendingSysLogPolicyService systemManagerSendingSysLogPolicyService : systemManagerSendingSysLogPolicyServices) {
            systemManagerSendingSysLogPolicyServiceMap.put(systemManagerSendingSysLogPolicyService.policyType(), systemManagerSendingSysLogPolicyService);
        }
    }
}
