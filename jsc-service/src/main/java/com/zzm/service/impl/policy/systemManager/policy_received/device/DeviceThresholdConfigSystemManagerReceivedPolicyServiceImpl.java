package com.zzm.service.impl.policy.systemManager.policy_received.device;

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
public class DeviceThresholdConfigSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.DEVICE_THRESHOLD_CONFIG.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        if (receiveSystemManagerDTO.getCode() == 200) {
            receiveSystemManagerDTO.setMsg("配置设备阈值成功！！！");
        }

        receiveSystemManagerDTO.setData(null);


        return receiveSystemManagerDTO;
    }
}
