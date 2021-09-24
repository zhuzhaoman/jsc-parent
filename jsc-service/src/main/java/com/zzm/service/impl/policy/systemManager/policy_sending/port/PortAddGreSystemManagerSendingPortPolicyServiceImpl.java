package com.zzm.service.impl.policy.systemManager.policy_sending.port;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.PortBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.port.SystemManagerSendingPortPolicyService;
import com.zzm.service.LogService;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Date;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class PortAddGreSystemManagerSendingPortPolicyServiceImpl implements SystemManagerSendingPortPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "port-add-gre";
    }

    public JSONArray handle(PortBO portBO) {

        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam()));
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(0)));
        JSONArray jsonArray1 = JSONArray.parseArray(JSONObject.toJSONString(jsonObject.get("m_tIpInfo")));
        JSONObject jsonObject1 = JSONObject.parseObject(JSONObject.toJSONString(jsonArray1.get(0)));

        if (StringUtils.isNotBlank(jsonObject1.getString("m_u32Ipv4"))) {
            Long ipv4 = BaseConversionUtils.ip2Long(jsonObject1.getString("m_u32Ipv4"));
            Long ipv4Mask = BaseConversionUtils.hex2Long(jsonObject1.getString("m_u32Ipv4Mask"));
            jsonObject1.put("m_u32Ipv4", ipv4);
            jsonObject1.put("m_u32Ipv4Mask", ipv4Mask);
        } else {
            String ipv6 = IPUtils.ipToBase64(jsonObject1.getString("m_strIpv6"));
            String ipv6Mask = IPUtils.ipToBase64(jsonObject1.getString("m_strIpv6Mask"));
            jsonObject1.put("m_strIpv6", ipv6);
            jsonObject1.put("m_strIpv6Mask", ipv6Mask);
        }

        String mac = jsonObject1.getString("m_strMac");
        String replaceStr = mac.replace(":", "");
        byte[] bytes = new byte[(replaceStr.length() / 2)];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) BaseConversionUtils.hex2int("0x" + replaceStr.substring(i * 2, (i + 1) * 2));
        }
        jsonObject1.put("m_strMac", Base64.getEncoder().encodeToString(bytes));

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
                MessageCodeEnum.INTERFACE_ADD_GRE.getReqCode(),
                portBO.getUsername(),
                portBO.getDomainId(),
                portBO.getDomainType(),
                param);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public void recordUserLog(PortBO portBO) {
        StringBuilder content = new StringBuilder("端口配置 >>> " + MessageCodeEnum.INTERFACE_ADD_GRE.getMsg());
        try {
            JSONObject params = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam())).getJSONObject(0);
            content.append("【")
                    .append("端口ID:").append(params.getInteger("m_u32PortId")).append("、")
                    .append("开关:").append(params.getInteger("m_u32GreTerminateEnable") == 0 ? "关" : "开")
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(portBO.getUsername())
                .operationTitle("端口管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }

}
