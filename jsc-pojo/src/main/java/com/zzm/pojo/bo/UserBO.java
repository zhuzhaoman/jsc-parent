package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author zhuzhaoman
 * @date 2020/8/19 0019 15:35
 * @description 前端传递用于用户登录参数
 */
@Data
public class UserBO {

    /**
     * 域id
     */
    private Integer domainId;

    /**
     * 域类型
     */
    private Integer domainType;

    private String username;

    private String password;


    /**
     * 配置类型
     */
    private String userType;

    /**
     * 执行操作：配置、查询
     */
    private Integer userAction;

    private Object param;

}
