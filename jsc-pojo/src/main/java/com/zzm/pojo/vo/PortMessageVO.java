package com.zzm.pojo.vo;

import lombok.Data;

/**
 * @author zhuzhaoman
 * @date 2020/8/11 0011 20:00
 * @description 指定端口信息（端口状态展示）
 */
@Data
public class PortMessageVO {

    // 端口号
    private String portNumber;

    // 输入流量
    private String inputBytes;

    // 输出流量
    private String outputBytes;

    // 输入光功率
    private String inputOpticalPower;

    // 输出光功率
    private String outputOpticalPower;

    // 端口速率
    private String portSpeed;

    // 端口状态
    private String portStatus;

}
