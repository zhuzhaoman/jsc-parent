package com.zzm.pojo.dto;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class ProtocolRuleDTO {

    /* 规则ID */
    public int m_u32AclIndex;

    /* 业务策略组 */
    public int m_u32ServiceProfileId;

    /* 应用层协议类型/信令协议类型 */
    public int m_u32ProtocolType;

    /* 掩码 */
    public String m_strImsi;

    /* 输入端口组 */
    public int m_u32InputPortGroup;

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

    /* 源IP */
    public String m_strSrcIp;

    /* 目的IP */
    public String m_strDstIp;

    /* 源IP掩码 */
    public String m_strSrcIpMask;

    /* 目的IP掩码 */
    public String m_strDstIpMask;

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

    /* 规则类型 */
    public int m_u32RuleType;

    /* 规则来源 */
    public int m_u32RuleSource;

    /* 规则优先级 */
    public int m_u32Priority;

    /* 规则重启保存 */
    public int m_u32RstAclSave;

    /* 是否中标统计 */
    public int m_u32HitStat;

    /* 规则数量 */
//    public int m_u32RuleNum;

    /* 按照Sip或者Dip递增 */
    public int m_u32Strategy;

    /* ip从右到左递增的字段位置（1-4） */
//    public int m_u32Location;

    /* 递增最小单位 */
//    public int m_u32Step;
}
