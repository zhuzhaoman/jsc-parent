package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-11
 * @description:
 **/
@Data
public class RuleQueryBO {

    private String ruleType;
    private String criteria;
    private Integer page;
    private Integer pageSize;

}
