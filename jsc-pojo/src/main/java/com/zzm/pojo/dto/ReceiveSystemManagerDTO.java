package com.zzm.pojo.dto;

import lombok.Data;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 13:29
 * @description SystemManager发送给后端的数据
 */
@Data
public class ReceiveSystemManagerDTO {

    // 状态码
    private int code;

    // 描述信息
    private String msg;

    // 当前操作
    private int messageCode;

    // 当前用户
    private String username;

    // 域ID
    private Integer domainId;

    // 域类型
    private Integer domainType;

    // 携带参数
    private Object data;

}
