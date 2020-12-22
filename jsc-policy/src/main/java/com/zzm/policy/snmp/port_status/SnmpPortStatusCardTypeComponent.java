package com.zzm.policy.snmp.port_status;

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
public class SnmpPortStatusCardTypeComponent {

    public static Map<Integer, SnmpPortStatusCardTypePolicyService> snmpCardTypePolicyServiceMap = new HashMap<>();

    public SnmpPortStatusCardTypeComponent(List<SnmpPortStatusCardTypePolicyService> snmpPortStatusCardTypePolicyServices) {
        for (SnmpPortStatusCardTypePolicyService snmpPortStatusCardTypePolicyService : snmpPortStatusCardTypePolicyServices) {
            snmpCardTypePolicyServiceMap.put(snmpPortStatusCardTypePolicyService.policyType(), snmpPortStatusCardTypePolicyService);
        }
    }

}
