package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "MAC_RULE_MSG")
@IdClass(MacRuleMsgPK.class)
public class MacRuleMsg {
    
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private Long profileId;
    private Long portGroupId;
    private Long portGroupMask;
    private Long dirType;
    private Long dirMask;
    private Long hashValue;
    private Long hashMask;
    private Long macRule;
    private Long macRuleMask;
    private Long slotId;
    private Long slotMask;
    private Long portId;
    private Long portMask;
    private Long deviceId;
    private Long deviceMask;
    private Long priority;
    private Long isStatic;
    private Long isHitStat;
    private Long setTime;
    private Long setIp;
    
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
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    
    @Column(name = "PortGroupId")
    public Long getPortGroupId() {
        return portGroupId;
    }

    public void setPortGroupId(Long portGroupId) {
        this.portGroupId = portGroupId;
    }

    
    @Column(name = "PortGroupMask")
    public Long getPortGroupMask() {
        return portGroupMask;
    }

    public void setPortGroupMask(Long portGroupMask) {
        this.portGroupMask = portGroupMask;
    }

    
    @Column(name = "DirType")
    public Long getDirType() {
        return dirType;
    }

    public void setDirType(Long dirType) {
        this.dirType = dirType;
    }

    
    @Column(name = "DirMask")
    public Long getDirMask() {
        return dirMask;
    }

    public void setDirMask(Long dirMask) {
        this.dirMask = dirMask;
    }

    
    @Column(name = "HashValue")
    public Long getHashValue() {
        return hashValue;
    }

    public void setHashValue(Long hashValue) {
        this.hashValue = hashValue;
    }

    
    @Column(name = "HashMask")
    public Long getHashMask() {
        return hashMask;
    }

    public void setHashMask(Long hashMask) {
        this.hashMask = hashMask;
    }

    
    @Column(name = "MacRule")
    public Long getMacRule() {
        return macRule;
    }

    public void setMacRule(Long macRule) {
        this.macRule = macRule;
    }

    
    @Column(name = "MacRuleMask")
    public Long getMacRuleMask() {
        return macRuleMask;
    }

    public void setMacRuleMask(Long macRuleMask) {
        this.macRuleMask = macRuleMask;
    }

    
    @Column(name = "SlotId")
    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    
    @Column(name = "SlotMask")
    public Long getSlotMask() {
        return slotMask;
    }

    public void setSlotMask(Long slotMask) {
        this.slotMask = slotMask;
    }

    
    @Column(name = "PortId")
    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    
    @Column(name = "PortMask")
    public Long getPortMask() {
        return portMask;
    }

    public void setPortMask(Long portMask) {
        this.portMask = portMask;
    }

    
    @Column(name = "DeviceId")
    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    
    @Column(name = "DeviceMask")
    public Long getDeviceMask() {
        return deviceMask;
    }

    public void setDeviceMask(Long deviceMask) {
        this.deviceMask = deviceMask;
    }

    
    @Column(name = "Priority")
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
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
