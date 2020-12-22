package com.zzm.sqlite.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
public class Ipv6RuleMsgPK implements Serializable {
    private long domainId;
    private long ruleId;

    @Column(name = "DomainId")
    @Id
    public long getDomainId() {
        return domainId;
    }

    public void setDomainId(long domainId) {
        this.domainId = domainId;
    }

    @Column(name = "RuleId")
    @Id
    public long getRuleId() {
        return ruleId;
    }

    public void setRuleId(long ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ipv6RuleMsgPK that = (Ipv6RuleMsgPK) o;
        return domainId == that.domainId &&
                ruleId == that.ruleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainId, ruleId);
    }
}
