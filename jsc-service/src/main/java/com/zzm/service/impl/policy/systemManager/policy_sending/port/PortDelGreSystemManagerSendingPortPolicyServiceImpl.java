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
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class PortDelGreSystemManagerSendingPortPolicyServiceImpl implements SystemManagerSendingPortPolicyService {

    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "port-del-gre";
    }

    public JSONArray handle(PortBO portBO) {

        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam()));
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(0)));
        JSONArray jsonArray1 = JSONArray.parseArray(JSONObject.toJSONString(jsonObject.get("m_tIpInfo")));
        JSONObject jsonObject1 = JSONObject.parseObject(JSONObject.toJSONString(jsonArray1.get(0)));

        if (StringUtils.isNotBlank((String) jsonObject1.get("m_u32Ipv4"))) {
            Long ipv4 = BaseConversionUtils.ip2Long((String) jsonObject1.get("m_u32Ipv4"));
            jsonObject1.put("m_u32Ipv4", ipv4);
        } else {
            String ipv6 = IPUtils.ipToBase64((String) jsonObject1.get("m_strIpv6"));
            jsonObject1.put("m_strIpv6", ipv6);
        }

        jsonArray1.set(0, jsonObject1);
        jsonObject.put("m_tIpInfo", jsonArray1);
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
                MessageCodeEnum.INTERFACE_DEL_GRE.getReqCode(),
                portBO.getUsername(),
                portBO.getDomainId(),
                portBO.getDomainType(),
                param);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

}
