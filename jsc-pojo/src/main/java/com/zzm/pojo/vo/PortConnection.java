package com.zzm.pojo.vo;

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
@Table(name = "port_connection")
public class PortConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "target_id")
    private String targetId;

}
