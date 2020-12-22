package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "TCPFLAG_RULE_MSG", schema = "main", catalog = "")
@IdClass(TcpflagRuleMsgPK.class)
public class TcpflagRuleMsg {
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private long profileId;
    private Long urgFlag;
    private Long ackFlag;
    private Long pshFlag;
    private Long rstFlag;
    private Long synFlag;
    private Long finFlag;
    private Long isStatic;
    private Long isHitStat;
    private Long loadLen;
    private Long isAny;
    private Long priority;
    private Long setTime;
    private Long setIp;
    private Long inputPortGroupId;

    @Basic
    @Column(name = "RuleSource")
    public Long getRuleSource() {
        return ruleSource;
    }

    public void setRuleSource(Long ruleSource) {
        this.ruleSource = ruleSource;
    }

    @Id
    @Column(name = "DomainId")
    public long getDomainId() {
        return domainId;
    }

    public void setDomainId(long domainId) {
        this.domainId = domainId;
    }

    @Basic
    @Column(name = "UserId")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "RuleId")
    public long getRuleId() {
        return ruleId;
    }

    public void setRuleId(long ruleId) {
        this.ruleId = ruleId;
    }

    @Basic
    @Column(name = "ProfileID")
    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "URGFlag")
    public Long getUrgFlag() {
        return urgFlag;
    }

    public void setUrgFlag(Long urgFlag) {
        this.urgFlag = urgFlag;
    }

    @Basic
    @Column(name = "ACKFlag")
    public Long getAckFlag() {
        return ackFlag;
    }

    public void setAckFlag(Long ackFlag) {
        this.ackFlag = ackFlag;
    }

    @Basic
    @Column(name = "PSHFlag")
    public Long getPshFlag() {
        return pshFlag;
    }

    public void setPshFlag(Long pshFlag) {
        this.pshFlag = pshFlag;
    }

    @Basic
    @Column(name = "RSTFlag")
    public Long getRstFlag() {
        return rstFlag;
    }

    public void setRstFlag(Long rstFlag) {
        this.rstFlag = rstFlag;
    }

    @Basic
    @Column(name = "SYNFlag")
    public Long getSynFlag() {
        return synFlag;
    }

    public void setSynFlag(Long synFlag) {
        this.synFlag = synFlag;
    }

    @Basic
    @Column(name = "FINFlag")
    public Long getFinFlag() {
        return finFlag;
    }

    public void setFinFlag(Long finFlag) {
        this.finFlag = finFlag;
    }

    @Basic
    @Column(name = "IsStatic")
    public Long getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(Long isStatic) {
        this.isStatic = isStatic;
    }

    @Basic
    @Column(name = "IsHitStat")
    public Long getIsHitStat() {
        return isHitStat;
    }

    public void setIsHitStat(Long isHitStat) {
        this.isHitStat = isHitStat;
    }

    @Basic
    @Column(name = "LoadLen")
    public Long getLoadLen() {
        return loadLen;
    }

    public void setLoadLen(Long loadLen) {
        this.loadLen = loadLen;
    }

    @Basic
    @Column(name = "IsAny")
    public Long getIsAny() {
        return isAny;
    }

    public void setIsAny(Long isAny) {
        this.isAny = isAny;
    }

    @Basic
    @Column(name = "Priority")
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "SetTime")
    public Long getSetTime() {
        return setTime;
    }

    public void setSetTime(Long setTime) {
        this.setTime = setTime;
    }

    @Basic
    @Column(name = "SetIp")
    public Long getSetIp() {
        return setIp;
    }

    public void setSetIp(Long setIp) {
        this.setIp = setIp;
    }

    @Basic
    @Column(name = "InputPortGroupId")
    public Long getInputPortGroupId() {
        return inputPortGroupId;
    }

    public void setInputPortGroupId(Long inputPortGroupId) {
        this.inputPortGroupId = inputPortGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TcpflagRuleMsg that = (TcpflagRuleMsg) o;
        return domainId == that.domainId &&
                userId == that.userId &&
                ruleId == that.ruleId &&
                profileId == that.profileId &&
                Objects.equals(ruleSource, that.ruleSource) &&
                Objects.equals(urgFlag, that.urgFlag) &&
                Objects.equals(ackFlag, that.ackFlag) &&
                Objects.equals(pshFlag, that.pshFlag) &&
                Objects.equals(rstFlag, that.rstFlag) &&
                Objects.equals(synFlag, that.synFlag) &&
                Objects.equals(finFlag, that.finFlag) &&
                Objects.equals(isStatic, that.isStatic) &&
                Objects.equals(isHitStat, that.isHitStat) &&
                Objects.equals(loadLen, that.loadLen) &&
                Objects.equals(isAny, that.isAny) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(setTime, that.setTime) &&
                Objects.equals(setIp, that.setIp) &&
                Objects.equals(inputPortGroupId, that.inputPortGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleSource, domainId, userId, ruleId, profileId, urgFlag, ackFlag, pshFlag, rstFlag, synFlag, finFlag, isStatic, isHitStat, loadLen, isAny, priority, setTime, setIp, inputPortGroupId);
    }
}
