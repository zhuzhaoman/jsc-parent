package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.pojo.EtherMacRuleMsg;
import com.zzm.sqlite.utils.EthMacUtils;
import com.zzm.sqlite.utils.Ipv4Utils;
import com.zzm.sqlite.utils.StatusUtils;
import com.zzm.sqlite.utils.TimeUtils;
import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
@Data
@BindEntity(EtherMacRuleMsg.class)
public class EthMacVO {

    private Long ruleId;

    private Long inputPortGroupId;

    private Long profileId;

    private Long priority;

    @BindField(fieldName = "srcMac", methodName = "macFormat", value = EthMacUtils.class)
    private String srcMac;

    @BindField(fieldName = "srcMacMask", methodName = "macFormat", value = EthMacUtils.class)
    private String srcMacMask;

    @BindField(fieldName = "dstMac", methodName = "macFormat", value = EthMacUtils.class)
    private String dstMac;

    @BindField(fieldName = "dstMacMask", methodName = "macFormat", value = EthMacUtils.class)
    private String dstMacMask;

    @BindField(fieldName = "ruleType", methodName = "getEthMacRuleType", value = StatusUtils.class)
    private String ruleType;

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
