package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.pojo.TotalHistoryFlow;
import com.zzm.pojo.bo.TotalHistoryFlowBO;
import com.zzm.service.TotalHistoryFlowService;
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
@RequestMapping("/totalHistoryFlow")
public class TotalHistoryFlowController {

    public static final Logger log = LoggerFactory.getLogger(TotalHistoryFlowController.class);

    @Resource
    private TotalHistoryFlowService totalHistoryFlowService;

    /**
     * 根据天获取历史流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @PostMapping("/day")
    @SystemLog(description = "根据日期范围查询历史流量")
    public JSONResult getTotalHistoryFlowByDay(@RequestBody TotalHistoryFlowBO totalHistoryFlowBO) {
        List<TotalHistoryFlow> totalHistoryFlowByDay = totalHistoryFlowService.getTotalHistoryFlowByDay(totalHistoryFlowBO);
        return JSONResult.ok(totalHistoryFlowByDay);
    }

    /**
     * 根据日期范围（刻度天）查询历史流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @PostMapping("/dateRange")
    @SystemLog(description = "根据日期范围查询历史流量")
    public JSONResult getTotalHistoryFlowByMonth(@RequestBody TotalHistoryFlowBO totalHistoryFlowBO) {

        Date startTime = totalHistoryFlowBO.getStartTime();
        Date endTime = totalHistoryFlowBO.getEndTime();

        long daysBetween = (endTime.getTime() - startTime.getTime() + 1000000) / (60 * 60 * 24 * 1000);

        List<TotalHistoryFlow> totalHistoryFlowList = null;

        if (daysBetween <= 1) {
            totalHistoryFlowList = totalHistoryFlowService.getTotalHistoryFlowByDay(totalHistoryFlowBO);
        }else if (daysBetween >1 && daysBetween < 30) {
            totalHistoryFlowList = totalHistoryFlowService.getTotalHistoryFlowByDayBetweenMonth(totalHistoryFlowBO);
        } else if (daysBetween == 30) {
            totalHistoryFlowList = totalHistoryFlowService.getTotalHistoryFlowByMonth(totalHistoryFlowBO);
        } else if (daysBetween > 30){
            totalHistoryFlowList = totalHistoryFlowService.getTotalHistoryFlowByQuarterRange(totalHistoryFlowBO);
        }

        return JSONResult.ok(totalHistoryFlowList);
    }

    /**
     * 根据季度查询历史流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @PostMapping("/quarter")
    @SystemLog(description = "根据日期范围查询历史流量")
    public JSONResult getTotalHistoryFlowByQuarterRange(@RequestBody TotalHistoryFlowBO totalHistoryFlowBO) {
        List<TotalHistoryFlow> totalHistoryFlowList = totalHistoryFlowService.getTotalHistoryFlowByQuarterRange(totalHistoryFlowBO);
        return JSONResult.ok(totalHistoryFlowList);
    }

}
