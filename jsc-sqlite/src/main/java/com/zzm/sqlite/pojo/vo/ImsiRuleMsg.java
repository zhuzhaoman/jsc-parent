package com.sample;


public class ImsiRuleMsg {

  private long ruleSource;
  private long domainId;
  private long userId;
  private long ruleId;
  private long profileId;
  private String imsi;
  private long priority;
  private long setTime;
  private long setIp;
  private long isStatic;
  private long isHitStat;
  private long inputPortGroupId;
  private long isCompound;


  public long getRuleSource() {
    return ruleSource;
  }

  public void setRuleSource(long ruleSource) {
    this.ruleSource = ruleSource;
  }


  public long getDomainId() {
    return domainId;
  }

  public void setDomainId(long domainId) {
    this.domainId = domainId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getRuleId() {
    return ruleId;
  }

  public void setRuleId(long ruleId) {
    this.ruleId = ruleId;
  }


  public long getProfileId() {
    return profileId;
  }

  public void setProfileId(long profileId) {
    this.profileId = profileId;
  }


  public String getImsi() {
    return imsi;
  }

  public void setImsi(String imsi) {
    this.imsi = imsi;
  }


  public long getPriority() {
    return priority;
  }

  public void setPriority(long priority) {
    this.priority = priority;
  }


  public long getSetTime() {
    return setTime;
  }

  public void setSetTime(long setTime) {
    this.setTime = setTime;
  }


  public long getSetIp() {
    return setIp;
  }

  public void setSetIp(long setIp) {
    this.setIp = setIp;
  }


  public long getIsStatic() {
    return isStatic;
  }

  public void setIsStatic(long isStatic) {
    this.isStatic = isStatic;
  }


  public long getIsHitStat() {
    return isHitStat;
  }

  public void setIsHitStat(long isHitStat) {
    this.isHitStat = isHitStat;
  }


  public long getInputPortGroupId() {
    return inputPortGroupId;
  }

  public void setInputPortGroupId(long inputPortGroupId) {
    this.inputPortGroupId = inputPortGroupId;
  }


  public long getIsCompound() {
    return isCompound;
  }

  public void setIsCompound(long isCompound) {
    this.isCompound = isCompound;
  }

}
