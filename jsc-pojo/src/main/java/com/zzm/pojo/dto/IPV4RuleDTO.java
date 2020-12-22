package com.zzm.pojo.dto;

import com.zzm.pojo.bo.CCompoundMsgBO;
import lombok.Data;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-13
 * @description:
 */
@Data
public class IPV4RuleDTO {

    /* 源IP掩码 */
    public Long m_u32SrcIpMask;

    /* 目的IP掩码 */
    public Long m_u32DstIpMask;

    /* 源端口掩码 */
    public Long m_u32SrcPortMask;

    /* 目的端口掩码 */
    public Long m_u32DstPortMask;

    /* 协议掩码 */
    public Long m_u32ProtocolMask;

    /* 业务策略组 */
    public int m_u32ServiceProfileId;

    /* 规则重启保存 */
    public int m_u32RstAclSave;

    /* 是否中标统计 */
    public int m_u32HitStat;

    /* 掩码规则表示 */
    public int m_u32MaskFlag;

    /* 协议号 */
    public int m_u32Protocol;

    /* 源IP */
    public Long m_u32SrcIp;

    /* 目的IP */
    public Long m_u32DstIp;

    /* 源端口 */
    public int m_u32SrcPort;

    /* 目的端口 */
    public int m_u32DstPort;

    /* 规则ID */
    public int m_u32AclIndex;

    /* 规则来源 */
    public int m_u32RuleSource;

    /* 规则优先级 */
    public int m_u32Priority;

    /* 符合规则标志位 */
    public int m_u32CompositeFlag;

    /* 老化时间 */
//    private int m_u32AgeingTime;

    /* 批量添加删除的规则数量 */
//    private int m_u32RuleNum;

    /* 递增最小单位 */
//    private int m_u32Step;

    /* 按照Sip或Dip递增 */
//    private int m_u32Strategy;

    /* ip从右到左递增的字段位置（1-4） */
//    public int m_u32Location;

    /* 规则类型 */
    public int m_u32RuleType;

    /* 规则匹配方式 */
    public int m_u32MatchType;

    /* 输入端口组 */
    public int m_u32InputPortGroupId;

    /* 符合规则类型和ID */
    public List<CCompoundMsgBO> m_tCompoundMsg;
}
