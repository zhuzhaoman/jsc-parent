package com.zzm.pojo.dto;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class CVlanTypeMsgDTO {

    /* vlan规则标签类型 */
    public int m_u32VlanType;

    /*  */
    public Long m_u32VlanId;

    /*  */
    public Long m_u32VlanIdMask;
}
