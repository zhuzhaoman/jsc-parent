package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "LTE_RULE_MSG")
@IdClass(LteRuleMsgPK.class)
public class LteRuleMsg {

    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private Long profileId;
    private String imsi;
    private String imsiMask;
    private String msisdn;
    private String msisdnMask;
    private String imei;
    private String imeiMask;
    private String meid;
    private String meidMask;
    private String esn;
    private String esnMask;
    private String lac;
    private String lacMask;
    private String tac;
    private String tacMask;
    private String ci;
    private String ciMask;
    private String apn;
    private String apnMask;
    private String bsid;
    private String bsidMask;
    private String enodeB;
    private String enodeBMask;
    private String rnc;
    private String rncMask;
    private String cellid;
    private String cellidMask;
    private String rat;
    private String ratMask;
    private String qci;
    private String qciMask;
    private Long priority;
    private Long setTime;
    private Long setIp;
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

    
    @Column(name = "Imsi")
    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    
    @Column(name = "ImsiMask")
    public String getImsiMask() {
        return imsiMask;
    }

    public void setImsiMask(String imsiMask) {
        this.imsiMask = imsiMask;
    }

    
    @Column(name = "Msisdn")
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    
    @Column(name = "MsisdnMask")
    public String getMsisdnMask() {
        return msisdnMask;
    }

    public void setMsisdnMask(String msisdnMask) {
        this.msisdnMask = msisdnMask;
    }

    
    @Column(name = "Imei")
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    
    @Column(name = "ImeiMask")
    public String getImeiMask() {
        return imeiMask;
    }

    public void setImeiMask(String imeiMask) {
        this.imeiMask = imeiMask;
    }

    
    @Column(name = "Meid")
    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    
    @Column(name = "MeidMask")
    public String getMeidMask() {
        return meidMask;
    }

    public void setMeidMask(String meidMask) {
        this.meidMask = meidMask;
    }

    
    @Column(name = "Esn")
    public String getEsn() {
        return esn;
    }

    public void setEsn(String esn) {
        this.esn = esn;
    }

    
    @Column(name = "EsnMask")
    public String getEsnMask() {
        return esnMask;
    }

    public void setEsnMask(String esnMask) {
        this.esnMask = esnMask;
    }

    
    @Column(name = "Lac")
    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    
    @Column(name = "LacMask")
    public String getLacMask() {
        return lacMask;
    }

    public void setLacMask(String lacMask) {
        this.lacMask = lacMask;
    }

    
    @Column(name = "Tac")
    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    
    @Column(name = "TacMask")
    public String getTacMask() {
        return tacMask;
    }

    public void setTacMask(String tacMask) {
        this.tacMask = tacMask;
    }

    
    @Column(name = "Ci")
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    
    @Column(name = "CiMask")
    public String getCiMask() {
        return ciMask;
    }

    public void setCiMask(String ciMask) {
        this.ciMask = ciMask;
    }

    
    @Column(name = "Apn")
    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    
    @Column(name = "ApnMask")
    public String getApnMask() {
        return apnMask;
    }

    public void setApnMask(String apnMask) {
        this.apnMask = apnMask;
    }

    
    @Column(name = "Bsid")
    public String getBsid() {
        return bsid;
    }

    public void setBsid(String bsid) {
        this.bsid = bsid;
    }

    
    @Column(name = "BsidMask")
    public String getBsidMask() {
        return bsidMask;
    }

    public void setBsidMask(String bsidMask) {
        this.bsidMask = bsidMask;
    }

    
    @Column(name = "EnodeB")
    public String getEnodeB() {
        return enodeB;
    }

    public void setEnodeB(String enodeB) {
        this.enodeB = enodeB;
    }

    
    @Column(name = "EnodeBMask")
    public String getEnodeBMask() {
        return enodeBMask;
    }

    public void setEnodeBMask(String enodeBMask) {
        this.enodeBMask = enodeBMask;
    }

    
    @Column(name = "Rnc")
    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    
    @Column(name = "RncMask")
    public String getRncMask() {
        return rncMask;
    }

    public void setRncMask(String rncMask) {
        this.rncMask = rncMask;
    }

    
    @Column(name = "Cellid")
    public String getCellid() {
        return cellid;
    }

    public void setCellid(String cellid) {
        this.cellid = cellid;
    }

    
    @Column(name = "CellidMask")
    public String getCellidMask() {
        return cellidMask;
    }

    public void setCellidMask(String cellidMask) {
        this.cellidMask = cellidMask;
    }

    
    @Column(name = "Rat")
    public String getRat() {
        return rat;
    }

    public void setRat(String rat) {
        this.rat = rat;
    }

    
    @Column(name = "RatMask")
    public String getRatMask() {
        return ratMask;
    }

    public void setRatMask(String ratMask) {
        this.ratMask = ratMask;
    }

    
    @Column(name = "Qci")
    public String getQci() {
        return qci;
    }

    public void setQci(String qci) {
        this.qci = qci;
    }

    
    @Column(name = "QciMask")
    public String getQciMask() {
        return qciMask;
    }

    public void setQciMask(String qciMask) {
        this.qciMask = qciMask;
    }

    
    @Column(name = "Priority")
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
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
