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
public class ImsiRuleMsgPK implements Serializable {

    private long domainId;
    private long ruleId;

    @Id
    @Column(name = "DomainId")
    public long getDomainId() {
        return domainId;
    }

    public void setDomainId(long domainId) {
        this.domainId = domainId;
    }

    @Id
    @Column(name = "RuleId")
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
        ImsiRuleMsgPK that = (ImsiRuleMsgPK) o;
        return domainId == that.domainId &&
                ruleId == that.ruleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainId, ruleId);
    }
}
