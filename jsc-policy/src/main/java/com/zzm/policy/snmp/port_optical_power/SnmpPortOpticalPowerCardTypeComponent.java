package com.zzm.policy.snmp.port_optical_power;

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
public class SnmpPortOpticalPowerCardTypeComponent {
    public static Map<Integer, SnmpPortOpticalPowerCardTypePolicyService> snmpCardTypePolicyServiceMap = new HashMap<>();

    public SnmpPortOpticalPowerCardTypeComponent(List<SnmpPortOpticalPowerCardTypePolicyService> snmpPortOpticalPowerCardTypePolicyServices) {
        for (SnmpPortOpticalPowerCardTypePolicyService snmpPortOpticalPowerCardTypePolicyService : snmpPortOpticalPowerCardTypePolicyServices) {
            snmpCardTypePolicyServiceMap.put(snmpPortOpticalPowerCardTypePolicyService.policyType(), snmpPortOpticalPowerCardTypePolicyService);
        }
    }
}
