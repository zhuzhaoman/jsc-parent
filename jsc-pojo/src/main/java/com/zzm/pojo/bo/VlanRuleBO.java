package com.zzm.pojo.bo;

import lombok.Data;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class VlanRuleBO {

    /* 规则ID */
    public int m_u32AclIndex;

    /* 业务策略组 */
    public int m_u32ServiceProfileId;

    /* 输入端口组 */
    public int m_u32InputPortGroup;

    /* 规则来源 */
    public int m_u32RuleSource;

    /* 规则优先级 */
    public int m_u32Priority;

    /* 规则重启保存 */
    public int m_u32RstAclSave;

    /* 是否中标统计 */
    public int m_u32HitStat;

    /* vlan规则的标签类型和id */
    public List<CVlanTypeMsgBO> m_tVlanTypeMsg;

    public RuleSendMsgBO m_tRuleSendMsg;
}
