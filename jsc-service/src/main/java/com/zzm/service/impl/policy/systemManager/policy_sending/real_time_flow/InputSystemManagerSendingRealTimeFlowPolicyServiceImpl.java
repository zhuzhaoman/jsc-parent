package com.zzm.service.impl.policy.systemManager.policy_sending.real_time_flow;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.RealTimeFlowBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.real_time_flow.SystemManagerSendingRealTimeFlowPolicyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class InputSystemManagerSendingRealTimeFlowPolicyServiceImpl implements SystemManagerSendingRealTimeFlowPolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "input";
    }

    @Override
    public Object dataEncapsulation(RealTimeFlowBO realTimeFlowBO) throws Exception {

        Map<String, Integer> param = new HashMap<>();
        param.put("inputportgroupId", Integer.parseInt(realTimeFlowBO.getParam()));

        Integer domainType = realTimeFlowBO.getDomainType();

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.FIND_FLOW_BY_SLOT_OR_INPUT_OR_OUTPUT.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.FIND_FLOW_BY_INPUT_OR_OUTPUT.getCode(),
                MessageCodeEnum.FIND_FLOW_BY_INPUT.getReqCode(),
                realTimeFlowBO.getUsername(),
                realTimeFlowBO.getDomainId(),
                domainType,
                param);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }
}
