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
public class HitStatSystemManagerSendingSystemPolicyServiceImpl implements SystemManagerSendingSystemPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "hit-stat";
    }

    @Override
    public Object configDataEncapsulation(SystemBO systemBO){


        SendSystemManagerDTO sendSystemManagerDTO = getSendData(systemBO);
        sendSystemManagerDTO.setMessageCode(MessageCodeEnum.SYSTEM_CONFIG_HIT_STAT.getReqCode());
        sendSystemManagerDTO.setData(systemBO.getParam());

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
        StringBuilder content = new StringBuilder(MessageCodeEnum.SYSTEM_CONFIG_HIT_STAT.getMsg());
        try {
            JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(systemBO.getParam()));
            content.append("【")
                    .append("开关:").append(params.getInteger("m_u32HitStat") == 0 ? "关" : "开")
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(systemBO.getUsername())
                .operationTitle("系统功能配置")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
