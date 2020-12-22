package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author zhuzhaoman
 * @date 2020/8/21 0021 15:35
 * @description 前端传递查询端口实时流量参数
 */
@Data
public class RealTimeFlowBO {

    // 域id
    private int domainId;

    // 域类型
    private int domainType;

    // 当前用户
    private String username;

    // 参数（板卡号/输入端口/输出端口）
    private String param;

    // 板卡/输入端口/输出端口
    private String type;
}
