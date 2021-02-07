package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.pojo.ProtocolRuleMsg;
import com.zzm.sqlite.pojo.UrlRuleMsg;
import com.zzm.sqlite.utils.*;
import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
@Data
@BindEntity(UrlRuleMsg.class)
public class UrlVO {

    private Long ruleId;
    private Long profileId;
    private Long inputPortGroup;
    private Long priority;

    @BindField(fieldName = "ruleType", methodName = "getUrlRuleType", value = StatusUtils.class)
    private String ruleType;

    @BindField(fieldName = "keyCode", methodName = "keyCodeFormat", value = KeyCodeUtils.class)
    private String keyCode;

    @BindField(fieldName = "keyCodeMask", methodName = "keyCodeFormat", value = KeyCodeUtils.class)
    private String keyCodeMask;

    @BindField(fieldName = "isComPound", methodName = "getFixCharCompound", value = StatusUtils.class)
    private String isComPound;

    @BindField(fieldName =
            "combRuleType1," +
                    "combRuleType2," +
                    "combRuleType3," +
                    "combRuleType4," +
                    "combRuleType5," +
                    "combRuleId1," +
                    "combRuleId2," +
                    "combRuleId3," +
                    "combRuleId4," +
                    "combRuleId5,", methodName = "getComRuleType", value = ComRuleTypeUtils.class)
    private String combRuleType;

    @BindField(fieldName = "isHitStat", methodName = "getHitStat", value = StatusUtils.class)
    private String isHitStat;

    @BindField(fieldName = "ruleSource", methodName = "getRuleResource", value = StatusUtils.class)
    private String ruleSource;

    @BindField(fieldName = "userId", methodName = "getUserId", value = StatusUtils.class)
    private String userId;


}
