package com.zzm.service;

import com.zzm.pojo.TotalHistoryFlow;
import com.zzm.pojo.bo.TotalHistoryFlowBO;

import java.util.Date;
import java.util.List;

public interface TotalHistoryFlowService {

    /**
     * 根据时间范围查询流量信息，精度：1分钟
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    List<TotalHistoryFlow> getTotalHistoryFlowByDay(TotalHistoryFlowBO totalHistoryFlowBO);

    /**
     * 根据时间范围查询流量信息，精度：5分钟
     * @param totalHistoryFlowBO
     * @return
     */
    List<TotalHistoryFlow> getTotalHistoryFlowByDayBetweenMonth(TotalHistoryFlowBO totalHistoryFlowBO);

    /**
     * 根据一个月内时间查询流量信息，精度：30分钟
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    List<TotalHistoryFlow> getTotalHistoryFlowByMonth(TotalHistoryFlowBO totalHistoryFlowBO);

    /**
     * 根据一个季度内时间查询流量信息，精度：60分钟
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    List<TotalHistoryFlow> getTotalHistoryFlowByQuarterRange(TotalHistoryFlowBO totalHistoryFlowBO);


    /**
     * 删除小于某个时间点的数据
     *
     * @param minTime 时间
     */
    void deleteTotalHistoryFlowByCreateTimeRange(Date minTime);

}
