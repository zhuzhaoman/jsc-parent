package com.zzm.policy.system_manager.sending.service_profile;

import com.zzm.pojo.bo.ServiceProfileBO;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
public interface SystemManagerSendingServiceProfilePolicyService {

    /**
     * 策略类型
     * @return
     */
    String policyType();

    /**
     * 封装配置要发送的数据
     * @param serviceProfileBO
     */
    Object dataEncapsulation(ServiceProfileBO serviceProfileBO);

}
