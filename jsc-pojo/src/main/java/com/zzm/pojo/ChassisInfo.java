package com.zzm.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-07
 * @description:
 **/
@Data
@Entity
@SuppressWarnings("all")
@Table(name = "chassis_info")
public class ChassisInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "chassis_name")
    private String chassisName;

    @Column(name = "chassis_id")
    private Integer chassisId;

    @Column(name = "chassis_ip")
    private String chassisIp;

    @Column(name = "x_axis")
    private Integer xAxis;

    @Column(name = "y_axis")
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
