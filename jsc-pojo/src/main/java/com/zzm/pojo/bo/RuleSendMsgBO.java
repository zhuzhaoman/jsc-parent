package com.zzm.pojo.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-04-23
 * @description:
 **/
@Data
public class RuleSendMsgBO {
    public int m_u32SendAllCardFlag;
    public List<Integer> m_u32SlotId;
}
