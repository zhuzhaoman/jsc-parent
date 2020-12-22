package com.zzm.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhuzhaoman
 * @date 2020/8/25 0025
 * @description 用于存储接收到的异常信息，分发给不同分支处理不同业务逻辑
 */
@Data
@AllArgsConstructor
public class ErrorMessageToEachBranchDTO {

    // 标题
    private String title;

    // 内容
    private String content;

    // 分类
    private int category;

    // 错误名称
    private String errorName;

}
