package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-08-26
 * @description: 接收前端传递参数用于配置设备阈值
 **/
@Data
public class DeviceThresholdConfigBO {

    // 当前用户
    private String username;

    // 配置阈值类型
    private String thresholdName;

    // 基数
    private Integer thresholdValue;

    // 幂数
    private Integer thresholdPower;

}
