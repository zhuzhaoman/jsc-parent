package com.zzm.service.impl.policy.systemManager.policy_sending.port_group;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.port_group.SystemManagerSendingPortGroupPolicyService;
import com.zzm.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class AddPortToInputGroupSystemManagerSendingPortGroupPolicyServiceImpl implements SystemManagerSendingPortGroupPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "add-port-to-input-group";
    }

    @Override
    public Object dataEncapsulation(PortGroupBO portGroupBO) {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.PORT_GROUP.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.PORT_GROUP_ADD.getCode(),
                MessageCodeEnum.PORT_GROUP_ADD_PORT_TO_INPUT_GROUP.getReqCode(),
                portGroupBO.getUsername(),
                portGroupBO.getDomainId(),
                portGroupBO.getDomainType(),
                portGroupBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    @Transactional
    public void recordUserLog(PortGroupBO portGroupBO) {
        StringBuilder content = new StringBuilder(MessageCodeEnum.PORT_GROUP_ADD_PORT_TO_INPUT_GROUP.getMsg());
        try {
            JSONObject params = JSONArray.parseArray(JSONObject.toJSONString(portGroupBO.getParam())).getJSONObject(0);
            List<Integer> m_u32PortId = params.getJSONArray("m_tPortWeightMsg").stream()
                    .map(param -> JSONObject.parseObject(param.toString())
                            .getInteger("m_u32PortId")).collect(Collectors.toList());

            content.append("【")
                    .append("输入端口组ID:").append(params.getInteger("m_u32AggPortGroup")).append("、")
                    .append("端口ID:").append(m_u32PortId.toString())
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(portGroupBO.getUsername())
                .operationTitle("端口组管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
