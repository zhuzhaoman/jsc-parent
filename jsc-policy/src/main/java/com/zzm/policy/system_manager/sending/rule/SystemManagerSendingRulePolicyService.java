package com.zzm.policy.system_manager.sending.rule;

import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.bo.ServiceProfileBO;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
public interface SystemManagerSendingRulePolicyService {

    /**
     * 策略类型
     * @return
     */
    String policyType();

    /**
     * 封装添加要发送的数据
     * @param ruleBO
     */
    Object addDataEncapsulation(RuleBO ruleBO);

    /**
     * 封装删除要发送的数据
     * @param ruleBO
     */
    Object delDataEncapsulation(RuleBO ruleBO);

    /**
     * 封装查询要发送的数据
     * @param ruleBO
     */
    Object findDataEncapsulation(RuleBO ruleBO);

    void recordUserLog(RuleBO ruleBO);

}
