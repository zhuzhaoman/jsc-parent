package com.zzm.pojo.bo;

import lombok.Data;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class ImsiRuleBO {

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

    /* 批量添加删除的规则数量 */
//    public int m_u32RuleNum;

    /* 递增最小单位 */
//    public int m_u32Step;

    /* Num从右到左递增的字段位置 */
//    public int m_u32Location;

    /* 输入端口组 */
    public int m_u32InputPortGroupId;

    /* 是否复合规则 */
    public int m_u32IsComPound;

    /*  */
    public List<CLteRuleContentMsgBO> m_tLteRuleContentMsg;
}
