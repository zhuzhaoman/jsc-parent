package com.zzm.service.impl.policy.systemManager.policy_sending.port;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.PortBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.port.SystemManagerSendingPortPolicyService;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class PortGreTerminateSystemManagerSendingPortPolicyServiceImpl implements SystemManagerSendingPortPolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "port-gre-terminate";
    }


    @Override
    public Object dataEncapsulation(PortBO portBO) throws Exception {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.INTERFACE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.INTERFACE_CONFIG.getCode(),
                MessageCodeEnum.INTERFACE_GRE_TERMINATE.getReqCode(),
                portBO.getUsername(),
                portBO.getDomainId(),
                portBO.getDomainType(),
                portBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

}
