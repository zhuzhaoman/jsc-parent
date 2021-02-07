package com.zzm.sqlite.repository;

import com.zzm.sqlite.pojo.VlanRuleMsg;
import com.zzm.sqlite.pojo.WindowRuleMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-25
 * @description:
 **/
public interface WindowRepository extends JpaRepository<WindowRuleMsg, Long>,
        JpaSpecificationExecutor<WindowRuleMsg> {
}
