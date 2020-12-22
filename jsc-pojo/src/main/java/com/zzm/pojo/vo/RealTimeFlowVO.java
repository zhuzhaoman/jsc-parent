package com.zzm.pojo.vo;

import lombok.Data;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 15:33
 * @description 实时流量
 */
@Data
public class RealTimeFlowVO {

    // 端口丝印号
    private int m_u32PortId;

    // 端口号
    private String portId;

    // 端口输入字节数
    private long m_u64InBytes;

    // 端口输入包数
    private long m_u64InPckts;

    // 端口输入字节速率
    private long m_u64InBitRate;

    // 端口输入包速率
    private long m_u64InPktRate;

    // 端口输入丢包数
    private long m_u64InDiscards;

    // 端口输入错包数
    private long m_u64InErrors;

    // 端口输出字节数
    private long m_u64OutBytes;

    // 端口输出包数
    private long m_u64OutPckts;

    // 端口输出字节速率
    private long m_u64OutBitRate;

    // 端口输出包速率
    private long m_u64OutPktRate;

    // 端口输出丢包数
    private long m_u64OutDiscards;

    // 端口输出错包数
    private long m_u64OutErrors;
}
