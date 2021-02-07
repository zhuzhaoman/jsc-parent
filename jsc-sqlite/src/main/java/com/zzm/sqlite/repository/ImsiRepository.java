package com.zzm.sqlite.repository;

import com.zzm.sqlite.pojo.ImsiRuleMsg;
import com.zzm.sqlite.pojo.VlanRuleMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-25
 * @description:
 **/
public interface ImsiRepository extends JpaRepository<ImsiRuleMsg, Long>,
        JpaSpecificationExecutor<ImsiRuleMsg> {
}
