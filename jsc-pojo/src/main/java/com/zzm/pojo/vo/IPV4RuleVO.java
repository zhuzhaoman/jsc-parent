package com.zzm.pojo.vo;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-13
 * @description:
 **/
public class IPV4RuleVO {

    /** 规则ID **/
    private int m_u32AclIndex;

    /** 规则类型 **/
    private int m_u32RuleType;

    /** 业务策略组 **/
    private int m_u32ServiceProfileId;

    /** 源IP **/
    private String m_strSrcIp;

    /** 源IP掩码 **/
    private String m_strSrcIpMask;

    /** 源端口 **/
    private int m_u32SrcPort;

    /** 源端口掩码 **/
    private int m_u32SrcPortMask;

    /** 目的IP **/
    private String m_strDstIp;

    /** 目的IP掩码 **/
    private String m_strDstIpMask;

    /** 目的端口 **/
    private int m_u32DstPort;

    /** 目的端口掩码 **/
    private int m_u32DstPortMask;

    /** 输入端口组 **/
    private int m_u32InputPortGroupId;

    /** 协议号 **/
    private int m_u32Protocol;

    /** 协议掩码 **/
    private int m_u32ProtocolMask;

    /** 规则优先级 **/
    private int m_u32Priority;

    /** 符合规则类型和ID **/
    private List m_tCompoundMsg;

    /** 符合规则标志位 **/
    private int m_u32CompositeFlag;

    /** 规则重启保存 **/
    private int m_u32RstAclSave;

    /** 是否中标统计 **/
    private int m_u32HitStat;

    /** 掩码规则表示 */
    private int m_u32MaskFlag;

    /** 规则来源 **/
    private int m_u32RuleSource;

    /** 规则匹配方式 **/
    private int m_u32MatchType;

}
