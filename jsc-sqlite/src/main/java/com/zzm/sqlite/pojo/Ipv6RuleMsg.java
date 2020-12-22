package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "IPV6_RULE_MSG", schema = "main", catalog = "")
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
    private Object srcIp;
    private Object srcIpMask;
    private Long srcPort;
    private Long srcPortMask;
    private Object dstIp;
    private Object dstIpMask;
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
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "MaskFlag")
    public Long getMaskFlag() {
        return maskFlag;
    }

    public void setMaskFlag(Long maskFlag) {
        this.maskFlag = maskFlag;
    }

    @Basic
    @Column(name = "Protocol")
    public Long getProtocol() {
        return protocol;
    }

    public void setProtocol(Long protocol) {
        this.protocol = protocol;
    }

    @Basic
    @Column(name = "ProtocolMask")
    public Long getProtocolMask() {
        return protocolMask;
    }

    public void setProtocolMask(Long protocolMask) {
        this.protocolMask = protocolMask;
    }

    @Basic
    @Column(name = "SrcIp")
    public Object getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(Object srcIp) {
        this.srcIp = srcIp;
    }

    @Basic
    @Column(name = "SrcIpMask")
    public Object getSrcIpMask() {
        return srcIpMask;
    }

    public void setSrcIpMask(Object srcIpMask) {
        this.srcIpMask = srcIpMask;
    }

    @Basic
    @Column(name = "SrcPort")
    public Long getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(Long srcPort) {
        this.srcPort = srcPort;
    }

    @Basic
    @Column(name = "SrcPortMask")
    public Long getSrcPortMask() {
        return srcPortMask;
    }

    public void setSrcPortMask(Long srcPortMask) {
        this.srcPortMask = srcPortMask;
    }

    @Basic
    @Column(name = "DstIp")
    public Object getDstIp() {
        return dstIp;
    }

    public void setDstIp(Object dstIp) {
        this.dstIp = dstIp;
    }

    @Basic
    @Column(name = "DstIpMask")
    public Object getDstIpMask() {
        return dstIpMask;
    }

    public void setDstIpMask(Object dstIpMask) {
        this.dstIpMask = dstIpMask;
    }

    @Basic
    @Column(name = "DstPort")
    public Long getDstPort() {
        return dstPort;
    }

    public void setDstPort(Long dstPort) {
        this.dstPort = dstPort;
    }

    @Basic
    @Column(name = "DstPortMask")
    public Long getDstPortMask() {
        return dstPortMask;
    }

    public void setDstPortMask(Long dstPortMask) {
        this.dstPortMask = dstPortMask;
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
    @Column(name = "IsCompound")
    public Long getIsCompound() {
        return isCompound;
    }

    public void setIsCompound(Long isCompound) {
        this.isCompound = isCompound;
    }

    @Basic
    @Column(name = "CombRuleType1")
    public Long getCombRuleType1() {
        return combRuleType1;
    }

    public void setCombRuleType1(Long combRuleType1) {
        this.combRuleType1 = combRuleType1;
    }

    @Basic
    @Column(name = "CombRuleType2")
    public Long getCombRuleType2() {
        return combRuleType2;
    }

    public void setCombRuleType2(Long combRuleType2) {
        this.combRuleType2 = combRuleType2;
    }

    @Basic
    @Column(name = "CombRuleType3")
    public Long getCombRuleType3() {
        return combRuleType3;
    }

    public void setCombRuleType3(Long combRuleType3) {
        this.combRuleType3 = combRuleType3;
    }

    @Basic
    @Column(name = "CombRuleType4")
    public Long getCombRuleType4() {
        return combRuleType4;
    }

    public void setCombRuleType4(Long combRuleType4) {
        this.combRuleType4 = combRuleType4;
    }

    @Basic
    @Column(name = "CombRuleType5")
    public Long getCombRuleType5() {
        return combRuleType5;
    }

    public void setCombRuleType5(Long combRuleType5) {
        this.combRuleType5 = combRuleType5;
    }

    @Basic
    @Column(name = "CombRuleId1")
    public Long getCombRuleId1() {
        return combRuleId1;
    }

    public void setCombRuleId1(Long combRuleId1) {
        this.combRuleId1 = combRuleId1;
    }

    @Basic
    @Column(name = "CombRuleId2")
    public Long getCombRuleId2() {
        return combRuleId2;
    }

    public void setCombRuleId2(Long combRuleId2) {
        this.combRuleId2 = combRuleId2;
    }

    @Basic
    @Column(name = "CombRuleId3")
    public Long getCombRuleId3() {
        return combRuleId3;
    }

    public void setCombRuleId3(Long combRuleId3) {
        this.combRuleId3 = combRuleId3;
    }

    @Basic
    @Column(name = "CombRuleId4")
    public Long getCombRuleId4() {
        return combRuleId4;
    }

    public void setCombRuleId4(Long combRuleId4) {
        this.combRuleId4 = combRuleId4;
    }

    @Basic
    @Column(name = "CombRuleId5")
    public Long getCombRuleId5() {
        return combRuleId5;
    }

    public void setCombRuleId5(Long combRuleId5) {
        this.combRuleId5 = combRuleId5;
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
    @Column(name = "MatchType")
    public Long getMatchType() {
        return matchType;
    }

    public void setMatchType(Long matchType) {
        this.matchType = matchType;
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
        Ipv6RuleMsg that = (Ipv6RuleMsg) o;
        return domainId == that.domainId &&
                userId == that.userId &&
                ruleId == that.ruleId &&
                Objects.equals(ruleSource, that.ruleSource) &&
                Objects.equals(profileId, that.profileId) &&
                Objects.equals(maskFlag, that.maskFlag) &&
                Objects.equals(protocol, that.protocol) &&
                Objects.equals(protocolMask, that.protocolMask) &&
                Objects.equals(srcIp, that.srcIp) &&
                Objects.equals(srcIpMask, that.srcIpMask) &&
                Objects.equals(srcPort, that.srcPort) &&
                Objects.equals(srcPortMask, that.srcPortMask) &&
                Objects.equals(dstIp, that.dstIp) &&
                Objects.equals(dstIpMask, that.dstIpMask) &&
                Objects.equals(dstPort, that.dstPort) &&
                Objects.equals(dstPortMask, that.dstPortMask) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(isStatic, that.isStatic) &&
                Objects.equals(isHitStat, that.isHitStat) &&
                Objects.equals(isCompound, that.isCompound) &&
                Objects.equals(combRuleType1, that.combRuleType1) &&
                Objects.equals(combRuleType2, that.combRuleType2) &&
                Objects.equals(combRuleType3, that.combRuleType3) &&
                Objects.equals(combRuleType4, that.combRuleType4) &&
                Objects.equals(combRuleType5, that.combRuleType5) &&
                Objects.equals(combRuleId1, that.combRuleId1) &&
                Objects.equals(combRuleId2, that.combRuleId2) &&
                Objects.equals(combRuleId3, that.combRuleId3) &&
                Objects.equals(combRuleId4, that.combRuleId4) &&
                Objects.equals(combRuleId5, that.combRuleId5) &&
                Objects.equals(setTime, that.setTime) &&
                Objects.equals(setIp, that.setIp) &&
                Objects.equals(matchType, that.matchType) &&
                Objects.equals(inputPortGroupId, that.inputPortGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleSource, domainId, userId, ruleId, profileId, maskFlag, protocol, protocolMask, srcIp, srcIpMask, srcPort, srcPortMask, dstIp, dstIpMask, dstPort, dstPortMask, priority, isStatic, isHitStat, isCompound, combRuleType1, combRuleType2, combRuleType3, combRuleType4, combRuleType5, combRuleId1, combRuleId2, combRuleId3, combRuleId4, combRuleId5, setTime, setIp, matchType, inputPortGroupId);
    }
}
