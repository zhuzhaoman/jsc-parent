package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.pojo.ProtocolRuleMsg;
import com.zzm.sqlite.utils.*;
import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
@Data
@BindEntity(ProtocolRuleMsg.class)
public class ProtocolVO {

    private Long ruleId;
    private Long profileId;
    private Long inputPortGroup;
    private Long priority;

    @BindField(fieldName = "srcIpv4," +
            "srcIpv6," +
            "ruleType", methodName = "ipFormat", value = ProtocolUtils.class)
    private String srcIp;

    @BindField(fieldName = "srcIpv4Mask," +
            "srcIpv6Mask," +
            "ruleType", methodName = "ipFormat", value = ProtocolUtils.class)
    private String srcIpMask;

    @BindField(fieldName = "dstIpv4," +
            "dstIpv6," +
            "ruleType", methodName = "ipFormat", value = ProtocolUtils.class)
    private String dstIp;

    @BindField(fieldName = "dstIpv4Mask," +
            "dstIpv6Mask," +
            "ruleType", methodName = "ipFormat", value = ProtocolUtils.class)
    private String dstIpMask;

    @BindField(fieldName = "protocolType," +
            "ruleType", methodName = "protocolTypeFormat", value = ProtocolUtils.class)
    private String protocolType;

    @BindField(fieldName = "protocol", methodName = "getProtocolType", value = StatusUtils.class)
    private String protocol;

    @BindField(fieldName = "protocolMask", methodName = "long2Hex", value = BaseConversionUtils.class)
    private String protocolMask;

    @BindField(fieldName = "ruleType", methodName = "getProtocolRuleType", value = StatusUtils.class)
    private String ruleType;

    @BindField(fieldName = "srcPort", methodName = "portFormat", value = PortUtils.class)
    private String srcPort;

    @BindField(fieldName = "srcPortMask", methodName = "long2Hex", value = BaseConversionUtils.class)
    private String srcPortMask;

    @BindField(fieldName = "dstPort", methodName = "portFormat", value = PortUtils.class)
    private String dstPort;

    @BindField(fieldName = "dstPortMask", methodName = "long2Hex", value = BaseConversionUtils.class)
    private String dstPortMask;

    @BindField(fieldName = "isStatic", methodName = "getStatic", value = StatusUtils.class)
    private String isStatic;

    @BindField(fieldName = "isHitStat", methodName = "getHitStat", value = StatusUtils.class)
    private String isHitStat;

    @BindField(fieldName = "ruleSource", methodName = "getRuleResource", value = StatusUtils.class)
    private String ruleSource;

    @BindField(fieldName = "userId", methodName = "getUserId", value = StatusUtils.class)
    private String userId;

}
