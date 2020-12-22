package com.zzm.service.impl.policy.systemManager.policy_received.device;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import org.springframework.stereotype.Service;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:38
 * @description 设备信息，systemManager返回数据
 */
@Service
@SuppressWarnings("all")
public class DeviceInfoSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.DEVICE_INFO.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        int messageCode = receiveSystemManagerDTO.getMessageCode();
        MessageCodeEnum messageCodeEnum = MessageCodeEnum.fromValue(messageCode);

        int code = receiveSystemManagerDTO.getCode();

        if (code == 200) {
            receiveSystemManagerDTO.setMsg("获取设备信息成功！！！");

            String[] params = messageCodeEnum.getParams().split(",");

            JSONObject jsonObject1 = JSONObject.parseObject(receiveSystemManagerDTO.getData().toString());
            JSONObject jsonObject2 = JSONObject.parseObject(jsonObject1.get(params[0]).toString());
            Object parse = JSONObject.parse(jsonObject2.get(params[1]).toString());

            receiveSystemManagerDTO.setData(parse);
        }

        return receiveSystemManagerDTO;
    }
}
