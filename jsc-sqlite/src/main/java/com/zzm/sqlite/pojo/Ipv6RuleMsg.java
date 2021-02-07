package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "IPV6_RULE_MSG")
@IdClass(Ipv6RuleMsgPK.class)
public class Ipv6RuleMsg {
    
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private Long profileId;
    private Long maskFlag;
    private Long protocol;
    private Long protocolMask;
    private String srcIp;
    private String srcIpMask;
    private Long srcPort;
    private Long srcPortMask;
    private String dstIp;
    private String dstIpMask;
    private Long dstPort;
    private Long dstPortMask;
    private Long priority;
    private Long isStatic;
    private Long isHitStat;
    private Long isCompound;
    private Long combRuleType1;
    private Long combRuleType2;
    private Long combRuleType3;
    private Long combRuleType4;
    private Long combRuleType5;
    private Long combRuleId1;
    private Long combRuleId2;
    private Long combRuleId3;
    private Long combRuleId4;
    private Long combRuleId5;
    private Long setTime;
    private Long setIp;
    private Long matchType;
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
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    
    @Column(name = "MaskFlag")
    public Long getMaskFlag() {
        return maskFlag;
    }

    public void setMaskFlag(Long maskFlag) {
        this.maskFlag = maskFlag;
    }

    
    @Column(name = "Protocol")
    public Long getProtocol() {
        return protocol;
    }

    public void setProtocol(Long protocol) {
        this.protocol = protocol;
    }

    
    @Column(name = "ProtocolMask")
    public Long getProtocolMask() {
        return protocolMask;
    }

    public void setProtocolMask(Long protocolMask) {
        this.protocolMask = protocolMask;
    }

    
    @Column(name = "SrcIp")
    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    
    @Column(name = "SrcIpMask")
    public String getSrcIpMask() {
        return srcIpMask;
    }

    public void setSrcIpMask(String srcIpMask) {
        this.srcIpMask = srcIpMask;
    }

    
    @Column(name = "SrcPort")
    public Long getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(Long srcPort) {
        this.srcPort = srcPort;
    }

    
    @Column(name = "SrcPortMask")
    public Long getSrcPortMask() {
        return srcPortMask;
    }

    public void setSrcPortMask(Long srcPortMask) {
        this.srcPortMask = srcPortMask;
    }

    
    @Column(name = "DstIp")
    public String getDstIp() {
        return dstIp;
    }

    public void setDstIp(String dstIp) {
        this.dstIp = dstIp;
    }

    
    @Column(name = "DstIpMask")
    public String getDstIpMask() {
        return dstIpMask;
    }

    public void setDstIpMask(String dstIpMask) {
        this.dstIpMask = dstIpMask;
    }

    
    @Column(name = "DstPort")
    public Long getDstPort() {
        return dstPort;
    }

    public void setDstPort(Long dstPort) {
        this.dstPort = dstPort;
    }

    
    @Column(name = "DstPortMask")
    public Long getDstPortMask() {
        return dstPortMask;
    }

    public void setDstPortMask(Long dstPortMask) {
        this.dstPortMask = dstPortMask;
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

    
    @Column(name = "IsCompound")
    public Long getIsCompound() {
        return isCompound;
    }

    public void setIsCompound(Long isCompound) {
        this.isCompound = isCompound;
    }

    
    @Column(name = "CombRuleType1")
    public Long getCombRuleType1() {
        return combRuleType1;
    }

    public void setCombRuleType1(Long combRuleType1) {
        this.combRuleType1 = combRuleType1;
    }

    
    @Column(name = "CombRuleType2")
    public Long getCombRuleType2() {
        return combRuleType2;
    }

    public void setCombRuleType2(Long combRuleType2) {
        this.combRuleType2 = combRuleType2;
    }

    
    @Column(name = "CombRuleType3")
    public Long getCombRuleType3() {
        return combRuleType3;
    }

    public void setCombRuleType3(Long combRuleType3) {
        this.combRuleType3 = combRuleType3;
    }

    
    @Column(name = "CombRuleType4")
    public Long getCombRuleType4() {
        return combRuleType4;
    }

    public void setCombRuleType4(Long combRuleType4) {
        this.combRuleType4 = combRuleType4;
    }

    
    @Column(name = "CombRuleType5")
    public Long getCombRuleType5() {
        return combRuleType5;
    }

    public void setCombRuleType5(Long combRuleType5) {
        this.combRuleType5 = combRuleType5;
    }

    
    @Column(name = "CombRuleId1")
    public Long getCombRuleId1() {
        return combRuleId1;
    }

    public void setCombRuleId1(Long combRuleId1) {
        this.combRuleId1 = combRuleId1;
    }

    
    @Column(name = "CombRuleId2")
    public Long getCombRuleId2() {
        return combRuleId2;
    }

    public void setCombRuleId2(Long combRuleId2) {
        this.combRuleId2 = combRuleId2;
    }

    
    @Column(name = "CombRuleId3")
    public Long getCombRuleId3() {
        return combRuleId3;
    }

    public void setCombRuleId3(Long combRuleId3) {
        this.combRuleId3 = combRuleId3;
    }

    
    @Column(name = "CombRuleId4")
    public Long getCombRuleId4() {
        return combRuleId4;
    }

    public void setCombRuleId4(Long combRuleId4) {
        this.combRuleId4 = combRuleId4;
    }

    
    @Column(name = "CombRuleId5")
    public Long getCombRuleId5() {
        return combRuleId5;
    }

    public void setCombRuleId5(Long combRuleId5) {
        this.combRuleId5 = combRuleId5;
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

    
    @Column(name = "MatchType")
    public Long getMatchType() {
        return matchType;
    }

    public void setMatchType(Long matchType) {
        this.matchType = matchType;
    }

    
    @Column(name = "InputPortGroupId")
    public Long getInputPortGroupId() {
        return inputPortGroupId;
    }

    public void setInputPortGroupId(Long inputPortGroupId) {
        this.inputPortGroupId = inputPortGroupId;
    }

}
