package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Data
public class ResourcesBO {

    /**
     * 域id
     */
    private Integer domainId;

    /**
     * 域类型
     */
    private Integer domainType;

    /**
     * 当前用户
     */
    private String username;

    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 规则容量类型
     */
    private String capacityRuleType;

    /**
     * 执行操作：增、删、查
     */
    private String resourceAction;

    private Object param;

}
