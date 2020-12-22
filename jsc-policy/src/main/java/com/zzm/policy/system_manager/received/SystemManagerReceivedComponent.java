package com.zzm.policy.system_manager.received;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:46
 * @description 项目初始化将systemManager处理分支缓存到map中
 */
@Component
public class SystemManagerReceivedComponent {

    public static Map<Integer, SystemManagerReceivedPolicyService> systemManagerPolicyServiceMap = new HashMap<>();

    public SystemManagerReceivedComponent(List<SystemManagerReceivedPolicyService> systemManagerReceivedPolicyServices) {
        for (SystemManagerReceivedPolicyService systemManagerReceivedPolicyService : systemManagerReceivedPolicyServices) {
            systemManagerPolicyServiceMap.put(systemManagerReceivedPolicyService.policyMessageCode(), systemManagerReceivedPolicyService);
        }
    }
}
