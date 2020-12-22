package com.zzm.dao;

import com.zzm.pojo.TotalHistoryFlow;
import com.zzm.pojo.bo.TotalHistoryFlowBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
public interface TotalHistoryFlowRepository extends JpaRepository<TotalHistoryFlow, String>, JpaSpecificationExecutor<TotalHistoryFlow> {


    /**
     * 按月查询
     *
     * @param totalHistoryFlowBO
     * @return
     */
    @Query(value = "select * from total_history_flow where create_time between :#{#totalHistoryFlow.startTime} and :#{#totalHistoryFlow.endTime}" +
            " and DATE_FORMAT(create_time,'%i')%5 = 0 and domain_type=:#{#totalHistoryFlow.domainType} and domain_id=:#{#totalHistoryFlow.domainId} order by create_time asc", nativeQuery = true)
    public List<TotalHistoryFlow> getTotalHistoryFlowByDayBetweenMonth(@Param("totalHistoryFlow") TotalHistoryFlowBO totalHistoryFlowBO);


    /**
     * 按月查询
     *
     * @param totalHistoryFlowBO
     * @return
     */
    @Query(value = "select * from total_history_flow where create_time between :#{#totalHistoryFlow.startTime} and :#{#totalHistoryFlow.endTime}" +
            " and DATE_FORMAT(create_time,'%i')%30 = 0 and domain_type=:#{#totalHistoryFlow.domainType} and domain_id=:#{#totalHistoryFlow.domainId} order by create_time asc", nativeQuery = true)
    public List<TotalHistoryFlow> getTotalHistoryFlowByMonthRange(@Param("totalHistoryFlow") TotalHistoryFlowBO totalHistoryFlowBO);

    /**
     * 按季度查询
     *
     * @param totalHistoryFlowBO
     * @return
     */
    @Query(value = "select * from total_history_flow where create_time between :#{#totalHistoryFlow.startTime} and :#{#totalHistoryFlow.endTime}" +
            " and DATE_FORMAT(create_time,'%i')%60 = 0 and domain_type=:#{#totalHistoryFlow.domainType} and domain_id=:#{#totalHistoryFlow.domainId} order by create_time asc", nativeQuery = true)
    public List<TotalHistoryFlow> getTotalHistoryFlowByQuarterRange(@Param("totalHistoryFlow") TotalHistoryFlowBO totalHistoryFlowBO);

    /**
     * 根据传入时间删除数据
     *
     * @param minTime
     */
    @Modifying
    @Query(value = "delete from total_history_flow where create_time < ?1", nativeQuery = true)
    @Transactional
    public void deleteTotalHistoryFlowByCreateTimeRange(Date minTime);
}
