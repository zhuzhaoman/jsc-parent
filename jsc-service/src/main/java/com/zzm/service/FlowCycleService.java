package com.zzm.service;


import com.zzm.pojo.FlowCycle;
import com.zzm.pojo.bo.FlowCycleBO;

/**
 * @author：zhuzhaoman
 * @date：2020/8/25
 * @description：历史流量周期业务层接口
 */
public interface FlowCycleService {


    /**
     * 获得历史流量
     *
     * @return
     */
    FlowCycle getFlowCycle();

    /**
     * 修改历史流量存储周期
     *
     * @param flowCycleBo 参数
     */
    void updateFlowCycle(FlowCycleBO flowCycleBo);
}
