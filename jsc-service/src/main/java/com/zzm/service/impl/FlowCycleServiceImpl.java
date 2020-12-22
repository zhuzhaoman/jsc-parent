package com.zzm.service.impl;

import com.zzm.dao.FlowCycleRepository;
import com.zzm.pojo.FlowCycle;
import com.zzm.pojo.bo.FlowCycleBO;
import com.zzm.service.FlowCycleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author：zhuzhaoman
 * @date：2020/8/25 0025
 * @description：历史流量周期业务层实现
 */
@SuppressWarnings("all")
@Service
public class FlowCycleServiceImpl implements FlowCycleService {

    public static final Logger log = LoggerFactory.getLogger(FlowCycleServiceImpl.class);

    @Resource
    private FlowCycleRepository flowCycleRepository;

    /**
     * 获得历史流量存储周期
     *
     * @return
     */
    public FlowCycle getFlowCycle() {

        List<FlowCycle> flowCycleList = flowCycleRepository.findAll();

        if (flowCycleList.size() <= 0) {
            return null;
        }

        return flowCycleList.get(0);
    }

    /**
     * 修改历史流量存储周期
     *
     * @param flowCycleBo 惨呼
     */
    @Override
    @Transactional
    public void updateFlowCycle(FlowCycleBO flowCycleBo) {

        FlowCycle flowCycle = new FlowCycle();
        BeanUtils.copyProperties(flowCycleBo, flowCycle);

        flowCycleRepository.save(flowCycle);
    }
}
