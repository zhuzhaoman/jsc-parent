package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 19:22
 * @description 前端传递查询设备域信息参数
 */
@Data
public class DeviceDomainBO {
    private String username;
    private int domainType;
    private int domainId;
}
