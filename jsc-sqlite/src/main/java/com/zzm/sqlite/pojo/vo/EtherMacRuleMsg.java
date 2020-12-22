package com.sample;


import com.zzm.sqlite.pojo.EtherMacRuleMsgPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ETHER_MAC_RULE_MSG", schema = "main", catalog = "")
@IdClass(EtherMacRuleMsgPK.class)
public class EtherMacRuleMsg {

  private long ruleSource;
    private long domainId;
  private long userId;
  private long ruleId;
  private long profileId;
  private String srcMac;
    private String dstMac;
    private long ruleType;
    private long isStatic;
    private long isHitStat;
    private long isComPound;
    private long priority;
    private long inputPortGroupId;
    private long setTime;
    private long setIp;

    @Basic
  @Column(name = "RuleSource")
  public long getRuleSource() {
    return ruleSource;
  }

    public void setRuleSource(Long ruleSource) {
        this.ruleSource = ruleSource;
    }

  public void setRuleSource(long ruleSource) {
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
  @Column(name = "SrcMac")
  public String getSrcMac() {
    return srcMac;
  }

    public void setSrcMac(Object srcMac) {
        this.srcMac = srcMac;
    }

  public void setSrcMac(String srcMac) {
    this.srcMac = srcMac;
  }

  @Basic
  @Column(name = "DstMac")
  public String getDstMac() {
    return dstMac;
  }

    public void setDstMac(Object dstMac) {
        this.dstMac = dstMac;
    }

  public void setDstMac(String dstMac) {
    this.dstMac = dstMac;
  }

  @Basic
  @Column(name = "RuleType")
  public long getRuleType() {
    return ruleType;
  }

    public void setRuleType(Long ruleType) {
        this.ruleType = ruleType;
    }

  public void setRuleType(long ruleType) {
    this.ruleType = ruleType;
  }

  @Basic
  @Column(name = "IsStatic")
  public long getIsStatic() {
    return isStatic;
  }

    public void setIsStatic(Long isStatic) {
        this.isStatic = isStatic;
    }

  public void setIsStatic(long isStatic) {
    this.isStatic = isStatic;
  }

  @Basic
  @Column(name = "IsHitStat")
  public long getIsHitStat() {
    return isHitStat;
  }

    public void setIsHitStat(Long isHitStat) {
        this.isHitStat = isHitStat;
    }

  public void setIsHitStat(long isHitStat) {
    this.isHitStat = isHitStat;
  }

  @Basic
  @Column(name = "IsComPound")
  public long getIsComPound() {
    return isComPound;
  }

    public void setIsComPound(Long isComPound) {
        this.isComPound = isComPound;
    }

  public void setIsComPound(long isComPound) {
    this.isComPound = isComPound;
  }

  @Basic
  @Column(name = "Priority")
  public long getPriority() {
    return priority;
  }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

  public void setPriority(long priority) {
    this.priority = priority;
  }

  @Basic
  @Column(name = "InputPortGroupId")
  public long getInputPortGroupId() {
    return inputPortGroupId;
  }

    public void setInputPortGroupId(Long inputPortGroupId) {
        this.inputPortGroupId = inputPortGroupId;
    }

  public void setInputPortGroupId(long inputPortGroupId) {
    this.inputPortGroupId = inputPortGroupId;
  }

  @Basic
  @Column(name = "SetTime")
  public long getSetTime() {
    return setTime;
  }

    public void setSetTime(Long setTime) {
        this.setTime = setTime;
    }

  public void setSetTime(long setTime) {
    this.setTime = setTime;
  }

  @Basic
  @Column(name = "SetIp")
  public long getSetIp() {
    return setIp;
  }

    public void setSetIp(Long setIp) {
        this.setIp = setIp;
    }

  public void setSetIp(long setIp) {
    this.setIp = setIp;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtherMacRuleMsg that = (EtherMacRuleMsg) o;
        return ruleSource == that.ruleSource &&
                domainId == that.domainId &&
                userId == that.userId &&
                ruleId == that.ruleId &&
                profileId == that.profileId &&
                ruleType == that.ruleType &&
                isStatic == that.isStatic &&
                isHitStat == that.isHitStat &&
                isComPound == that.isComPound &&
                priority == that.priority &&
                inputPortGroupId == that.inputPortGroupId &&
                setTime == that.setTime &&
                setIp == that.setIp &&
                Objects.equals(srcMac, that.srcMac) &&
                Objects.equals(dstMac, that.dstMac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleSource, domainId, userId, ruleId, profileId, srcMac, dstMac, ruleType, isStatic, isHitStat, isComPound, priority, inputPortGroupId, setTime, setIp);
    }
}
