package com.zzm.policy.system_manager.sending.device;

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
public class SystemManagerSendingDeviceComponent {

    public static Map<String, SystemManagerSendingDevicePolicyService> systemManagerSendingPolicyServiceMap = new HashMap<>();

    public SystemManagerSendingDeviceComponent(List<SystemManagerSendingDevicePolicyService> systemManagerSendingDevicePolicyServices) {
        for (SystemManagerSendingDevicePolicyService systemManagerSendingDevicePolicyService : systemManagerSendingDevicePolicyServices) {
            systemManagerSendingPolicyServiceMap.put(systemManagerSendingDevicePolicyService.policyType(), systemManagerSendingDevicePolicyService);
        }
    }
}
