package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class CVlanTypeMsgBO {

    /* vlan规则标签类型 */
    public int m_u32VlanType;

    /*  */
    public String m_u32VlanId;

    /*  */
    public String m_u32VlanIdMask;
}
