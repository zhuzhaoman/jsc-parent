package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.pojo.FixedloccharcodeRuleMsg;
import com.zzm.sqlite.utils.Ipv4Utils;
import com.zzm.sqlite.utils.StatusUtils;
import com.zzm.sqlite.utils.TimeUtils;
import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-31
 * @description:
 **/
@Data
public class BaseVO {

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
