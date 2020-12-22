package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "ETHER_MAC_RULE_MSG", schema = "main", catalog = "")
@IdClass(EtherMacRuleMsgPK.class)
public class EtherMacRuleMsg {
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private long profileId;
    private Object srcMac;
    private Object dstMac;
    private Long ruleType;
    private Long isStatic;
    private Long isHitStat;
    private Long isComPound;
    private Long priority;
    private Long inputPortGroupId;
    private Long setTime;
    private Long setIp;

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
    @Column(name = "SrcMac")
    public Object getSrcMac() {
        return srcMac;
    }

    public void setSrcMac(Object srcMac) {
        this.srcMac = srcMac;
    }

    @Basic
    @Column(name = "DstMac")
    public Object getDstMac() {
        return dstMac;
    }

    public void setDstMac(Object dstMac) {
        this.dstMac = dstMac;
    }

    @Basic
    @Column(name = "RuleType")
    public Long getRuleType() {
        return ruleType;
    }

    public void setRuleType(Long ruleType) {
        this.ruleType = ruleType;
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
    @Column(name = "IsComPound")
    public Long getIsComPound() {
        return isComPound;
    }

    public void setIsComPound(Long isComPound) {
        this.isComPound = isComPound;
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
    @Column(name = "InputPortGroupId")
    public Long getInputPortGroupId() {
        return inputPortGroupId;
    }

    public void setInputPortGroupId(Long inputPortGroupId) {
        this.inputPortGroupId = inputPortGroupId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtherMacRuleMsg that = (EtherMacRuleMsg) o;
        return domainId == that.domainId &&
                userId == that.userId &&
                ruleId == that.ruleId &&
                profileId == that.profileId &&
                Objects.equals(ruleSource, that.ruleSource) &&
                Objects.equals(srcMac, that.srcMac) &&
                Objects.equals(dstMac, that.dstMac) &&
                Objects.equals(ruleType, that.ruleType) &&
                Objects.equals(isStatic, that.isStatic) &&
                Objects.equals(isHitStat, that.isHitStat) &&
                Objects.equals(isComPound, that.isComPound) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(inputPortGroupId, that.inputPortGroupId) &&
                Objects.equals(setTime, that.setTime) &&
                Objects.equals(setIp, that.setIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleSource, domainId, userId, ruleId, profileId, srcMac, dstMac, ruleType, isStatic, isHitStat, isComPound, priority, inputPortGroupId, setTime, setIp);
    }
}
