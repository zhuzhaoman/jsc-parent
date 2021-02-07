package com.zzm.sqlite.repository;

import com.zzm.sqlite.pojo.UrlRuleMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-25
 * @description:
 **/
public interface UrlRepository extends JpaRepository<UrlRuleMsg, Long>,
        JpaSpecificationExecutor<UrlRuleMsg> {
}
