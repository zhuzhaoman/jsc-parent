package com.zzm.policy.system_manager.received;

import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:34
 * @description 根据策略不同接口systemmanager返回的数据
 */
public interface SystemManagerReceivedPolicyService {

    int policyMessageCode();

    Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) throws Exception;

}
