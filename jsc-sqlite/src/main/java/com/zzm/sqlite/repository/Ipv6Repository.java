package com.zzm.sqlite.repository;

import com.zzm.sqlite.pojo.Ipv6RuleMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-25
 * @description:
 **/
public interface Ipv6Repository extends JpaRepository<Ipv6RuleMsg, Long>, JpaSpecificationExecutor<Ipv6RuleMsg> {
}
