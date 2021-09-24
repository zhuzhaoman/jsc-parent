package com.zzm.policy.system_manager.sending.resources;

import com.zzm.pojo.bo.ResourcesBO;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
public interface SystemManagerSendingResourcesPolicyService {

    /**
     * 策略类型
     * @return
     */
    String policyType();

    /**
     * 封装配置要发送的数据
     * @param resourcesBO
     */
    Object configDataEncapsulation(ResourcesBO resourcesBO);

    /**
     * 封装查询要发送的数据
     * @param resourcesBO
     */
    Object getDataEncapsulation(ResourcesBO resourcesBO);

    /**
     * 封装释放要发送的数据
     * @param resourcesBO
     */
    Object releaseDataEncapsulation(ResourcesBO resourcesBO);

    void recordConfigOrReleaseUserLog(ResourcesBO resourcesBO, boolean isConfig);

    void recordGetUserLog(ResourcesBO resourcesBO);
}
