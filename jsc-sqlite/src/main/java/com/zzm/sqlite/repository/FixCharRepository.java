package com.zzm.sqlite.repository;

import com.zzm.sqlite.pojo.FixedloccharcodeRuleMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-25
 * @description:
 **/
public interface FixCharRepository extends JpaRepository<FixedloccharcodeRuleMsg, Long>,
        JpaSpecificationExecutor<FixedloccharcodeRuleMsg> {
}
