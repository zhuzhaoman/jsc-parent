package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.pojo.PortHistoryOpticalPower;
import com.zzm.pojo.bo.PortHistoryOpticalPowerBO;
import com.zzm.service.PortHistoryOpticalPowerService;
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
@RequestMapping("/portHistoryOpticalPower")
public class PortHistoryOpticalPowerController {

    public static final Logger log = LoggerFactory.getLogger(PortHistoryOpticalPowerController.class);

    @Resource
    private PortHistoryOpticalPowerService portHistoryOpticalPowerService;

    /**
     * 根据日期范围（刻度天）查询历史流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @PostMapping("/dateRange")
    @SystemLog(description = "根据日期范围查询历史流量")
    public JSONResult getPortHistoryFlowByMonth(@RequestBody PortHistoryOpticalPowerBO portHistoryOpticalPowerBO) {

        Date startTime = portHistoryOpticalPowerBO.getStartTime();
        Date endTime = portHistoryOpticalPowerBO.getEndTime();

        long daysBetween = (endTime.getTime() - startTime.getTime() + 1000000) / (60 * 60 * 24 * 1000);

        List<PortHistoryOpticalPower> portHistoryOpticalPowerList = null;

        if (daysBetween <= 1) {
            portHistoryOpticalPowerList = portHistoryOpticalPowerService.getPortHistoryOpticalPowerByDay(portHistoryOpticalPowerBO);
        }else if (daysBetween >1 && daysBetween < 30) {
            portHistoryOpticalPowerList = portHistoryOpticalPowerService.getPortHistoryOpticalPowerByDayBetweenMonth(portHistoryOpticalPowerBO);
        } else if (daysBetween == 30) {
            portHistoryOpticalPowerList = portHistoryOpticalPowerService.getPortHistoryOpticalPowerByMonth(portHistoryOpticalPowerBO);
        } else if (daysBetween > 30){
            portHistoryOpticalPowerList = portHistoryOpticalPowerService.getPortHistoryOpticalPowerByQuarterRange(portHistoryOpticalPowerBO);
        }

        return JSONResult.ok(portHistoryOpticalPowerList);
    }

}

