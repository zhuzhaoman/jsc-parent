package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "TCPFLAG_RULE_MSG")
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

    
    @Column(name = "ProfileID")
    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    
    @Column(name = "URGFlag")
    public Long getUrgFlag() {
        return urgFlag;
    }

    public void setUrgFlag(Long urgFlag) {
        this.urgFlag = urgFlag;
    }

    
    @Column(name = "ACKFlag")
    public Long getAckFlag() {
        return ackFlag;
    }

    public void setAckFlag(Long ackFlag) {
        this.ackFlag = ackFlag;
    }

    
    @Column(name = "PSHFlag")
    public Long getPshFlag() {
        return pshFlag;
    }

    public void setPshFlag(Long pshFlag) {
        this.pshFlag = pshFlag;
    }

    
    @Column(name = "RSTFlag")
    public Long getRstFlag() {
        return rstFlag;
    }

    public void setRstFlag(Long rstFlag) {
        this.rstFlag = rstFlag;
    }

    
    @Column(name = "SYNFlag")
    public Long getSynFlag() {
        return synFlag;
    }

    public void setSynFlag(Long synFlag) {
        this.synFlag = synFlag;
    }

    
    @Column(name = "FINFlag")
    public Long getFinFlag() {
        return finFlag;
    }

    public void setFinFlag(Long finFlag) {
        this.finFlag = finFlag;
    }

    
    @Column(name = "IsStatic")
    public Long getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(Long isStatic) {
        this.isStatic = isStatic;
    }

    
    @Column(name = "IsHitStat")
    public Long getIsHitStat() {
        return isHitStat;
    }

    public void setIsHitStat(Long isHitStat) {
        this.isHitStat = isHitStat;
    }

    
    @Column(name = "LoadLen")
    public Long getLoadLen() {
        return loadLen;
    }

    public void setLoadLen(Long loadLen) {
        this.loadLen = loadLen;
    }

    
    @Column(name = "IsAny")
    public Long getIsAny() {
        return isAny;
    }

    public void setIsAny(Long isAny) {
        this.isAny = isAny;
    }

    
    @Column(name = "Priority")
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    
    @Column(name = "SetTime")
    public Long getSetTime() {
        return setTime;
    }

    public void setSetTime(Long setTime) {
        this.setTime = setTime;
    }

    
    @Column(name = "SetIp")
    public Long getSetIp() {
        return setIp;
    }

    public void setSetIp(Long setIp) {
        this.setIp = setIp;
    }

    
    @Column(name = "InputPortGroupId")
    public Long getInputPortGroupId() {
        return inputPortGroupId;
    }

    public void setInputPortGroupId(Long inputPortGroupId) {
        this.inputPortGroupId = inputPortGroupId;
    }


}
