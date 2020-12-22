package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Data
public class RuleBO {

    /**
     * 域id
     */
    private int domainId;

    /**
     * 域类型
     */
    private int domainType;

    /**
     * 当前用户
     */
    private String username;

    /**
     * 规则类型
     */
    private String ruleType;

    /**
     * 执行操作：增、删、查
     */
    private String ruleAction;

    private Object param;

}
