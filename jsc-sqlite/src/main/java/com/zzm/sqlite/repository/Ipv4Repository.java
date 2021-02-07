package com.zzm.sqlite.repository;

import com.zzm.sqlite.pojo.Ipv4RuleMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-25
 * @description:
 **/
public interface Ipv4Repository extends JpaRepository<Ipv4RuleMsg, Long>, JpaSpecificationExecutor<Ipv4RuleMsg> {
}
