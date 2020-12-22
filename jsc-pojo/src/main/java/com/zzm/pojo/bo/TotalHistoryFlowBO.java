package com.zzm.pojo.bo;

import lombok.Data;
import java.util.Date;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 19:22
 * @description 前端传递查询历史流量参数
 */
@Data
public class TotalHistoryFlowBO {

    private int domainType;

    private int domainId;

    private Date startTime;

    private Date endTime;

}
