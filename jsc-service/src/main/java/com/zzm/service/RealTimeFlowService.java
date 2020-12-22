package com.zzm.service;

import com.zzm.pojo.bo.RealTimeFlowBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

public interface RealTimeFlowService {

    /**
     * 获取端口实时流量
     * @param realTimeFlowBO 参数
     * @throws InterruptedException
     */
    ReceiveSystemManagerDTO realTimeFlow(RealTimeFlowBO realTimeFlowBO) throws Exception;
}
