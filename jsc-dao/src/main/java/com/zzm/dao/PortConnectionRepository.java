package com.zzm.dao;

import com.zzm.pojo.ChassisInfo;
import com.zzm.pojo.Errors;
import com.zzm.pojo.vo.PortConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@SuppressWarnings("all")
public interface PortConnectionRepository extends JpaRepository<PortConnection, Integer>, JpaSpecificationExecutor<PortConnection> {



}
