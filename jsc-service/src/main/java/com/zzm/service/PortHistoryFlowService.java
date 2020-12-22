package com.zzm.service;

import com.zzm.pojo.PortHistoryFlow;
import com.zzm.pojo.bo.PortHistoryFlowBO;
import java.util.Date;
import java.util.List;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 端口历史流量业务层接口
 */
public interface PortHistoryFlowService {

    /**
     * 根据时间范围查询流量信息，精度：1分钟
     *
     * @param portHistoryFlowBO 参数
     * @return
     */
    List<PortHistoryFlow> getPortHistoryFlowByDay(PortHistoryFlowBO portHistoryFlowBO);

    /**
     * 根据时间范围查询流量信息：精度5分钟
     * @param portHistoryFlowBO
     * @return
     */
    List<PortHistoryFlow> getPortHistoryFlowByDayBetweenMonth(PortHistoryFlowBO portHistoryFlowBO);

    /**
     * 根据一个月内时间查询流量信息，精度：30分钟
     *
     * @param portHistoryFlowBO 参数
     * @return
     */
    List<PortHistoryFlow> getPortHistoryFlowByMonth(PortHistoryFlowBO portHistoryFlowBO);

    /**
     * 根据一个季度内时间查询流量信息，精度：60分钟
     *
     * @param portHistoryFlowBO 参数
     * @return
     */
    List<PortHistoryFlow> getPortHistoryFlowByQuarterRange(PortHistoryFlowBO portHistoryFlowBO);

    /**
     * 删除小于某个时间点的数据
     *
     * @param minTime 时间
     */
    void deletePortHistoryFlowByCreateTimeRange(Date minTime);

}
