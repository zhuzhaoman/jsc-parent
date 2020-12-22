package com.zzm.pojo.bo;

import lombok.Data;
import java.util.Date;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 19:22
 * @description 前端传递分页查询异常信息参数
 */
@Data
public class ErrorsPagedBO {

    private Integer page;

    private Integer size;

    private Integer category;

    private Date startTime;

    private Date endTime;
}
