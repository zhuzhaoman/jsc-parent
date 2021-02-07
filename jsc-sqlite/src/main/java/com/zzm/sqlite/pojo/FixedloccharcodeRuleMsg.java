package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "FIXEDLOCCHARCODE_RULE_MSG")
@IdClass(FixedloccharcodeRuleMsgPK.class)
public class FixedloccharcodeRuleMsg {
    
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private long profileId;
    private Long beginPos;
    private Long keyCodeLen;
    private String keyCode;
    private String keyCodeMask;
    private Long isStatic;
    private Long isHitStat;
    private Long isComPound;
    private Long priority;
    private Long protocol;
    private Long protocolMask;
    private Long srcPort;
    private Long srcPortMask;
    private Long dstPort;
    private Long dstPortMask;
    private Long setTime;
    private Long setIp;
    private Long inputPortGroupId;
    private Long outpuPktAction;
    private Long outpuPktNum;

    
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

    
    @Column(name = "BeginPos")
    public Long getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(Long beginPos) {
        this.beginPos = beginPos;
    }

    
    @Column(name = "KeyCodeLen")
    public Long getKeyCodeLen() {
        return keyCodeLen;
    }

    public void setKeyCodeLen(Long keyCodeLen) {
        this.keyCodeLen = keyCodeLen;
    }

    
    @Column(name = "KeyCode")
    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    
    @Column(name = "KeyCodeMask")
    public String getKeyCodeMask() {
        return keyCodeMask;
    }

    public void setKeyCodeMask(String keyCodeMask) {
        this.keyCodeMask = keyCodeMask;
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

    
    @Column(name = "OutpuPktAction")
    public Long getOutpuPktAction() {
        return outpuPktAction;
    }

    public void setOutpuPktAction(Long outpuPktAction) {
        this.outpuPktAction = outpuPktAction;
    }

    
    @Column(name = "OutpuPktNum")
    public Long getOutpuPktNum() {
        return outpuPktNum;
    }

    public void setOutpuPktNum(Long outpuPktNum) {
        this.outpuPktNum = outpuPktNum;
    }

}
