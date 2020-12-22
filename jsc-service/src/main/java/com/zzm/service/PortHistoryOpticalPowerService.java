package com.zzm.service;

import com.zzm.pojo.PortHistoryOpticalPower;
import com.zzm.pojo.bo.PortHistoryOpticalPowerBO;
import java.util.Date;
import java.util.List;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 端口历史流量业务层接口
 */
public interface PortHistoryOpticalPowerService {

    /**
     * 根据时间范围查询流量信息，精度：1分钟
     *
     * @param portHistoryOpticalPowerBO 参数
     * @return
     */
    List<PortHistoryOpticalPower> getPortHistoryOpticalPowerByDay(PortHistoryOpticalPowerBO portHistoryOpticalPowerBO);

    /**
     * 根据时间范围查询流量信息：精度5分钟
     * @param portHistoryOpticalPowerBO
     * @return
     */
    List<PortHistoryOpticalPower> getPortHistoryOpticalPowerByDayBetweenMonth(PortHistoryOpticalPowerBO portHistoryOpticalPowerBO);

    /**
     * 根据一个月内时间查询流量信息，精度：30分钟
     *
     * @param portHistoryOpticalPowerBO 参数
     * @return
     */
    List<PortHistoryOpticalPower> getPortHistoryOpticalPowerByMonth(PortHistoryOpticalPowerBO portHistoryOpticalPowerBO);

    /**
     * 根据一个季度内时间查询流量信息，精度：60分钟
     *
     * @param portHistoryOpticalPowerBO 参数
     * @return
     */
    List<PortHistoryOpticalPower> getPortHistoryOpticalPowerByQuarterRange(PortHistoryOpticalPowerBO portHistoryOpticalPowerBO);

    /**
     * 删除小于某个时间点的数据
     *
     * @param minTime 时间
     */
    void deletePortHistoryOpticalPowerByCreateTimeRange(Date minTime);

}
