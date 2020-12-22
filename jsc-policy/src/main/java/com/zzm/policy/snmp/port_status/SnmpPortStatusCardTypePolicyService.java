package com.zzm.policy.snmp.port_status;

import java.util.List;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-25
 * @description: 根据不同的板卡类型，来处理不同类型板卡端口状态
 **/
public interface SnmpPortStatusCardTypePolicyService {

    /**
     * 策略类型
     *
     * @return
     */
    int policyType();

    /**
     * 封装要发送的数据
     * @param indexNumber 端口号 5552455
     * @param slotNumber 槽位号
     * @param portNumber 端口号
     * @param portRate 端口速率
     * @param isEXTEND 是否甩纤
     * @param extendPortNumber 摔纤端口号
     * @param result 返回的数据
     * @param index 当前第多少的多口
     * @param portStatusList 端口状态
     */
    void dataEncapsulation(int indexNumber, int slotNumber, int portNumber, int portRate, int isEXTEND, int extendPortNumber, List<Map<String, Object>> result, int index, List<String> portStatusList);
}