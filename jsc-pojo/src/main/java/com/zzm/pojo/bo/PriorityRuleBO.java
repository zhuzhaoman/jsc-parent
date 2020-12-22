package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-03
 * @description:
 **/
@Data
public class PriorityRuleBO {

    /**
     * 规则类型
     */
    public int m_u32RuleTyp;

    /**
     * 规则优先级
     */
    public int m_u32Priority;
}
