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
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class PortGrePackageSystemManagerSendingPortPolicyServiceImpl implements SystemManagerSendingPortPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "port-gre-package";
    }

    private JSONArray handle(PortBO portBO) throws UnsupportedEncodingException {

        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam()));
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(0)));

        //        jsonObject.put("m_strGreSrcMac", Base64.getEncoder().encodeToString(m_strGreSrcMac.replace(":", "").getBytes("US-ASCII")));
//        jsonObject.put("m_strGreDstMac", Base64.getEncoder().encodeToString(m_strGreDstMac.replace(":", "").getBytes("US-ASCII")));

        if (StringUtils.isNotBlank(jsonObject.getString("m_strGreSrcMac"))) {
            String m_strGreSrcMac = jsonObject.getString("m_strGreSrcMac");
            String m_strGreDstMac = jsonObject.getString("m_strGreDstMac");


            jsonObject.put("m_strGreSrcMac", m_strGreSrcMac.replace(":", ""));
            jsonObject.put("m_strGreDstMac", m_strGreDstMac.replace(":", ""));
        }


        if (StringUtils.isNotBlank(jsonObject.getString("m_u32Ipv4Sip"))) {
            String srcIp = jsonObject.getString("m_u32Ipv4Sip");
            String dstIp = jsonObject.getString("m_u32Ipv4Dip");

            Long src = BaseConversionUtils.ip2Long(srcIp);
            Long dst = BaseConversionUtils.ip2Long(dstIp);

            jsonObject.put("m_u32Ipv4Sip", src);
            jsonObject.put("m_u32Ipv4Dip", dst);
        } else {
            String srcIp = jsonObject.getString("m_strIpv6Sip");
            String dstIp = jsonObject.getString("m_strIpv6Dip");

            String src = IPUtils.ipToBase64(srcIp);
            String dst = IPUtils.ipToBase64(dstIp);

            jsonObject.put("m_strIpv6Sip", src);
            jsonObject.put("m_strIpv6Dip", dst);
        }

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
                MessageCodeEnum.INTERFACE_GRE_PACKAGE.getReqCode(),
                portBO.getUsername(),
                portBO.getDomainId(),
                portBO.getDomainType(),
                param);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public void recordUserLog(PortBO portBO) {
        StringBuilder content = new StringBuilder("端口配置 >>> " + MessageCodeEnum.INTERFACE_GRE_PACKAGE.getMsg());
        try {
            JSONObject params = JSONArray.parseArray(JSONObject.toJSONString(portBO.getParam())).getJSONObject(0);
            content.append("【")
                    .append("端口ID:").append(params.getInteger("m_u32PortId")).append("、")
                    .append("开关:").append(params.getInteger("m_u32GrePackageEnable"))
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
