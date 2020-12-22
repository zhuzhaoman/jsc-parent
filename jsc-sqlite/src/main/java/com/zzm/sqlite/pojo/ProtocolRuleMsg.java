package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "PROTOCOL_RULE_MSG", schema = "main", catalog = "")
@IdClass(ProtocolRuleMsgPK.class)
public class ProtocolRuleMsg {
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private Long profileId;
    private Long protocolType;
    private Object imsi;
    private Long inputPortGroup;
    private Long protocol;
    private Long protocolMask;
    private Long srcIpv4;
    private Long srcIpv4Mask;
    private Long srcPort;
    private Long srcPortMask;
    private Long dstIpv4;
    private Long dstIpv4Mask;
    private Long dstPort;
    private Long dstPortMask;
    private Object srcIpv6;
    private Object srcIpv6Mask;
    private Object dstIpv6;
    private Object dstIpv6Mask;
    private Long ruleType;
    private Long priority;
    private Long isStatic;
    private Long isHitStat;

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
    @Column(name = "ProtocolType")
    public Long getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Long protocolType) {
        this.protocolType = protocolType;
    }

    @Basic
    @Column(name = "Imsi")
    public Object getImsi() {
        return imsi;
    }

    public void setImsi(Object imsi) {
        this.imsi = imsi;
    }

    @Basic
    @Column(name = "InputPortGroup")
    public Long getInputPortGroup() {
        return inputPortGroup;
    }

    public void setInputPortGroup(Long inputPortGroup) {
        this.inputPortGroup = inputPortGroup;
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
    @Column(name = "SrcIpv4")
    public Long getSrcIpv4() {
        return srcIpv4;
    }

    public void setSrcIpv4(Long srcIpv4) {
        this.srcIpv4 = srcIpv4;
    }

    @Basic
    @Column(name = "SrcIpv4Mask")
    public Long getSrcIpv4Mask() {
        return srcIpv4Mask;
    }

    public void setSrcIpv4Mask(Long srcIpv4Mask) {
        this.srcIpv4Mask = srcIpv4Mask;
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
    @Column(name = "DstIpv4")
    public Long getDstIpv4() {
        return dstIpv4;
    }

    public void setDstIpv4(Long dstIpv4) {
        this.dstIpv4 = dstIpv4;
    }

    @Basic
    @Column(name = "DstIpv4Mask")
    public Long getDstIpv4Mask() {
        return dstIpv4Mask;
    }

    public void setDstIpv4Mask(Long dstIpv4Mask) {
        this.dstIpv4Mask = dstIpv4Mask;
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
    @Column(name = "SrcIpv6")
    public Object getSrcIpv6() {
        return srcIpv6;
    }

    public void setSrcIpv6(Object srcIpv6) {
        this.srcIpv6 = srcIpv6;
    }

    @Basic
    @Column(name = "SrcIpv6Mask")
    public Object getSrcIpv6Mask() {
        return srcIpv6Mask;
    }

    public void setSrcIpv6Mask(Object srcIpv6Mask) {
        this.srcIpv6Mask = srcIpv6Mask;
    }

    @Basic
    @Column(name = "DstIpv6")
    public Object getDstIpv6() {
        return dstIpv6;
    }

    public void setDstIpv6(Object dstIpv6) {
        this.dstIpv6 = dstIpv6;
    }

    @Basic
    @Column(name = "DstIpv6Mask")
    public Object getDstIpv6Mask() {
        return dstIpv6Mask;
    }

    public void setDstIpv6Mask(Object dstIpv6Mask) {
        this.dstIpv6Mask = dstIpv6Mask;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtocolRuleMsg that = (ProtocolRuleMsg) o;
        return domainId == that.domainId &&
                userId == that.userId &&
                ruleId == that.ruleId &&
                Objects.equals(ruleSource, that.ruleSource) &&
                Objects.equals(profileId, that.profileId) &&
                Objects.equals(protocolType, that.protocolType) &&
                Objects.equals(imsi, that.imsi) &&
                Objects.equals(inputPortGroup, that.inputPortGroup) &&
                Objects.equals(protocol, that.protocol) &&
                Objects.equals(protocolMask, that.protocolMask) &&
                Objects.equals(srcIpv4, that.srcIpv4) &&
                Objects.equals(srcIpv4Mask, that.srcIpv4Mask) &&
                Objects.equals(srcPort, that.srcPort) &&
                Objects.equals(srcPortMask, that.srcPortMask) &&
                Objects.equals(dstIpv4, that.dstIpv4) &&
                Objects.equals(dstIpv4Mask, that.dstIpv4Mask) &&
                Objects.equals(dstPort, that.dstPort) &&
                Objects.equals(dstPortMask, that.dstPortMask) &&
                Objects.equals(srcIpv6, that.srcIpv6) &&
                Objects.equals(srcIpv6Mask, that.srcIpv6Mask) &&
                Objects.equals(dstIpv6, that.dstIpv6) &&
                Objects.equals(dstIpv6Mask, that.dstIpv6Mask) &&
                Objects.equals(ruleType, that.ruleType) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(isStatic, that.isStatic) &&
                Objects.equals(isHitStat, that.isHitStat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleSource, domainId, userId, ruleId, profileId, protocolType, imsi, inputPortGroup, protocol, protocolMask, srcIpv4, srcIpv4Mask, srcPort, srcPortMask, dstIpv4, dstIpv4Mask, dstPort, dstPortMask, srcIpv6, srcIpv6Mask, dstIpv6, dstIpv6Mask, ruleType, priority, isStatic, isHitStat);
    }
}
