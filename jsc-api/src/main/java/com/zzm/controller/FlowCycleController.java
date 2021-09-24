package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.pojo.FlowCycle;
import com.zzm.pojo.bo.FlowCycleBO;
import com.zzm.service.FlowCycleService;
import com.zzm.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 流量周期
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/flowCycle")
public class FlowCycleController {

    public static final Logger log = LoggerFactory.getLogger(FlowCycleController.class);

    @Resource
    private FlowCycleService flowCycleService;

    /**
     * 获取历史流量存储周期
     *
     * @return
     */
    @GetMapping("/getCycle")
    public JSONResult getFlowCycle() {
        FlowCycle flowCycle = flowCycleService.getFlowCycle();
        return JSONResult.ok(flowCycle);
    }


    /**
     * 修改历史流量存储周期
     *
     * @param flowCycleBo 参数
     * @return
     */
    @PostMapping("/updateCycle")
    @SystemLog(description = "修改历史流量存储周期")
    public JSONResult updateFlowCycle(@RequestBody FlowCycleBO flowCycleBo) {
        flowCycleService.updateFlowCycle(flowCycleBo);
        return JSONResult.ok();
    }

}
