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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class PortRouteSystemManagerSendingPortPolicyServiceImpl implements SystemManagerSendingPortPolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "port-route";
    }

    private JSONArray handle(PortBO portBO) {

        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam()));
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(0)));
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("m_tPortRoute").toString());

        String srcIp = (String) jsonObject1.get("m_u32SrcIp");
        String dstIp = (String) jsonObject1.get("m_u32DstIp");

        Long src = BaseConversionUtils.ip2Long(srcIp);
        Long dst = BaseConversionUtils.ip2Long(dstIp);

        jsonObject1.put("m_u32SrcIp", src);
        jsonObject1.put("m_u32DstIp", dst);

        jsonObject.put("m_tPortRoute", jsonObject1);

        jsonArray.set(0, jsonObject);
        return jsonArray;
    }

    @Override
    public Object dataEncapsulation(PortBO portBO) throws Exception {

        JSONArray param = handle(portBO);

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.INTERFACE.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.INTERFACE_CONFIG.getCode(),
                MessageCodeEnum.INTERFACE_ROUTE.getReqCode(),
                portBO.getUsername(),
                portBO.getDomainId(),
                portBO.getDomainType(),
                param);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }


}
