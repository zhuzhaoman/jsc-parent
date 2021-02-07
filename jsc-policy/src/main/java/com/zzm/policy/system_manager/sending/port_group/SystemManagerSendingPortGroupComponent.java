package com.zzm.policy.system_manager.sending.port_group;

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
public class SystemManagerSendingPortGroupComponent {

    public static Map<String, SystemManagerSendingPortGroupPolicyService> systemManagerSendingPortGroupPolicyServiceMap = new HashMap<>();

    public SystemManagerSendingPortGroupComponent(List<SystemManagerSendingPortGroupPolicyService> systemManagerSendingPortGroupPolicyServices) {
        for (SystemManagerSendingPortGroupPolicyService systemManagerSendingPortGroupPolicyService : systemManagerSendingPortGroupPolicyServices) {
            systemManagerSendingPortGroupPolicyServiceMap.put(systemManagerSendingPortGroupPolicyService.policyType(), systemManagerSendingPortGroupPolicyService);
        }
    }
}
