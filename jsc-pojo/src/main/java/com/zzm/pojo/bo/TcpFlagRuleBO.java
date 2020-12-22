package com.zzm.pojo.bo;

import lombok.Data;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class TcpFlagRuleBO {

    /* 紧急指针标记位 */
    public int m_u32URGFlag;

    /* 应答标记位 */
    public int m_u32ACKFlag;

    /* PUSH标记位 */
    public int m_u32PSHFlag;

    /* 复位请求标记位 */
    public int m_u32RSTFlag;

    /* 同步请求标记位 */
    public int m_u32SYNFlag;

    /* 结束请求标记位 */
    public int m_u32FINFlag;

    /* TCP载荷长度 */
    public int m_u32TcpLoadLen;

    /* 规则ID */
    public int m_u32AclIndex;

    /* 业务策略组 */
    public int m_u32ServiceProfileId;

    /* 规则来源 */
    public int m_u32RuleSource;

    /* 规则优先级 */
    public int m_u32Priority;

    /* 规则重启保存 */
    public int m_u32RstAclSave;

    /* 是否中标统计 */
    public int m_u32HitStat;

    /* 老化时间 */
//    public int m_u32AgeingTime;

    /* payload是否为不关心 */
    public int m_u32IsAny;

    /* 输入端口组 */
    public int m_u32InputPortGroupId;
}
