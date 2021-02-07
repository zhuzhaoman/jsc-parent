package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.pojo.FixedloccharcodeRuleMsg;
import com.zzm.sqlite.utils.*;
import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-30
 * @description:
 **/
@Data
@BindEntity(FixedloccharcodeRuleMsg.class)
public class FixCharVO{

    private Long ruleId;

    private Long inputPortGroupId;

    private Long priority;

    private Long profileId;

    private Long beginPos;

    private Long keyCodeLen;

    @BindField(fieldName = "keyCode", methodName = "keyCodeFormat", value = KeyCodeUtils.class)
    private String keyCode;

    @BindField(fieldName = "keyCodeMask", methodName = "keyCodeFormat", value = KeyCodeUtils.class)
    private String keyCodeMask;

    @BindField(fieldName = "protocol", methodName = "getProtocolType", value = StatusUtils.class)
    private String protocol;

    @BindField(fieldName = "protocolMask", methodName = "long2Hex", value = BaseConversionUtils.class)
    private String protocolMask;

    @BindField(fieldName = "srcPort", methodName = "portFormat", value = PortUtils.class)
    private String srcPort;

    @BindField(fieldName = "srcPortMask", methodName = "long2Hex", value = BaseConversionUtils.class)
    private String srcPortMask;

    @BindField(fieldName = "dstPort", methodName = "portFormat", value = PortUtils.class)
    private String dstPort;

    @BindField(fieldName = "dstPortMask", methodName = "long2Hex", value = BaseConversionUtils.class)
    private String dstPortMask;

    @BindField(fieldName = "isComPound", methodName = "getFixCharCompound", value = StatusUtils.class)
    private String isComPound;

    @BindField(fieldName = "isStatic", methodName = "getStatic", value = StatusUtils.class)
    public String isStatic;

    @BindField(fieldName = "isHitStat", methodName = "getHitStat", value = StatusUtils.class)
    public String isHitStat;

    @BindField(fieldName = "ruleSource", methodName = "getRuleResource", value = StatusUtils.class)
    public String ruleSource;

    @BindField(fieldName = "userId", methodName = "getUserId", value = StatusUtils.class)
    public String userId;

    @BindField(fieldName = "setIp", methodName = "long2Ip", value = Ipv4Utils.class)
    public String setIp;

    @BindField(fieldName = "setTime", methodName = "dateFormat", value = TimeUtils.class)
    public String setTime;
}
