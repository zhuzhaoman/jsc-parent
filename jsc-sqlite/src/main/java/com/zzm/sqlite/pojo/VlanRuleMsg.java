package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "VLAN_RULE_MSG")
@IdClass(VlanRuleMsgPK.class)
public class VlanRuleMsg {

    private Long sendSlotId;
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private long profileId;
    private Long vlanType1;
    private Long vlanType2;
    private Long vlanType3;
    private Long vlanType4;
    private Long vlanId1;
    private Long vlanId2;
    private Long vlanId3;
    private Long vlanId4;
    private Long vlanIdMask1;
    private Long vlanIdMask2;
    private Long vlanIdMask3;
    private Long vlanIdMask4;
    private Long isStatic;
    private Long isHitStat;
    private Long priority;
    private Long setTime;
    private Long setIp;
    private Long inputPortGroupId;

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

    
    @Column(name = "VlanType1")
    public Long getVlanType1() {
        return vlanType1;
    }

    public void setVlanType1(Long vlanType1) {
        this.vlanType1 = vlanType1;
    }

    
    @Column(name = "VlanType2")
    public Long getVlanType2() {
        return vlanType2;
    }

    public void setVlanType2(Long vlanType2) {
        this.vlanType2 = vlanType2;
    }

    
    @Column(name = "VlanType3")
    public Long getVlanType3() {
        return vlanType3;
    }

    public void setVlanType3(Long vlanType3) {
        this.vlanType3 = vlanType3;
    }

    
    @Column(name = "VlanType4")
    public Long getVlanType4() {
        return vlanType4;
    }

    public void setVlanType4(Long vlanType4) {
        this.vlanType4 = vlanType4;
    }

    
    @Column(name = "VlanId1")
    public Long getVlanId1() {
        return vlanId1;
    }

    public void setVlanId1(Long vlanId1) {
        this.vlanId1 = vlanId1;
    }

    
    @Column(name = "VlanId2")
    public Long getVlanId2() {
        return vlanId2;
    }

    public void setVlanId2(Long vlanId2) {
        this.vlanId2 = vlanId2;
    }

    
    @Column(name = "VlanId3")
    public Long getVlanId3() {
        return vlanId3;
    }

    public void setVlanId3(Long vlanId3) {
        this.vlanId3 = vlanId3;
    }

    
    @Column(name = "VlanId4")
    public Long getVlanId4() {
        return vlanId4;
    }

    public void setVlanId4(Long vlanId4) {
        this.vlanId4 = vlanId4;
    }

    
    @Column(name = "VlanIdMask1")
    public Long getVlanIdMask1() {
        return vlanIdMask1;
    }

    public void setVlanIdMask1(Long vlanIdMask1) {
        this.vlanIdMask1 = vlanIdMask1;
    }

    
    @Column(name = "VlanIdMask2")
    public Long getVlanIdMask2() {
        return vlanIdMask2;
    }

    public void setVlanIdMask2(Long vlanIdMask2) {
        this.vlanIdMask2 = vlanIdMask2;
    }

    
    @Column(name = "VlanIdMask3")
    public Long getVlanIdMask3() {
        return vlanIdMask3;
    }

    public void setVlanIdMask3(Long vlanIdMask3) {
        this.vlanIdMask3 = vlanIdMask3;
    }

    
    @Column(name = "VlanIdMask4")
    public Long getVlanIdMask4() {
        return vlanIdMask4;
    }

    public void setVlanIdMask4(Long vlanIdMask4) {
        this.vlanIdMask4 = vlanIdMask4;
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
