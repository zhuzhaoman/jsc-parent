package com.zzm.pojo.vo;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuzhaoman
 * @date 2020/8/10 0010 20:28
 * @description 端口状态显示
 */
@Data
public class SlotPortStatusVO {

    // 端口丝印号
    private Integer portIndex;

    // 端口号
    private Integer portNumber;

    // 端口类型：S/Q S(10G口) Q(100G口)
    private String portType;

    // 端口是否甩纤
    private boolean isExtend;

    // 端口状态
    private String portStatus;

    // 甩纤子端口
    private List<SlotPortStatusVO> slotPortStatusVOList = new ArrayList<>();
}
