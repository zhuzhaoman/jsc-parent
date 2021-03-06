package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.pojo.Ipv4RuleMsg;
import com.zzm.sqlite.utils.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-28
 * @description:
 **/
@Data
@BindEntity(Ipv4RuleMsg.class)
public class Ipv4VO implements Serializable {

    @BindField(fieldName = "sendSlotId", methodName = "slotIdFormat", value = BaseConversionUtils.class)
    private String sendSlotId;

    private Long ruleId;

    @BindField(fieldName = "srcIp", methodName = "long2Ip", value = Ipv4Utils.class)
    private String srcIp;

    @BindField(fieldName = "srcIpMask", methodName = "long2Ip", value = Ipv4Utils.class)
    private String srcIpMask;

    @BindField(fieldName = "srcPort", methodName = "portFormat", value = PortUtils.class)
    private String srcPort;

    @BindField(fieldName = "srcPortMask", methodName = "long2Hex", value = BaseConversionUtils.class)
    private String srcPortMask;

    @BindField(fieldName = "dstIp", methodName = "long2Ip", value = Ipv4Utils.class)
    private String dstIp;

    @BindField(fieldName = "dstIpMask", methodName = "long2Ip", value = Ipv4Utils.class)
    private String dstIpMask;

    @BindField(fieldName = "dstPort", methodName = "portFormat", value = PortUtils.class)
    private String dstPort;

    @BindField(fieldName = "dstPortMask", methodName = "long2Hex", value = BaseConversionUtils.class)
    private String dstPortMask;

    @BindField(fieldName = "protocol", methodName = "getProtocolType", value = StatusUtils.class)
    private String protocol;

    @BindField(fieldName = "protocolMask", methodName = "long2Hex", value = BaseConversionUtils.class)
    private String protocolMask;

    @BindField(fieldName = "maskFlag", methodName = "getMaskFlag", value = StatusUtils.class)
    private String maskFlag;

    @BindField(fieldName = "isCompound", methodName = "getCompound", value = StatusUtils.class)
    private String isCompound;

    @BindField(fieldName = "isStatic", methodName = "getStatic", value = StatusUtils.class)
    private String isStatic;

    @BindField(fieldName = "isHitStat", methodName = "getHitStat", value = StatusUtils.class)
    private String isHitStat;

    @BindField(fieldName = "ruleSource", methodName = "getRuleResource", value = StatusUtils.class)
    private String ruleSource;

    @BindField(fieldName = "userId", methodName = "getUserId", value = StatusUtils.class)
    private String userId;

    private Long inputPortGroupId;

    private Long priority;

    private Long profileId;

    @BindField(fieldName = "setIp", methodName = "long2Ip", value = Ipv4Utils.class)
    private String setIp;

    @BindField(fieldName = "setTime", methodName = "dateFormat", value = TimeUtils.class)
    private String setTime;

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
}
