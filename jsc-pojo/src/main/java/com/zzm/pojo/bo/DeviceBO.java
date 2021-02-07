package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-20
 * @description:
 **/
@Data
public class DeviceBO {

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
    private String deviceType;

    /**
     * 执行操作：配置、查询
     */
    private String deviceAction;

    private Object param;

}
