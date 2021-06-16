package com.zzm.sqlite.pojo.vo;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.pojo.ProtocolRuleMsg;
import com.zzm.sqlite.pojo.TcpflagRuleMsg;
import com.zzm.sqlite.utils.*;
import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
@Data
@BindEntity(TcpflagRuleMsg.class)
public class TcpFlagVO {

    private Long ruleId;
    private Long profileId;
    private Long inputPortGroupId;
    private Long priority;

    private Long urgFlag;
    private Long ackFlag;
    private Long pshFlag;
    private Long rstFlag;
    private Long synFlag;
    private Long finFlag;

    @BindField(fieldName = "loadLen", methodName = "getLoadLen", value = TcpFlagUtils.class)
    private String loadLen;

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
