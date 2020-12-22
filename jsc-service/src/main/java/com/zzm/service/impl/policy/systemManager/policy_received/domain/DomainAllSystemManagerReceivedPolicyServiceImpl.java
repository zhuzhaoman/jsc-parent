package com.zzm.service.impl.policy.systemManager.policy_received.domain;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.vo.DomainInfoVO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-22
 * @description:
 **/
@Service
public class DomainAllSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {
    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.DOMAIN_ALL_GET.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        int messageCode = receiveSystemManagerDTO.getMessageCode();
        MessageCodeEnum messageCodeEnum = MessageCodeEnum.fromValue(messageCode);

        int code = receiveSystemManagerDTO.getCode();

        if (code == 200) {
            receiveSystemManagerDTO.setMsg("OK");

            List<DomainInfoVO> domainInfoVOList = JSONObject.parseArray(receiveSystemManagerDTO.getData().toString(), DomainInfoVO.class);
            receiveSystemManagerDTO.setData(domainInfoVOList);
        }

        return receiveSystemManagerDTO;
    }
}
