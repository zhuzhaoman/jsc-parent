package com.zzm.policy.system_manager.sending.real_time_flow;


import com.zzm.pojo.bo.RealTimeFlowBO;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:49
 * @description 根据策略类型不同来发送不同的信息到systemmanager
 */
public interface SystemManagerSendingRealTimeFlowPolicyService {

    /**
     * 策略类型
     * @return
     */
    String policyType();

    /**
     * 封装要发送的数据
     * @param realTimeFlowBO
     */
    Object dataEncapsulation(RealTimeFlowBO realTimeFlowBO) throws Exception;
}
