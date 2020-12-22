package com.zzm.pojo.vo;

import lombok.Data;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-22
 * @description:
 **/
@Data
public class DomainInfoVO {

    /**
     * domainId
     */
    private Integer m_u32DomainId;

    /**
     * domainType
     */
    private Integer m_u32Property;

    /**
     * 域描述
     */
    private String m_strDescription;

    private List<SlotInfoVO> m_tSlotMsg;
}
