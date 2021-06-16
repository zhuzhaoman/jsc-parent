package com.zzm.pojo.vo;

import com.zzm.pojo.ChassisInfo;
import lombok.Data;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-07
 * @description:
 **/
@Data
public class TopologyVO {
    private boolean status;
    private List<ChassisInfo> chassisInfoList;
    private List<PortConnection> portConnectionList;
}
