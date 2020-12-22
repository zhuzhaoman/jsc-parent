package com.zzm.policy.snmp.port_flow;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-29
 * @description:
 **/
@Component
public class SnmpPortFlowCardTypeComponent {
    public static Map<Integer, SnmpPortFlowCardTypePolicyService> snmpCardTypePolicyServiceMap = new HashMap<>();

    public SnmpPortFlowCardTypeComponent(List<SnmpPortFlowCardTypePolicyService> snmpPortFlowCardTypePolicyServices) {
        for (SnmpPortFlowCardTypePolicyService snmpPortFlowCardTypePolicyService : snmpPortFlowCardTypePolicyServices) {
            snmpCardTypePolicyServiceMap.put(snmpPortFlowCardTypePolicyService.policyType(), snmpPortFlowCardTypePolicyService);
        }
    }
}
