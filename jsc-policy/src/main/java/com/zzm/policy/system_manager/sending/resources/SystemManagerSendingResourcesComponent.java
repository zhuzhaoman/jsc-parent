package com.zzm.policy.system_manager.sending.resources;

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
public class SystemManagerSendingResourcesComponent {

    public static Map<String, SystemManagerSendingResourcesPolicyService> systemManagerSendingResourcesPolicyServiceMap = new HashMap<>();

    public SystemManagerSendingResourcesComponent(List<SystemManagerSendingResourcesPolicyService> systemManagerSendingResourcesPolicyServices) {
        for (SystemManagerSendingResourcesPolicyService systemManagerSendingResourcesPolicyService : systemManagerSendingResourcesPolicyServices) {
            systemManagerSendingResourcesPolicyServiceMap.put(systemManagerSendingResourcesPolicyService.policyType(), systemManagerSendingResourcesPolicyService);
        }
    }
}
