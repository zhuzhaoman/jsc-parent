package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.core.BindFieldIgnore;
import com.zzm.sqlite.pojo.ImsiRuleMsg;
import com.zzm.sqlite.pojo.WindowRuleMsg;
import com.zzm.sqlite.utils.BaseConversionUtils;
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
@BindEntity(ImsiRuleMsg.class)
public class ImsiVO {

    private Long ruleId;

    private Long inputPortGroupId;

    private Long profileId;

    private Long priority;

    private String imsi;

    @BindFieldIgnore
    private String ruleType = "imsi";

    @BindField(fieldName = "isCompound", methodName = "getFixCharCompound", value = StatusUtils.class)
    private String isCompound;

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
