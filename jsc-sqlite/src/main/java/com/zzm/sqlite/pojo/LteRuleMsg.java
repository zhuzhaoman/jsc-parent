package com.zzm.sqlite.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@Entity
@Table(name = "LTE_RULE_MSG", schema = "main", catalog = "")
@IdClass(LteRuleMsgPK.class)
public class LteRuleMsg {
    private Long ruleSource;
    private long domainId;
    private long userId;
    private long ruleId;
    private Long profileId;
    private Object imsi;
    private Object imsiMask;
    private Object msisdn;
    private Object msisdnMask;
    private Object imei;
    private Object imeiMask;
    private Object meid;
    private Object meidMask;
    private Object esn;
    private Object esnMask;
    private Object lac;
    private Object lacMask;
    private Object tac;
    private Object tacMask;
    private Object ci;
    private Object ciMask;
    private Object apn;
    private Object apnMask;
    private Object bsid;
    private Object bsidMask;
    private Object enodeB;
    private Object enodeBMask;
    private Object rnc;
    private Object rncMask;
    private Object cellid;
    private Object cellidMask;
    private Object rat;
    private Object ratMask;
    private Object qci;
    private Object qciMask;
    private Long priority;
    private Long setTime;
    private Long setIp;
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
    @Column(name = "Imsi")
    public Object getImsi() {
        return imsi;
    }

    public void setImsi(Object imsi) {
        this.imsi = imsi;
    }

    @Basic
    @Column(name = "ImsiMask")
    public Object getImsiMask() {
        return imsiMask;
    }

    public void setImsiMask(Object imsiMask) {
        this.imsiMask = imsiMask;
    }

    @Basic
    @Column(name = "Msisdn")
    public Object getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(Object msisdn) {
        this.msisdn = msisdn;
    }

    @Basic
    @Column(name = "MsisdnMask")
    public Object getMsisdnMask() {
        return msisdnMask;
    }

    public void setMsisdnMask(Object msisdnMask) {
        this.msisdnMask = msisdnMask;
    }

    @Basic
    @Column(name = "Imei")
    public Object getImei() {
        return imei;
    }

    public void setImei(Object imei) {
        this.imei = imei;
    }

    @Basic
    @Column(name = "ImeiMask")
    public Object getImeiMask() {
        return imeiMask;
    }

    public void setImeiMask(Object imeiMask) {
        this.imeiMask = imeiMask;
    }

    @Basic
    @Column(name = "Meid")
    public Object getMeid() {
        return meid;
    }

    public void setMeid(Object meid) {
        this.meid = meid;
    }

    @Basic
    @Column(name = "MeidMask")
    public Object getMeidMask() {
        return meidMask;
    }

    public void setMeidMask(Object meidMask) {
        this.meidMask = meidMask;
    }

    @Basic
    @Column(name = "Esn")
    public Object getEsn() {
        return esn;
    }

    public void setEsn(Object esn) {
        this.esn = esn;
    }

    @Basic
    @Column(name = "EsnMask")
    public Object getEsnMask() {
        return esnMask;
    }

    public void setEsnMask(Object esnMask) {
        this.esnMask = esnMask;
    }

    @Basic
    @Column(name = "Lac")
    public Object getLac() {
        return lac;
    }

    public void setLac(Object lac) {
        this.lac = lac;
    }

    @Basic
    @Column(name = "LacMask")
    public Object getLacMask() {
        return lacMask;
    }

    public void setLacMask(Object lacMask) {
        this.lacMask = lacMask;
    }

    @Basic
    @Column(name = "Tac")
    public Object getTac() {
        return tac;
    }

    public void setTac(Object tac) {
        this.tac = tac;
    }

    @Basic
    @Column(name = "TacMask")
    public Object getTacMask() {
        return tacMask;
    }

    public void setTacMask(Object tacMask) {
        this.tacMask = tacMask;
    }

    @Basic
    @Column(name = "Ci")
    public Object getCi() {
        return ci;
    }

    public void setCi(Object ci) {
        this.ci = ci;
    }

    @Basic
    @Column(name = "CiMask")
    public Object getCiMask() {
        return ciMask;
    }

    public void setCiMask(Object ciMask) {
        this.ciMask = ciMask;
    }

    @Basic
    @Column(name = "Apn")
    public Object getApn() {
        return apn;
    }

    public void setApn(Object apn) {
        this.apn = apn;
    }

    @Basic
    @Column(name = "ApnMask")
    public Object getApnMask() {
        return apnMask;
    }

    public void setApnMask(Object apnMask) {
        this.apnMask = apnMask;
    }

    @Basic
    @Column(name = "Bsid")
    public Object getBsid() {
        return bsid;
    }

    public void setBsid(Object bsid) {
        this.bsid = bsid;
    }

    @Basic
    @Column(name = "BsidMask")
    public Object getBsidMask() {
        return bsidMask;
    }

    public void setBsidMask(Object bsidMask) {
        this.bsidMask = bsidMask;
    }

    @Basic
    @Column(name = "EnodeB")
    public Object getEnodeB() {
        return enodeB;
    }

    public void setEnodeB(Object enodeB) {
        this.enodeB = enodeB;
    }

    @Basic
    @Column(name = "EnodeBMask")
    public Object getEnodeBMask() {
        return enodeBMask;
    }

    public void setEnodeBMask(Object enodeBMask) {
        this.enodeBMask = enodeBMask;
    }

    @Basic
    @Column(name = "Rnc")
    public Object getRnc() {
        return rnc;
    }

    public void setRnc(Object rnc) {
        this.rnc = rnc;
    }

    @Basic
    @Column(name = "RncMask")
    public Object getRncMask() {
        return rncMask;
    }

    public void setRncMask(Object rncMask) {
        this.rncMask = rncMask;
    }

    @Basic
    @Column(name = "Cellid")
    public Object getCellid() {
        return cellid;
    }

    public void setCellid(Object cellid) {
        this.cellid = cellid;
    }

    @Basic
    @Column(name = "CellidMask")
    public Object getCellidMask() {
        return cellidMask;
    }

    public void setCellidMask(Object cellidMask) {
        this.cellidMask = cellidMask;
    }

    @Basic
    @Column(name = "Rat")
    public Object getRat() {
        return rat;
    }

    public void setRat(Object rat) {
        this.rat = rat;
    }

    @Basic
    @Column(name = "RatMask")
    public Object getRatMask() {
        return ratMask;
    }

    public void setRatMask(Object ratMask) {
        this.ratMask = ratMask;
    }

    @Basic
    @Column(name = "Qci")
    public Object getQci() {
        return qci;
    }

    public void setQci(Object qci) {
        this.qci = qci;
    }

    @Basic
    @Column(name = "QciMask")
    public Object getQciMask() {
        return qciMask;
    }

    public void setQciMask(Object qciMask) {
        this.qciMask = qciMask;
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
        LteRuleMsg that = (LteRuleMsg) o;
        return domainId == that.domainId &&
                userId == that.userId &&
                ruleId == that.ruleId &&
                Objects.equals(ruleSource, that.ruleSource) &&
                Objects.equals(profileId, that.profileId) &&
                Objects.equals(imsi, that.imsi) &&
                Objects.equals(imsiMask, that.imsiMask) &&
                Objects.equals(msisdn, that.msisdn) &&
                Objects.equals(msisdnMask, that.msisdnMask) &&
                Objects.equals(imei, that.imei) &&
                Objects.equals(imeiMask, that.imeiMask) &&
                Objects.equals(meid, that.meid) &&
                Objects.equals(meidMask, that.meidMask) &&
                Objects.equals(esn, that.esn) &&
                Objects.equals(esnMask, that.esnMask) &&
                Objects.equals(lac, that.lac) &&
                Objects.equals(lacMask, that.lacMask) &&
                Objects.equals(tac, that.tac) &&
                Objects.equals(tacMask, that.tacMask) &&
                Objects.equals(ci, that.ci) &&
                Objects.equals(ciMask, that.ciMask) &&
                Objects.equals(apn, that.apn) &&
                Objects.equals(apnMask, that.apnMask) &&
                Objects.equals(bsid, that.bsid) &&
                Objects.equals(bsidMask, that.bsidMask) &&
                Objects.equals(enodeB, that.enodeB) &&
                Objects.equals(enodeBMask, that.enodeBMask) &&
                Objects.equals(rnc, that.rnc) &&
                Objects.equals(rncMask, that.rncMask) &&
                Objects.equals(cellid, that.cellid) &&
                Objects.equals(cellidMask, that.cellidMask) &&
                Objects.equals(rat, that.rat) &&
                Objects.equals(ratMask, that.ratMask) &&
                Objects.equals(qci, that.qci) &&
                Objects.equals(qciMask, that.qciMask) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(setTime, that.setTime) &&
                Objects.equals(setIp, that.setIp) &&
                Objects.equals(isStatic, that.isStatic) &&
                Objects.equals(isHitStat, that.isHitStat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleSource, domainId, userId, ruleId, profileId, imsi, imsiMask, msisdn, msisdnMask, imei, imeiMask, meid, meidMask, esn, esnMask, lac, lacMask, tac, tacMask, ci, ciMask, apn, apnMask, bsid, bsidMask, enodeB, enodeBMask, rnc, rncMask, cellid, cellidMask, rat, ratMask, qci, qciMask, priority, setTime, setIp, isStatic, isHitStat);
    }
}
