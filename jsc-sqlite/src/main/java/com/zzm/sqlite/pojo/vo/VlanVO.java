package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.pojo.FixedloccharcodeRuleMsg;
import com.zzm.sqlite.pojo.VlanRuleMsg;
import com.zzm.sqlite.utils.*;
import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-31
 * @description:
 **/
@Data
@BindEntity(VlanRuleMsg.class)
public class VlanVO {

    private Long ruleId;
    private Long profileId;
    private Long inputPortGroupId;
    private Long priority;

//    @BindField(fieldName = "VlanType1," +
//            "VlanType2," +
//            "VlanType3," +
//            "VlanType4", methodName = "vlanTypeFormat", value = VlanUtils.class)
//    private String vlanType;
//
//    @BindField(fieldName = "VlanId1," +
//            "VlanId2," +
//            "VlanId3," +
//            "VlanId4", methodName = "vlanIdFormat", value = VlanUtils.class)
//    private String vlanId;
//
//    @BindField(fieldName = "VlanIdMask1," +
//            "VlanIdMask2," +
//            "VlanIdMask3," +
//            "VlanIdMask4", methodName = "VlanIdMaskFormat", value = VlanUtils.class)
//    private String vlanMask;

    @BindField(fieldName = "VlanId1," +
                    "VlanId2," +
                    "VlanId3," +
                    "VlanId4," +
                    "VlanType1," +
                    "VlanType2," +
                    "VlanType3," +
                    "VlanType4," +
                    "VlanIdMask1," +
                    "VlanIdMask2," +
                    "VlanIdMask3," +
                    "VlanIdMask4", methodName = "VlanIdAndTypeAndMaskFormat", value = VlanUtils.class)
    private String vlanInfo;

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
