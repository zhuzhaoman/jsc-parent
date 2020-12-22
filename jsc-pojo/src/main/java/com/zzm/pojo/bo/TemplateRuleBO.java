package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-03
 * @description:
 **/
@Data
public class TemplateRuleBO {

    /**
     * 规则模板
     */
    public int m_u32TemplateType;

    /**
     * 规则模板优先级
     */
    public int m_u32Priority;
}
