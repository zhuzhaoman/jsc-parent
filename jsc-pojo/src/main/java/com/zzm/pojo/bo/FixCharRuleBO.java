package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-17
 * @description:
 */
@Data
public class FixCharRuleBO {

    /* 协议 */
    public int m_u32Protocol;

    /* 协议掩码 */
    public String m_u32ProtocolMask;

    /* 关键字 */
    public String m_strKeyCode;

    /* 掩码 */
    public String m_strKeyCodeMask;

    /* 业务策略组 */
    public int m_u32ServiceProfileId;

    /* 规则重启保存 */
    public int m_u32RstAclSave;

    /* 是否中标统计 */
    public int m_u32HitStat;

    /* 是否复合规则 */
    public int m_u32IsComPound;

    /* 固定位置特征码偏移 */
    public int m_u32BeginPos;

    /* 固定位置特征码长度 */
    public int m_u32KeyCodeLen;

    /* 规则ID */
    public int m_u32AclIndex;

    /* 规则来源 */
    public int m_u32RuleSource;

    /* 规则优先级 */
    public int m_u32Priority;

    /* 批量添加删除的规则数量 */
//    public int m_u32RuleNum;

    /* 老化时间 */
//    public int m_u32AgeingTime;

    /* 递增最小单位 */
//    public int m_u32Step;

    /* 按照Sip或Dip递增 */
//    public int m_u32Strategy;

    /* 源端口 */
    public int m_u32SrcPort;

    /* 源掩码端口 */
    public String m_u32SrcPortMask;

    /* 目的端口 */
    public int m_u32DstPort;

    /* 目的端口掩码 */
    public String m_u32DstPortMask;

    /* 输入端口组 */
    public int m_u32InputPortGroupId;

    /* 输出报文动作 */
//    public int m_u32OutputPktAction;

    /* 输出报文个数 */
//    public int m_u32OutputPktNum;

    public RuleSendMsgBO m_tRuleSendMsg;
}
