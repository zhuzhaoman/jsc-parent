package com.zzm.pojo.bo;

import lombok.Data;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class FullCharRuleBO {

    /* 关键字 */
    public String m_strKeyCode;

    /* 掩码 */
    public String m_strKeyCodeMask;

    /* 关键字长度 */
    public int m_u32KeyCodeLen;

    /* 业务策略组 */
    public int m_u32ServiceProfileId;

    /* 规则重启保存 */
    public int m_u32RstAclSave;

    /* 是否中标统计 */
    public int m_u32HitStat;

    /* 是否复合规则 */
    public int m_u32IsComPound;

    /* 规则ID */
    public int m_u32AclIndex;

    /* 规则来源 */
    public int m_u32RuleSource;

    /* 规则优先级 */
    public int m_u32Priority;

    /* 老化时间 */
//    public int m_u32AgeingTime;

    /* 输入端口组 */
    public int m_u32InputPortGroupId;

    /* 规则类型 */
//    public int m_u32RuleType;

    /* 批量删除的规则数量 */
//    public int m_u32RuleNum;

    /* 递增最小单位 */
//    public int m_u32Step;

    /* ip从右到左递增的字段位置（1-8） */
//    public int m_u32Location;

    /* 关联规则 */
    public List<CFullCharRelatedRuleMsgBO> m_tRelateRule;

    public RuleSendMsgBO m_tRuleSendMsg;
}
