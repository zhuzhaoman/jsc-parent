package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "URL_RULE_MSG")
@IdClass(UrlRuleMsgPK.class)
public class UrlRuleMsg {

    private long domainId;
    private long userId;
    private long ruleId;
    private Long ruleType;
    private String keyCode;
    private String keyCodeMask;
    private Long keyCodeLen;
    private Long profileId;
    private Long isHitStat;
    private Long isComPound;
    private Long ruleSource;
    private Long priority;
    private Long inputPortGroup;
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

    
    @Column(name = "RuleType")
    public Long getRuleType() {
        return ruleType;
    }

    public void setRuleType(Long ruleType) {
        this.ruleType = ruleType;
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

    
    @Column(name = "KeyCodeLen")
    public Long getKeyCodeLen() {
        return keyCodeLen;
    }

    public void setKeyCodeLen(Long keyCodeLen) {
        this.keyCodeLen = keyCodeLen;
    }

    
    @Column(name = "ProfileID")
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
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

    
    @Column(name = "RuleSource")
    public Long getRuleSource() {
        return ruleSource;
    }

    public void setRuleSource(Long ruleSource) {
        this.ruleSource = ruleSource;
    }

    
    @Column(name = "Priority")
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    
    @Column(name = "InputPortGroup")
    public Long getInputPortGroup() {
        return inputPortGroup;
    }

    public void setInputPortGroup(Long inputPortGroup) {
        this.inputPortGroup = inputPortGroup;
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

}
