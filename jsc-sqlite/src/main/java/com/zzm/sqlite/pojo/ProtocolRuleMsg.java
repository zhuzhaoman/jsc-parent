package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "PROTOCOL_RULE_MSG")
@IdClass(ProtocolRuleMsgPK.class)
public class ProtocolRuleMsg {
    
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private Long profileId;
    private Long protocolType;
    private String imsi;
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
    private String srcIpv6;
    private String srcIpv6Mask;
    private String dstIpv6;
    private String dstIpv6Mask;
    private Long ruleType;
    private Long priority;
    private Long isStatic;
    private Long isHitStat;

    
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

    
    @Column(name = "ProtocolType")
    public Long getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Long protocolType) {
        this.protocolType = protocolType;
    }

    
    @Column(name = "Imsi")
    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    
    @Column(name = "InputPortGroup")
    public Long getInputPortGroup() {
        return inputPortGroup;
    }

    public void setInputPortGroup(Long inputPortGroup) {
        this.inputPortGroup = inputPortGroup;
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

    
    @Column(name = "SrcIpv4")
    public Long getSrcIpv4() {
        return srcIpv4;
    }

    public void setSrcIpv4(Long srcIpv4) {
        this.srcIpv4 = srcIpv4;
    }

    
    @Column(name = "SrcIpv4Mask")
    public Long getSrcIpv4Mask() {
        return srcIpv4Mask;
    }

    public void setSrcIpv4Mask(Long srcIpv4Mask) {
        this.srcIpv4Mask = srcIpv4Mask;
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

    
    @Column(name = "DstIpv4")
    public Long getDstIpv4() {
        return dstIpv4;
    }

    public void setDstIpv4(Long dstIpv4) {
        this.dstIpv4 = dstIpv4;
    }

    
    @Column(name = "DstIpv4Mask")
    public Long getDstIpv4Mask() {
        return dstIpv4Mask;
    }

    public void setDstIpv4Mask(Long dstIpv4Mask) {
        this.dstIpv4Mask = dstIpv4Mask;
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

    
    @Column(name = "SrcIpv6")
    public String getSrcIpv6() {
        return srcIpv6;
    }

    public void setSrcIpv6(String srcIpv6) {
        this.srcIpv6 = srcIpv6;
    }

    
    @Column(name = "SrcIpv6Mask")
    public String getSrcIpv6Mask() {
        return srcIpv6Mask;
    }

    public void setSrcIpv6Mask(String srcIpv6Mask) {
        this.srcIpv6Mask = srcIpv6Mask;
    }

    
    @Column(name = "DstIpv6")
    public String getDstIpv6() {
        return dstIpv6;
    }

    public void setDstIpv6(String dstIpv6) {
        this.dstIpv6 = dstIpv6;
    }

    
    @Column(name = "DstIpv6Mask")
    public String getDstIpv6Mask() {
        return dstIpv6Mask;
    }

    public void setDstIpv6Mask(String dstIpv6Mask) {
        this.dstIpv6Mask = dstIpv6Mask;
    }

    
    @Column(name = "RuleType")
    public Long getRuleType() {
        return ruleType;
    }

    public void setRuleType(Long ruleType) {
        this.ruleType = ruleType;
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

}
