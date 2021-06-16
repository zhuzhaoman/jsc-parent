package com.zzm.sqlite.pojo;

import javax.persistence.*;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
@Entity
@Table(name = "ETHER_MAC_RULE_MSG")
@IdClass(EtherMacRuleMsgPK.class)
public class EtherMacRuleMsg {

    private Long sendSlotId;
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private long profileId;
    private String srcMac;
    private String dstMac;
    private String srcMacMask;
    private String dstMacMask;
    private Long nextProtocol;
    private Long nextProtocolMask;
    private Long ruleType;
    private Long isStatic;
    private Long isHitStat;
    private Long isComPound;
    private Long priority;
    private Long inputPortGroupId;
    private Long setTime;
    private Long setIp;


    @Column(name = "NextProtocol")
    public Long getNextProtocol() {
        return nextProtocol;
    }

    public void setNextProtocol(Long nextProtocol) {
        this.nextProtocol = nextProtocol;
    }

    @Column(name = "NextProtocolMask")
    public Long getNextProtocolMask() {
        return nextProtocolMask;
    }

    public void setNextProtocolMask(Long nextProtocolMask) {
        this.nextProtocolMask = nextProtocolMask;
    }

    @Column(name = "SendSlotId")
    public Long getSendSlotId() {
        return sendSlotId;
    }

    public void setSendSlotId(Long sendSlotId) {
        this.sendSlotId = sendSlotId;
    }

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

    
    @Column(name = "SrcMac")
    public String getSrcMac() {
        return srcMac;
    }

    public void setSrcMac(String srcMac) {
        this.srcMac = srcMac;
    }

    
    @Column(name = "DstMac")
    public String getDstMac() {
        return dstMac;
    }

    public void setDstMac(String dstMac) {
        this.dstMac = dstMac;
    }

    
    @Column(name = "SrcMacMask")
    public String getSrcMacMask() {
        return srcMacMask;
    }

    public void setSrcMacMask(String srcMacMask) {
        this.srcMacMask = srcMacMask;
    }

    
    @Column(name = "DstMacMask")
    public String getDstMacMask() {
        return dstMacMask;
    }

    public void setDstMacMask(String dstMacMask) {
        this.dstMacMask = dstMacMask;
    }

    
    @Column(name = "RuleType")
    public Long getRuleType() {
        return ruleType;
    }

    public void setRuleType(Long ruleType) {
        this.ruleType = ruleType;
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

    
    @Column(name = "IsComPound")
    public Long getIsComPound() {
        return isComPound;
    }

    public void setIsComPound(Long isComPound) {
        this.isComPound = isComPound;
    }

    
    @Column(name = "Priority")
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    
    @Column(name = "InputPortGroupId")
    public Long getInputPortGroupId() {
        return inputPortGroupId;
    }

    public void setInputPortGroupId(Long inputPortGroupId) {
        this.inputPortGroupId = inputPortGroupId;
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
    
}
