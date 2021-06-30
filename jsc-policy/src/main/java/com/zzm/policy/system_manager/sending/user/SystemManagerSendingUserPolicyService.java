package com.zzm.policy.system_manager.sending.user;

import com.zzm.pojo.bo.SystemBO;
import com.zzm.pojo.bo.UserBO;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
public interface SystemManagerSendingUserPolicyService {

    /**
     * 策略类型
     * @return
     */
    String policyType();

    /**
     * 封装配置要发送的数据
     * @param userBO
     */
    Object configDataEncapsulation(UserBO userBO);

    /**
     * 封装查询要发送的数据
     * @param userBO
     */
    Object getDataEncapsulation(UserBO userBO);

}
