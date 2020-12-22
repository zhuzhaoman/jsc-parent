package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "FIXEDLOCCHARCODE_RULE_MSG", schema = "main", catalog = "")
@IdClass(FixedloccharcodeRuleMsgPK.class)
public class FixedloccharcodeRuleMsg {
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private long profileId;
    private Long beginPos;
    private Long keyCodeLen;
    private Object keyCode;
    private Object keyCodeMask;
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
    @Column(name = "BeginPos")
    public Long getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(Long beginPos) {
        this.beginPos = beginPos;
    }

    @Basic
    @Column(name = "KeyCodeLen")
    public Long getKeyCodeLen() {
        return keyCodeLen;
    }

    public void setKeyCodeLen(Long keyCodeLen) {
        this.keyCodeLen = keyCodeLen;
    }

    @Basic
    @Column(name = "KeyCode")
    public Object getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(Object keyCode) {
        this.keyCode = keyCode;
    }

    @Basic
    @Column(name = "KeyCodeMask")
    public Object getKeyCodeMask() {
        return keyCodeMask;
    }

    public void setKeyCodeMask(Object keyCodeMask) {
        this.keyCodeMask = keyCodeMask;
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
    @Column(name = "InputPortGroupId")
    public Long getInputPortGroupId() {
        return inputPortGroupId;
    }

    public void setInputPortGroupId(Long inputPortGroupId) {
        this.inputPortGroupId = inputPortGroupId;
    }

    @Basic
    @Column(name = "OutpuPktAction")
    public Long getOutpuPktAction() {
        return outpuPktAction;
    }

    public void setOutpuPktAction(Long outpuPktAction) {
        this.outpuPktAction = outpuPktAction;
    }

    @Basic
    @Column(name = "OutpuPktNum")
    public Long getOutpuPktNum() {
        return outpuPktNum;
    }

    public void setOutpuPktNum(Long outpuPktNum) {
        this.outpuPktNum = outpuPktNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixedloccharcodeRuleMsg that = (FixedloccharcodeRuleMsg) o;
        return domainId == that.domainId &&
                userId == that.userId &&
                ruleId == that.ruleId &&
                profileId == that.profileId &&
                Objects.equals(ruleSource, that.ruleSource) &&
                Objects.equals(beginPos, that.beginPos) &&
                Objects.equals(keyCodeLen, that.keyCodeLen) &&
                Objects.equals(keyCode, that.keyCode) &&
                Objects.equals(keyCodeMask, that.keyCodeMask) &&
                Objects.equals(isStatic, that.isStatic) &&
                Objects.equals(isHitStat, that.isHitStat) &&
                Objects.equals(isComPound, that.isComPound) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(protocol, that.protocol) &&
                Objects.equals(protocolMask, that.protocolMask) &&
                Objects.equals(srcPort, that.srcPort) &&
                Objects.equals(srcPortMask, that.srcPortMask) &&
                Objects.equals(dstPort, that.dstPort) &&
                Objects.equals(dstPortMask, that.dstPortMask) &&
                Objects.equals(setTime, that.setTime) &&
                Objects.equals(setIp, that.setIp) &&
                Objects.equals(inputPortGroupId, that.inputPortGroupId) &&
                Objects.equals(outpuPktAction, that.outpuPktAction) &&
                Objects.equals(outpuPktNum, that.outpuPktNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleSource, domainId, userId, ruleId, profileId, beginPos, keyCodeLen, keyCode, keyCodeMask, isStatic, isHitStat, isComPound, priority, protocol, protocolMask, srcPort, srcPortMask, dstPort, dstPortMask, setTime, setIp, inputPortGroupId, outpuPktAction, outpuPktNum);
    }
}
