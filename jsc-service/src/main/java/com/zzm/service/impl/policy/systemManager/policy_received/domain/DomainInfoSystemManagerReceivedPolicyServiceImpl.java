package com.zzm.service.impl.policy.systemManager.policy_received.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import org.springframework.stereotype.Service;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:38
 * @description 设备域信息，systemManager返回数据
 */
@Service
@SuppressWarnings("all")
public class DomainInfoSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.DEVICE_DOMAIN.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) throws Exception {

        int messageCode = receiveSystemManagerDTO.getMessageCode();
        System.out.println(messageCode);
        System.out.println(receiveSystemManagerDTO.toString());
        MessageCodeEnum messageCodeEnum = MessageCodeEnum.fromValue(messageCode);

        int code = receiveSystemManagerDTO.getCode();

        if (code == 200) {
            receiveSystemManagerDTO.setMsg("获取域信息成功！！！");

            String[] params = messageCodeEnum.getParams().split(",");

            JSONArray objects = JSONObject.parseArray(receiveSystemManagerDTO.getData().toString());

            receiveSystemManagerDTO.setData(objects.get(0));
        }

        return receiveSystemManagerDTO;
    }
}
