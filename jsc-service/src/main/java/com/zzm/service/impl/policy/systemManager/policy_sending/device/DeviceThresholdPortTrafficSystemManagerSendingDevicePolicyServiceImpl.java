package com.zzm.service.impl.policy.systemManager.policy_sending.device;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.DeviceBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.device.SystemManagerSendingDevicePolicyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class DeviceThresholdPortTrafficSystemManagerSendingDevicePolicyServiceImpl implements SystemManagerSendingDevicePolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "device-threshold-port-traffic";
    }

    @Override
    public Object dataEncapsulation(DeviceBO deviceBO) throws Exception {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DEVICE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DEVICE_CONFIG.getCode(),
                MessageCodeEnum.DEVICE_PORT_TRAFFIC_THRESHOLD_CONFIG.getReqCode(),
                deviceBO.getUsername(),
                deviceBO.getDomainId(),
                deviceBO.getDomainType(),
                deviceBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

}
