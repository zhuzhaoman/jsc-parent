package com.zzm.service.impl.policy.systemManager.policy_sending.port_group;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.port_group.SystemManagerSendingPortGroupPolicyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-30
 * @description:
 **/
@Service
public class GetInputPortGroupSystemManagerSendingPortGroupPolicyServiceImpl implements SystemManagerSendingPortGroupPolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "show-input-group";
    }

    @Override
    public Object dataEncapsulation(PortGroupBO portGroupBO) {


        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.PORT_GROUP.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.PORT_GROUP_GET.getCode(),
                MessageCodeEnum.PORT_GROUP_GET_INPUT.getReqCode(),
                portGroupBO.getUsername(),
                portGroupBO.getDomainId(),
                portGroupBO.getDomainType(),
                portGroupBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }
}
