package com.zzm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.DeviceDomainBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.pojo.vo.DomainInfoVO;
import com.zzm.service.DomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-22
 * @description:
 **/
@Service
public class DomainServiceImpl implements DomainService {

    public static final Logger log = LoggerFactory.getLogger(DomainServiceImpl.class);

    @Resource
    private ClientServerSync clientServerSync;

    /**
     * 获得全部域信息
     *
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public ReceiveSystemManagerDTO getDomainAll(String username) {
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DOMAIN_ALL.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DOMAIN_ALL_GET.getCode(),
                MessageCodeEnum.DOMAIN_ALL_GET.getReqCode(),
                username, 0, 0, null);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        ReceiveSystemManagerDTO receiveSystemManagerDTO = (ReceiveSystemManagerDTO) clientServerSync.sendMessage(content);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO getDomainById(DeviceDomainBO deviceDomainBO) {

        // 获取域字符串对应域数值
        Integer domainType = deviceDomainBO.getDomainType();

        Map<String, Integer> data = new HashMap<>();
        data.put("domainType", deviceDomainBO.getDomainType());
        data.put("domainId", deviceDomainBO.getDomainId());

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DEVICE_DOMAIN.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DEVICE_DOMAIN.getCode(),
                MessageCodeEnum.DEVICE_DOMAIN.getReqCode(),
                deviceDomainBO.getUsername(), deviceDomainBO.getDomainId(),
                domainType, data);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) clientServerSync.sendMessage(content);

        return receiveSystemManagerDTO;
    }

    /**
     * 获取当前板卡所有存在槽位的域信息
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public List<DomainInfoVO> getDomainAllBySlot(String username) {
        ReceiveSystemManagerDTO receiveSystemManagerDTO = getDomainAll(username);

        List<DomainInfoVO> resultDomainInfoVOList = new ArrayList<DomainInfoVO>();

        List<DomainInfoVO> domainInfoVOList = (List<DomainInfoVO>) receiveSystemManagerDTO.getData();

        /**
         * 将域下面没有槽位信息的全部剔除
         */
        domainInfoVOList.stream().forEach(domainInfoVO -> {
            if (domainInfoVO.getM_tSlotMsg().size() > 0) {
                resultDomainInfoVOList.add(domainInfoVO);
            }
        });

        return resultDomainInfoVOList;
    }
}
