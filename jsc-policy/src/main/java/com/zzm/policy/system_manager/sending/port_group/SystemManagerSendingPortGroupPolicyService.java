package com.zzm.policy.system_manager.sending.port_group;

import com.zzm.pojo.bo.PortGroupBO;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
public interface SystemManagerSendingPortGroupPolicyService {

    /**
     * 策略类型
     * @return
     */
    String policyType();

    /**
     * 封装配置要发送的数据
     * @param portGroupBO
     */
    Object dataEncapsulation(PortGroupBO portGroupBO);

    void recordUserLog(PortGroupBO portGroupBO);
}
