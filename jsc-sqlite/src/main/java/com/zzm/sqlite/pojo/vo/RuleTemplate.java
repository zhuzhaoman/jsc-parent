package com.sample;


public class RuleTemplate {

  private long domainId;
  private long templateType;
  private long priority;
  private long userId;


  public long getDomainId() {
    return domainId;
  }

  public void setDomainId(long domainId) {
    this.domainId = domainId;
  }


  public long getTemplateType() {
    return templateType;
  }

  public void setTemplateType(long templateType) {
    this.templateType = templateType;
  }


  public long getPriority() {
    return priority;
  }

  public void setPriority(long priority) {
    this.priority = priority;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

}
