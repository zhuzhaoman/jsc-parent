package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-05
 * @description:
 **/
@Data
public class ChassisBO {
    private String chassisName;
    private String chassisIp;
    private Integer chassisId;
    private Integer xAxis;
    private Integer yAxis;

    public Integer getxAxis() {
        return xAxis;
    }

    public void setxAxis(Integer xAxis) {
        this.xAxis = xAxis;
    }

    public Integer getyAxis() {
        return yAxis;
    }

    public void setyAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }
}
