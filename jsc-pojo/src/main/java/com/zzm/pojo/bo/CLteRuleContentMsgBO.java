package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class CLteRuleContentMsgBO {

    /* LTE规则类型 */
    public int m_u32RuleType;

    /* 字符串型规则内容 */
    public String m_strRule;

    /* 掩码 */
    public String m_strRuleMask;

    /* 规则来源 */
    public int m_u32Rule;

    /*  */
    public int m_u32RuleMask;
}
