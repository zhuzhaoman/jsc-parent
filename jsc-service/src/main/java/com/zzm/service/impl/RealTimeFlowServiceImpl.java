package com.zzm.service.impl;

import com.zzm.pojo.bo.RealTimeFlowBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.sending.real_time_flow.SystemManagerSendingRealTimeFlowComponent;
import com.zzm.policy.system_manager.sending.real_time_flow.SystemManagerSendingRealTimeFlowPolicyService;
import com.zzm.service.RealTimeFlowService;
import org.springframework.stereotype.Service;

/**
 * @author zhuzhaoman
 * @date 2020/8/21 0021 15:39
 * @description 获取端口实时流量业务实现类
 */
@Service
public class RealTimeFlowServiceImpl implements RealTimeFlowService {

    /**
     * 执行指定的业务实现类
     *
     * @param realTimeFlowBO 参数
     * @throws InterruptedException
     */
    @Override
    public ReceiveSystemManagerDTO realTimeFlow(RealTimeFlowBO realTimeFlowBO) throws Exception {

        SystemManagerSendingRealTimeFlowPolicyService systemManagerSendingRealTimeFlowPolicyService =
                SystemManagerSendingRealTimeFlowComponent.systemManagerSendingPolicyServiceMap.get(realTimeFlowBO.getType());
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingRealTimeFlowPolicyService.dataEncapsulation(realTimeFlowBO);
        systemManagerSendingRealTimeFlowPolicyService.recordUserLog(realTimeFlowBO);

        return receiveSystemManagerDTO;
    }
}
