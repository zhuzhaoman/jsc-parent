package com.zzm.dao;

import com.zzm.pojo.ChassisInfo;
import com.zzm.pojo.Errors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@SuppressWarnings("all")
public interface ChassisInfoRepository extends JpaRepository<ChassisInfo, Integer>, JpaSpecificationExecutor<ChassisInfo> {



}
