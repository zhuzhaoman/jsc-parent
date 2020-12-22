package com.zzm.policy.system_manager.sending.real_time_flow;

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
public class SystemManagerSendingRealTimeFlowComponent {

    public static Map<String, SystemManagerSendingRealTimeFlowPolicyService> systemManagerSendingPolicyServiceMap = new HashMap<>();

    public SystemManagerSendingRealTimeFlowComponent(List<SystemManagerSendingRealTimeFlowPolicyService> systemManagerSendingRealTimeFlowPolicyServices) {
        for (SystemManagerSendingRealTimeFlowPolicyService systemManagerSendingRealTimeFlowPolicyService : systemManagerSendingRealTimeFlowPolicyServices) {
            systemManagerSendingPolicyServiceMap.put(systemManagerSendingRealTimeFlowPolicyService.policyType(), systemManagerSendingRealTimeFlowPolicyService);
        }
    }
}
