package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.pojo.FixedloccharcodeRuleMsg;
import com.zzm.sqlite.pojo.WindowRuleMsg;
import com.zzm.sqlite.utils.*;
import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
@Data
@BindEntity(WindowRuleMsg.class)
public class WindowVO {

    private Long ruleId;

    private Long inputPortGroupId;

    private Long priority;

    private Long profileId;

    private Long keyCodeLen;

    @BindField(fieldName = "keyCode", methodName = "keyCodeFormat", value = KeyCodeUtils.class)
    private String keyCode;

    @BindField(fieldName = "keyCodeMask", methodName = "keyCodeFormat", value = KeyCodeUtils.class)
    private String keyCodeMask;

    private Long windowLength;

    private Long windowBeginPos;

    @BindField(fieldName =
            "relatedType1," +
                    "relatedType2," +
                    "relatedType3," +
                    "relatedType4," +
                    "relatedType5," +
                    "relatedId1," +
                    "relatedId2," +
                    "relatedId3," +
                    "relatedId4," +
                    "relatedId5,", methodName = "getRelatedType", value = WindowUtils.class)
    private String relatedType;

    @BindField(fieldName = "isComPound", methodName = "getFullCharCompound", value = StatusUtils.class)
    private String isComPound;

    @BindField(fieldName = "isStatic", methodName = "getStatic", value = StatusUtils.class)
    private String isStatic;

    @BindField(fieldName = "isHitStat", methodName = "getHitStat", value = StatusUtils.class)
    private String isHitStat;

    @BindField(fieldName = "ruleSource", methodName = "getRuleResource", value = StatusUtils.class)
    private String ruleSource;

    @BindField(fieldName = "userId", methodName = "getUserId", value = StatusUtils.class)
    private String userId;

    @BindField(fieldName = "setIp", methodName = "long2Ip", value = Ipv4Utils.class)
    private String setIp;

    @BindField(fieldName = "setTime", methodName = "dateFormat", value = TimeUtils.class)
    private String setTime;


}
