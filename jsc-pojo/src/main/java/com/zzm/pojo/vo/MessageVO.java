package com.zzm.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 13:15
 * @description 推送到前端信息提示
 */
@Data
@AllArgsConstructor
public class MessageVO {

    // 状态码
    private Integer code;

    // 标题
    private String title;

    // 描述信息
    private String msg;

    // 信息类型
    private String type;


}
