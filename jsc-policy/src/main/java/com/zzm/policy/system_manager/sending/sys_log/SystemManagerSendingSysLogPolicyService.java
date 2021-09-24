package com.zzm.policy.system_manager.sending.sys_log;

import com.zzm.pojo.bo.DeviceBO;
import com.zzm.pojo.bo.SysLogBO;
import com.zzm.pojo.bo.SystemBO;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
public interface SystemManagerSendingSysLogPolicyService {

    /**
     * 策略类型
     * @return
     */
    String policyType();

    /**
     * 封装配置要发送的数据
     * @param sysLogBO
     */
    Object configDataEncapsulation(SysLogBO sysLogBO);

    /**
     * 封装查询要发送的数据
     * @param sysLogBO
     */
    Object getDataEncapsulation(SysLogBO sysLogBO);

    void recordUserLog(SysLogBO sysLogBO);

}
