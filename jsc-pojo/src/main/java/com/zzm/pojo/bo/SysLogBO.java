package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-24
 * @description:
 **/
@Data
public class SysLogBO {

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
    private String sysLogType;

    /**
     * 执行操作：配置、查询
     */
    private Integer sysLogAction;

    private Object param;


}
