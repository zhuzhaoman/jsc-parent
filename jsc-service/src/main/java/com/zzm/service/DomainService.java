package com.zzm.service;

import com.zzm.pojo.bo.DeviceDomainBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.vo.DomainInfoVO;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-22
 * @description:
 **/
public interface DomainService {

    /**
     * 获得当前板卡所有域
     *
     * @param username
     */
    ReceiveSystemManagerDTO getDomainAll(String username) throws Exception;

    /**
     * 根据域类型和域id查询域信息
     * @param deviceDomainBO
     * @return
     */
    ReceiveSystemManagerDTO getDomainById(DeviceDomainBO deviceDomainBO) throws Exception;

    /**
     * 获取当前板卡所有存在槽位的域信息
     * @param username
     * @return
     */
    List<DomainInfoVO> getDomainAllBySlot(String username) throws Exception;
}
