package com.zzm.service.impl.policy.systemManager.policy_sending.system;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.SystemBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.system.SystemManagerSendingSystemPolicyService;
import com.zzm.service.LogService;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
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
public class DataOriginDelSendingServiceImpl implements SystemManagerSendingSystemPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "data-origin-del";
    }

    public Object dataHandle(Object data) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data));

        Long ipv4 = BaseConversionUtils.ip2Long(jsonObject.getString("m_u32Ipv4"));
        Long ipv4Mask = BaseConversionUtils.hex2Long(jsonObject.getString("m_u32Ipv4Mask"));
        String ipv6 = IPUtils.ipToBase64(jsonObject.getString("m_strIpv6"));
        String ipv6Mask = IPUtils.ipToBase64(jsonObject.getString("m_strIpv6Mask"));

        jsonObject.put("m_u32Ipv4", ipv4);
        jsonObject.put("m_u32Ipv4Mask", ipv4Mask);
        jsonObject.put("m_strIpv6", ipv6);
        jsonObject.put("m_strIpv6Mask", ipv6Mask);

        return jsonObject;
    }

    @Override
    public Object configDataEncapsulation(SystemBO systemBO){

        Object sendData = dataHandle(systemBO.getParam());

        SendSystemManagerDTO sendSystemManagerDTO = getSendData(systemBO);
        sendSystemManagerDTO.setMessageCode(MessageCodeEnum.SYSTEM_DEL_DATA_ORIGIN.getReqCode());
        sendSystemManagerDTO.setData(sendData);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);

    }

    @Override
    public Object getDataEncapsulation(SystemBO systemBO){
        return null;
    }


    private SendSystemManagerDTO getSendData(SystemBO systemBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.SYSTEM_CONFIG.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.SYSTEM_CONFIG.getCode(),
                systemBO.getUsername(),
                systemBO.getDomainId(),
                systemBO.getDomainType());

        return sendSystemManagerDTO;
    }

    @Override
    @Transactional
    public void recordUserLog(SystemBO systemBO) {
        OperationLog operationLog = OperationLog.builder().username(systemBO.getUsername())
                .operationTitle("系统功能配置")
                .operationContent(MessageCodeEnum.SYSTEM_DEL_DATA_ORIGIN.getMsg())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
