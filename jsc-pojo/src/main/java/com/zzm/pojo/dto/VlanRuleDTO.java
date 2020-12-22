package com.zzm.pojo.dto;

import com.zzm.pojo.bo.CVlanTypeMsgBO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class VlanRuleDTO {

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
    public List<CVlanTypeMsgDTO> m_tVlanTypeMsg;
}
