package com.zzm.policy.system_manager.sending.system;

import com.zzm.pojo.bo.ResourcesBO;
import com.zzm.pojo.bo.SystemBO;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
public interface SystemManagerSendingSystemPolicyService {

    /**
     * 策略类型
     * @return
     */
    String policyType();

    /**
     * 封装配置要发送的数据
     * @param systemBO
     */
    Object configDataEncapsulation(SystemBO systemBO);

    /**
     * 封装查询要发送的数据
     * @param systemBO
     */
    Object getDataEncapsulation(SystemBO systemBO);

}
