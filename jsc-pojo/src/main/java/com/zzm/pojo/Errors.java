package com.zzm.pojo;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@SuppressWarnings("all")
@Table(name = "errors")
public class Errors {

    /**
     * id
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 错误分类名称
     */
    @Column(name = "title")
    private String title;

    /**
     * 错误分类
     */
    @Column(name = "category")
    private Integer category;

    /**
     * 错误内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 错误名称
     */
    @Column(name = "error_name")
    private String errorName;

    /**
     * 是否已读
     */
    @Column(name = "flag")
    private Integer flag;

    /**
     * 产生时间
     */
    @Column(name = "create_time")
    private Date createTime;

}
