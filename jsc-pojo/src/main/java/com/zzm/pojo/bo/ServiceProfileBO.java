package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-24
 * @description:
 **/
@Data
public class ServiceProfileBO {

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
     * 配置类型
     */
    private String serviceProfileType;

    /**
     * 执行操作：配置、查询
     */
    private Integer serviceProfileAction;

    private Object param;


}
