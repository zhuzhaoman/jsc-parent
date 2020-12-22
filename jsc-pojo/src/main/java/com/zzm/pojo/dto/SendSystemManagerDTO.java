package com.zzm.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 10:28
 * @description 发送给SystemManager的数据
 */
@Data
@AllArgsConstructor
public class SendSystemManagerDTO {

    private Integer messageBlockType;
    private Integer messageIdentify;
    private Integer messageType;

    // 当前操作
    private Integer messageCode;

    // 当前用户
    private String username;

    // 域id
    private Integer domainId;

    // 域类型
    private Integer domainType;

    // 携带参数
    private Object data;

    public SendSystemManagerDTO(Integer messageBlockType, Integer messageIdentify, String username, Integer domainId, Integer domainType) {
        this.messageBlockType = messageBlockType;
        this.messageIdentify = messageIdentify;
        this.username = username;
        this.domainId = domainId;
        this.domainType = domainType;
    }

    public SendSystemManagerDTO(Integer messageBlockType, Integer messageIdentify, Integer messageType, String username, Integer domainId, Integer domainType) {
        this.messageBlockType = messageBlockType;
        this.messageIdentify = messageIdentify;
        this.messageType = messageType;
        this.username = username;
        this.domainId = domainId;
        this.domainType = domainType;
    }
}
