package com.zzm.dao;

import com.zzm.pojo.PortHistoryFlow;
import com.zzm.pojo.bo.PortHistoryFlowBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
public interface PortHistoryFlowRepository extends JpaRepository<PortHistoryFlow, String>, JpaSpecificationExecutor<PortHistoryFlow> {

    /**
     * 按月查询
     *
     * @param totalHistoryFlowBO
     * @return
     */
    @Query(value = "select * from port_history_flow where create_time between :#{#portHistoryFlow.startTime} and :#{#portHistoryFlow.endTime}" +
            " and DATE_FORMAT(create_time,'%i')%5 = 0 and domain_type=:#{#portHistoryFlow.domainType} and domain_id=:#{#portHistoryFlow.domainId} " +
            "and port_name=:#{#portHistoryFlow.portName} order by create_time asc", nativeQuery = true)
    public List<PortHistoryFlow> getPortHistoryFlowByDayBetweenMonth(@Param("portHistoryFlow") PortHistoryFlowBO portHistoryFlowBO);

    /**
     * 按月查询
     *
     * @param totalHistoryFlowBO
     * @return
     */
    @Query(value = "select * from port_history_flow where create_time between :#{#portHistoryFlow.startTime} and :#{#portHistoryFlow.endTime}" +
            " and DATE_FORMAT(create_time,'%i')%30 = 0 and domain_type=:#{#portHistoryFlow.domainType} and domain_id=:#{#portHistoryFlow.domainId} " +
            "and port_name=:#{#portHistoryFlow.portName} order by create_time asc", nativeQuery = true)
    public List<PortHistoryFlow> getPortHistoryFlowByMonthRange(@Param("portHistoryFlow") PortHistoryFlowBO portHistoryFlowBO);

    /**
     * 按季度查询
     *
     * @param totalHistoryFlowBO
     * @return
     */
    @Query(value = "select * from port_history_flow where create_time between :#{#portHistoryFlow.startTime} and :#{#portHistoryFlow.endTime}" +
            " and DATE_FORMAT(create_time,'%i')%60 = 0 and domain_type=:#{#portHistoryFlow.domainType} and domain_id=:#{#portHistoryFlow.domainId} " +
            "and port_name=:#{#portHistoryFlow.portName} order by create_time asc", nativeQuery = true)
    public List<PortHistoryFlow> getPortHistoryFlowByQuarterRange(@Param("portHistoryFlow") PortHistoryFlowBO portHistoryFlowBO);

    /**
     * 根据传入时间删除数据
     *
     * @param minTime
     */
    @Modifying
    @Query(value = "delete from port_history_flow where create_time < ?1", nativeQuery = true)
    @Transactional
    public void deletePortHistoryFlowByCreateTimeRange(Date minTime);

}
