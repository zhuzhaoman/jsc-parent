package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class EthMacRuleBO {

    /* 输入端口组id */
    public int m_u32InputPortGroupId;

    /* 源Mac地址 */
    public String m_strSourceMac;

    private String m_strSourceMacMask;

    /* 目的Mac地址 */
    public String m_strDestinationMac;

    public String m_strDestinationMacMask;

    /* EtherMac规则类型:0-src,1-dst,2-src+dst */
    public int m_u32RuleType;

    /* 业务策略组 */
    public int m_u32ServiceProfileId;

    /* 规则ID */
    public int m_u32AclIndex;

    /* 规则来源 */
    public int m_u32RuleSource;

    /* 规则优先级 */
    public int m_u32Priority;

    /* 规则重启保存 */
    public int m_u32RstAclSave;

    /* 是否中标统计 */
    public int m_u32HitStat;

    /* 批量添加删除的规则数量 */
    public int m_u32RuleNum;
}
