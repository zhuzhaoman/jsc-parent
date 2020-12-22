package com.zzm.pojo.bo;

import lombok.Data;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-24
 * @description:
 **/
@Data
public class WindowRuleBO {

    /* 关键字 */
    public String m_strKeyCode;

    /* 掩码 */
    public String m_strKeyCodeMask;

    /* 关键字长度 */
    public int m_u32KeyCodeLen;

    /* 业务策略组 */
    public int m_u32ServiceProfileId;

    /* 规则重启保存 */
    public int m_u32RstAclSave;

    /* 是否中标统计 */
    public int m_u32HitStat;

    /* 是否复合规则 */
    public int m_u32IsComPound;

    /* 窗口个数 */
    public int m_u32WindowNum;

    /* 规则Id */
    public int m_u32AclIndex;

    /* 规则来源 */
    public int m_u32RuleSource;

    /* 窗口宽度 */
    public int m_u32WindowLength;

    /* 窗口起始位置 */
    public int m_u32BeginPos;

    /* 规则优先级 */
    public int m_u32Priority;

    /* 老化时间 */
//    public int m_u32AgeingTime;

    /* 输入端口组 */
    public int m_u32InputPortGroupId;

    /* 复合规则类型和ID */
    public List<CCompoundMsgBO> m_tCompoundMsg;

    /* 规则类型 */
    public int m_u32RuleType;
}
