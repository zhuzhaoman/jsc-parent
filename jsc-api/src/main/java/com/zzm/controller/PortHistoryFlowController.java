package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.pojo.PortHistoryFlow;
import com.zzm.pojo.bo.PortHistoryFlowBO;
import com.zzm.service.PortHistoryFlowService;
import com.zzm.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author：zhuzhaoman
 * @date：2020/8/25
 * @description：历史流量查询
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/portHistoryFlow")
public class PortHistoryFlowController {

    public static final Logger log = LoggerFactory.getLogger(PortHistoryFlowController.class);

    @Resource
    private PortHistoryFlowService portHistoryFlowService;

    /**
     * 根据天获取历史流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @PostMapping("/day")
    @SystemLog(description = "根据日期范围查询历史流量")
    public JSONResult getPortHistoryFlowByDay(@RequestBody PortHistoryFlowBO portHistoryFlowBO) {
        List<PortHistoryFlow> portHistoryFlowByDay = portHistoryFlowService.getPortHistoryFlowByDay(portHistoryFlowBO);
        return JSONResult.ok(portHistoryFlowByDay);
    }

    /**
     * 根据日期范围（刻度天）查询历史流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @PostMapping("/dateRange")
    @SystemLog(description = "根据日期范围查询历史流量")
    public JSONResult getPortHistoryFlowByMonth(@RequestBody PortHistoryFlowBO portHistoryFlowBO) {

        Date startTime = portHistoryFlowBO.getStartTime();
        Date endTime = portHistoryFlowBO.getEndTime();

        long daysBetween = (endTime.getTime() - startTime.getTime() + 1000000) / (60 * 60 * 24 * 1000);

        List<PortHistoryFlow> portHistoryFlowList = null;

        if (daysBetween <= 1) {
            portHistoryFlowList = portHistoryFlowService.getPortHistoryFlowByDay(portHistoryFlowBO);
        }else if (daysBetween >1 && daysBetween < 30) {
            portHistoryFlowList = portHistoryFlowService.getPortHistoryFlowByDayBetweenMonth(portHistoryFlowBO);
        } else if (daysBetween == 30) {
            portHistoryFlowList = portHistoryFlowService.getPortHistoryFlowByMonth(portHistoryFlowBO);
        } else if (daysBetween > 30){
            portHistoryFlowList = portHistoryFlowService.getPortHistoryFlowByQuarterRange(portHistoryFlowBO);
        }

        return JSONResult.ok(portHistoryFlowList);
    }

    /**
     * 根据季度查询历史流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @PostMapping("/quarter")
    @SystemLog(description = "根据日期范围查询历史流量")
    public JSONResult getPortHistoryFlowByQuarterRange(@RequestBody PortHistoryFlowBO portHistoryFlowBO) {
        List<PortHistoryFlow> portHistoryFlowByQuarterRange = portHistoryFlowService.getPortHistoryFlowByQuarterRange(portHistoryFlowBO);
        return JSONResult.ok(portHistoryFlowByQuarterRange);
    }
}

