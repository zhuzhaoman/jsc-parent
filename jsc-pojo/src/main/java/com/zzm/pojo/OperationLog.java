package com.zzm.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: zhuzhaoman
 * @date: 2021-07-15
 * @description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuppressWarnings("all")
@Table(name = "operation_log")
public class OperationLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "operation_title")
    private String operationTitle;

    @Column(name = "operation_content")
    private String operationContent;

    @Column(name = "create_time")
    private Date createTime;

}
