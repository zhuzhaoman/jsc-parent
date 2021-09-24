package com.zzm.service.impl.policy.systemManager.policy_sending.port_group;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.PortBO;
import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.port_group.SystemManagerSendingPortGroupPolicyService;
import com.zzm.service.LogService;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 10:53
 * @description 根据输入端口组，封装数据，发送给systemManager
 */
@Service
public class ConfigSubPortGrePackageSystemManagerSendingPortGroupServiceImpl implements SystemManagerSendingPortGroupPolicyService {

    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "sub-port-gre-package";
    }


    private JSONArray handle(PortGroupBO portGroupBO) {

        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(portGroupBO.getParam()));
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(0)));

//        String m_strGreSrcMac = jsonObject.get("m_strGreSrcMac").toString();
//        String m_strGreDstMac = jsonObject.get("m_strGreDstMac").toString();
//
//        jsonObject.put("m_strGreSrcMac", m_strGreSrcMac.replace(":", ""));
//        jsonObject.put("m_strGreDstMac", m_strGreDstMac.replace(":", ""));

        if (StringUtils.isNotBlank(jsonObject.getString("m_u32Ipv4Sip"))) {
            String srcIp = jsonObject.getString("m_u32Ipv4Sip");
            String dstIp = jsonObject.getString("m_u32Ipv4Dip");

            Long src = BaseConversionUtils.ip2Long(srcIp);
            Long dst = BaseConversionUtils.ip2Long(dstIp);

            jsonObject.put("m_u32Ipv4Sip", src);
            jsonObject.put("m_u32Ipv4Dip", dst);
        } else if (StringUtils.isNotBlank(jsonObject.getString("m_strIpv6Sip"))){
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
    public Object dataEncapsulation(PortGroupBO portGroupBO) {

        JSONArray param = handle(portGroupBO);

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.PORT_GROUP.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.PORT_GROUP_CONFIG.getCode(),
                MessageCodeEnum.PORT_GROUP_CONFIG_CHILD_GROUP_GRE.getReqCode(),
                portGroupBO.getUsername(),
                portGroupBO.getDomainId(),
                portGroupBO.getDomainType(),
                param);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public void recordUserLog(PortGroupBO portGroupBO) {
        StringBuilder content = new StringBuilder(MessageCodeEnum.PORT_GROUP_CONFIG_CHILD_GROUP_GRE.getMsg());
        try {
            JSONObject params = JSONArray.parseArray(JSONObject.toJSONString(portGroupBO.getParam())).getJSONObject(0);
            content.append("【")
                    .append("子端口ID:").append(params.getInteger("m_u32SubPortId")).append("、")
                    .append("开关:").append(params.getInteger("m_u32SubGrePackageEnable"))
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = OperationLog.builder().username(portGroupBO.getUsername())
                .operationTitle("端口管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }

}
