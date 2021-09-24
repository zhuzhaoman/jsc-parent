package com.zzm.service.impl.policy.systemManager.policy_sending.device;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.DeviceBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.device.SystemManagerSendingDevicePolicyService;
import com.zzm.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class DeviceNtpConfigSystemManagerSendingDevicePolicyServiceImpl implements SystemManagerSendingDevicePolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "device-ntp-config";
    }

    @Override
    public Object dataEncapsulation(DeviceBO deviceBO) throws Exception {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DEVICE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DEVICE_CONFIG.getCode(),
                MessageCodeEnum.DEVICE_NTP_CONFIG.getReqCode(),
                deviceBO.getUsername(),
                deviceBO.getDomainId(),
                deviceBO.getDomainType(),
                deviceBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    @Transactional
    public void recordUserLog(DeviceBO deviceBO) {
        StringBuilder content = new StringBuilder("设备系统配置 >>> " + MessageCodeEnum.DEVICE_NTP_CONFIG.getMsg());
        try {
            JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(deviceBO.getParam()));
            content.append("【")
                    .append("ntp地址:").append(params.getString("m_strNtpServerIp"))
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(deviceBO.getUsername())
                .operationTitle("设备信息管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }

}
