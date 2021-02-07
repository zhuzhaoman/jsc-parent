package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "WINDOW_RULE_MSG")
@IdClass(WindowRuleMsgPK.class)
public class WindowRuleMsg {
    
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private long profileId;
    private long keyCodeLen;
    private String keyCode;
    private String keyCodeMask;
    private Long windowLength;
    private Long windowBeginPos;
    private Long ruleType;
    private Long relatedType1;
    private Long relatedType2;
    private Long relatedType3;
    private Long relatedType4;
    private Long relatedType5;
    private Long relatedId1;
    private Long relatedId2;
    private Long relatedId3;
    private Long relatedId4;
    private Long relatedId5;
    private Long isStatic;
    private Long isHitStat;
    private Long isComPound;
    private Long priority;
    private Long inputPortGroupId;
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
    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    
    @Column(name = "KeyCodeLen")
    public long getKeyCodeLen() {
        return keyCodeLen;
    }

    public void setKeyCodeLen(long keyCodeLen) {
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

    
    @Column(name = "WindowLength")
    public Long getWindowLength() {
        return windowLength;
    }

    public void setWindowLength(Long windowLength) {
        this.windowLength = windowLength;
    }

    
    @Column(name = "WindowBeginPos")
    public Long getWindowBeginPos() {
        return windowBeginPos;
    }

    public void setWindowBeginPos(Long windowBeginPos) {
        this.windowBeginPos = windowBeginPos;
    }

    
    @Column(name = "RuleType")
    public Long getRuleType() {
        return ruleType;
    }

    public void setRuleType(Long ruleType) {
        this.ruleType = ruleType;
    }

    
    @Column(name = "RelatedType1")
    public Long getRelatedType1() {
        return relatedType1;
    }

    public void setRelatedType1(Long relatedType1) {
        this.relatedType1 = relatedType1;
    }

    
    @Column(name = "RelatedType2")
    public Long getRelatedType2() {
        return relatedType2;
    }

    public void setRelatedType2(Long relatedType2) {
        this.relatedType2 = relatedType2;
    }

    
    @Column(name = "RelatedType3")
    public Long getRelatedType3() {
        return relatedType3;
    }

    public void setRelatedType3(Long relatedType3) {
        this.relatedType3 = relatedType3;
    }

    
    @Column(name = "RelatedType4")
    public Long getRelatedType4() {
        return relatedType4;
    }

    public void setRelatedType4(Long relatedType4) {
        this.relatedType4 = relatedType4;
    }

    
    @Column(name = "RelatedType5")
    public Long getRelatedType5() {
        return relatedType5;
    }

    public void setRelatedType5(Long relatedType5) {
        this.relatedType5 = relatedType5;
    }

    
    @Column(name = "RelatedId1")
    public Long getRelatedId1() {
        return relatedId1;
    }

    public void setRelatedId1(Long relatedId1) {
        this.relatedId1 = relatedId1;
    }

    
    @Column(name = "RelatedId2")
    public Long getRelatedId2() {
        return relatedId2;
    }

    public void setRelatedId2(Long relatedId2) {
        this.relatedId2 = relatedId2;
    }

    
    @Column(name = "RelatedId3")
    public Long getRelatedId3() {
        return relatedId3;
    }

    public void setRelatedId3(Long relatedId3) {
        this.relatedId3 = relatedId3;
    }

    
    @Column(name = "RelatedId4")
    public Long getRelatedId4() {
        return relatedId4;
    }

    public void setRelatedId4(Long relatedId4) {
        this.relatedId4 = relatedId4;
    }

    
    @Column(name = "RelatedId5")
    public Long getRelatedId5() {
        return relatedId5;
    }

    public void setRelatedId5(Long relatedId5) {
        this.relatedId5 = relatedId5;
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
