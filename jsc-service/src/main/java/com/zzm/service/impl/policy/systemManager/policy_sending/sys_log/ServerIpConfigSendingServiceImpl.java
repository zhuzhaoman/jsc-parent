package com.zzm.service.impl.policy.systemManager.policy_sending.sys_log;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.ServiceProfileBO;
import com.zzm.pojo.bo.SysLogBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.sys_log.SystemManagerSendingSysLogPolicyService;
import com.zzm.service.LogService;
import com.zzm.utils.BaseConversionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class ServerIpConfigSendingServiceImpl implements SystemManagerSendingSysLogPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "server-ip";
    }

    public JSONObject handle(Object param) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(param));


        JSONObject jsonObject1 = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("m_tSyslogConfigSetMsg")));
        String ipStr = jsonObject1.getString("m_u32SyslogIpAddress");

        if (StringUtils.isNotBlank(ipStr)) {
            Long ipLong = BaseConversionUtils.ip2Long(ipStr);
            jsonObject1.put("m_u32SyslogIpAddress", ipLong);
            jsonObject.put("m_tSyslogConfigSetMsg", jsonObject1);
        }

        return jsonObject;
    }

    @Override
    public Object configDataEncapsulation(SysLogBO sysLogBO) {

        JSONObject sendData = this.handle(sysLogBO.getParam());

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.SYSTEM_CONFIG.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.SYS_LOG_CONFIG.getCode(),
                MessageCodeEnum.SYSLOG_SERVER_IP_CONFIG.getReqCode(),
                sysLogBO.getUsername(),
                sysLogBO.getDomainId(),
                sysLogBO.getDomainType(),
                sendData);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public Object getDataEncapsulation(SysLogBO sysLogBO) {
        return null;
    }


    @Override
    @Transactional
    public void recordUserLog(SysLogBO sysLogBO) {
        StringBuilder content = new StringBuilder(MessageCodeEnum.SYSLOG_SERVER_IP_CONFIG.getMsg());
        try {
            JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(sysLogBO.getParam()));
            content.append("【")
                    .append("IP地址:").append(params.getString("m_u32SyslogIpAddress")).append("、")
                    .append("端口:").append(params.getInteger("m_u32SyslogPort"))
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(sysLogBO.getUsername())
                .operationTitle("SystemManager日志管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
