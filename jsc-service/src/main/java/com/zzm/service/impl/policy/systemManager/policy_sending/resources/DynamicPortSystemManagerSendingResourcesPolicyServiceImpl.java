package com.zzm.service.impl.policy.systemManager.policy_sending.resources;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.DeviceBO;
import com.zzm.pojo.bo.ResourcesBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.resources.SystemManagerSendingResourcesPolicyService;
import com.zzm.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class DynamicPortSystemManagerSendingResourcesPolicyServiceImpl implements SystemManagerSendingResourcesPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "dynamic-port";
    }

    @Override
    public Object configDataEncapsulation(ResourcesBO resourcesBO) {

        SendSystemManagerDTO sendSystemManagerDTO = getSendData(resourcesBO);
        sendSystemManagerDTO.setMessageCode(MessageCodeEnum.DYNAMIC_PORT_RESOURCES_CONFIG.getReqCode());
        sendSystemManagerDTO.setData(resourcesBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public Object getDataEncapsulation(ResourcesBO resourcesBO) {
        return null;
    }

    @Override
    public Object releaseDataEncapsulation(ResourcesBO resourcesBO) {
        SendSystemManagerDTO sendSystemManagerDTO = getSendData(resourcesBO);
        sendSystemManagerDTO.setMessageCode(MessageCodeEnum.DYNAMIC_PORT_RESOURCES_CONFIG.getReqCode());
        sendSystemManagerDTO.setData(resourcesBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    private SendSystemManagerDTO getSendData(ResourcesBO resourcesBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RESOURCES_CONFIG.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.RESOURCES_CONFIG.getCode(),
                resourcesBO.getUsername(),
                resourcesBO.getDomainId(),
                resourcesBO.getDomainType());

        return sendSystemManagerDTO;
    }

    @Override
    @Transactional
    public void recordConfigOrReleaseUserLog(ResourcesBO resourcesBO, boolean isConfig) {

        StringBuilder content = new StringBuilder(MessageCodeEnum.DYNAMIC_PORT_RESOURCES_CONFIG.getMsg());

        try {
            JSONObject params = JSONArray.parseArray(JSONObject.toJSONString(resourcesBO.getParam())).getJSONObject(0);
            content.append("【")
                    .append("端口ID:").append(params.getInteger("m_u32PortId"))
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(resourcesBO.getUsername())
                .operationTitle("资源配置")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }


    @Override
    public void recordGetUserLog(ResourcesBO resourcesBO) {
    }

}
