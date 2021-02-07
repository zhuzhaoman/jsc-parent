package com.zzm.policy.system_manager.sending.port;


import com.zzm.pojo.bo.PortBO;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:49
 * @description 根据策略类型不同来发送不同的信息到systemmanager
 */
public interface SystemManagerSendingPortPolicyService {

    /**
     * 策略类型
     * @return
     */
    String policyType();

    /**
     * 封装要发送的数据
     * @param portBO
     */
    Object dataEncapsulation(PortBO portBO) throws Exception;
}
