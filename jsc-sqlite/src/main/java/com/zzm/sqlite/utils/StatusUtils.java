package com.zzm.sqlite.utils;

import com.zzm.sqlite.enums.*;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-30
 * @description:
 **/
public class StatusUtils {

    public String getCompound(Long code) {
        return CompoundEnum.fromCode(code);
    }

    public String getMaskFlag(Long code) {
        return MaskFlagEnum.fromCode(code);
    }

    public String getProtocolType(Long code) {
        return ProtocolEnum.fromCode(code);
    }
    public static Long getProtocolCode(String value) {
        return ProtocolEnum.fromValue(value);
    }

    public String getStatic(Long code) {
        return StaticEnum.fromCode(code);
    }

    public String getHitStat(Long code) {
        return HitStatEnum.fromCode(code);
    }

    public String getRuleResource(Long code) {
        return RuleResourceEnum.fromCode(code);
    }

    public String getUserId(Long code) {
        return UserIdEnum.fromCode(code);
    }

    /**
     * fix、imsi、url  共用
     * @param code
     * @return
     */
    public String getFixCharCompound(Long code) {
        return FixCharCompoundEnum.fromCode(code);
    }

    /**
     * full 和 window 共用
     * @param code
     * @return
     */
    public String getFullCharCompound(Long code) {
        return FullCharCompoundEnum.fromCode(code);
    }

    public String getEthMacRuleType(Long code) {
        return EthMacRuleTypeEnum.fromCode(code);
    }

    public String getProtocolRuleType(Long code) {
        return ProtocolRuleTypeEnum.fromCode(code);
    }
    public static Long getProtocolRuleCode(String value) {
        return ProtocolRuleTypeEnum.fromValue(value);
    }

    public String getUrlRuleType(Long code) {
        return UrlRuleTypeEnum.fromCode(code);
    }
}
