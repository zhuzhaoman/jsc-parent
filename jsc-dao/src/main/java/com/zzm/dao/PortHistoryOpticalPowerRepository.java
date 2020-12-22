package com.zzm.dao;

import com.zzm.pojo.PortHistoryOpticalPower;
import com.zzm.pojo.bo.PortHistoryOpticalPowerBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
public interface PortHistoryOpticalPowerRepository extends JpaRepository<PortHistoryOpticalPower, String>, JpaSpecificationExecutor<PortHistoryOpticalPower> {

    /**
     * 按月查询
     *
     * @param totalHistoryFlowBO
     * @return
     */
    @Query(value = "select * from port_history_optical_power where create_time between :#{#portHistoryOpticalPowerBO.startTime} and :#{#portHistoryOpticalPowerBO.endTime}" +
            " and DATE_FORMAT(create_time,'%i')%5 = 0 and domain_type=:#{#portHistoryOpticalPowerBO.domainType} and domain_id=:#{#portHistoryOpticalPowerBO.domainId} " +
            "and port_name=:#{#portHistoryOpticalPowerBO.portName} order by create_time asc", nativeQuery = true)
    public List<PortHistoryOpticalPower> getPortHistoryOpticalPowerByDayBetweenMonth(@Param("portHistoryOpticalPowerBO") PortHistoryOpticalPowerBO portHistoryOpticalPowerBO);

    /**
     * 按月查询
     *
     * @param totalHistoryFlowBO
     * @return
     */
    @Query(value = "select * from port_history_optical_power where create_time between :#{#portHistoryOpticalPowerBO.startTime} and :#{#portHistoryOpticalPowerBO.endTime}" +
            " and DATE_FORMAT(create_time,'%i')%30 = 0 and domain_type=:#{#portHistoryOpticalPowerBO.domainType} and domain_id=:#{#portHistoryOpticalPowerBO.domainId} " +
            "and port_name=:#{#portHistoryOpticalPowerBO.portName} order by create_time asc", nativeQuery = true)
    public List<PortHistoryOpticalPower> getPortHistoryOpticalPowerByMonthRange(@Param("portHistoryOpticalPowerBO") PortHistoryOpticalPowerBO portHistoryOpticalPowerBO);

    /**
     * 按季度查询
     *
     * @param totalHistoryFlowBO
     * @return
     */
    @Query(value = "select * from port_history_optical_power where create_time between :#{#portHistoryOpticalPowerBO.startTime} and :#{#portHistoryOpticalPowerBO.endTime}" +
            " and DATE_FORMAT(create_time,'%i')%60 = 0 and domain_type=:#{#portHistoryOpticalPowerBO.domainType} and domain_id=:#{#portHistoryOpticalPowerBO.domainId} " +
            "and port_name=:#{#portHistoryOpticalPowerBO.portName} order by create_time asc", nativeQuery = true)
    public List<PortHistoryOpticalPower> getPortHistoryOpticalPowerByQuarterRange(@Param("portHistoryOpticalPowerBO") PortHistoryOpticalPowerBO portHistoryOpticalPowerBO);

    /**
     * 根据传入时间删除数据
     *
     * @param minTime
     */
    @Modifying
    @Query(value = "delete from port_history_optical_power where create_time < ?1", nativeQuery = true)
    @Transactional
    public void deletePortHistoryFlowByCreateTimeRange(Date minTime);

}
