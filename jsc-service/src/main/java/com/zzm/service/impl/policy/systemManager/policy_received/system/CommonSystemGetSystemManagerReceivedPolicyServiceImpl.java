package com.zzm.service.impl.policy.systemManager.policy_received.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:38
 * @description 用户登录，systemManager返回数据
 */
@Service
@SuppressWarnings("all")
public class CommonSystemGetSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.COMMON_SYSTEM_CONFIG_GET.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        if (receiveSystemManagerDTO.getCode() == 200) {

            if (MessageCodeEnum.SYSTEM_GET_DATA_ORIGIN.getResCode() == receiveSystemManagerDTO.getMessageCode()) {
                JSONArray jsonArray = this.dataOriginHandle(receiveSystemManagerDTO.getData());
                receiveSystemManagerDTO.setData(jsonArray);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(receiveSystemManagerDTO.getData().toString());
                System.out.println(jsonObject.toJSONString());
                receiveSystemManagerDTO.setData(jsonObject);
            }

            receiveSystemManagerDTO.setMsg("系统配置获取成功！");
        }

        return receiveSystemManagerDTO;
    }

    public JSONArray dataOriginHandle(Object data) {
        JSONArray jsonArray = JSONArray.parseArray(data.toString());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(i)));
            JSONObject jsonObject1 = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("m_tDataSourceInfomationMsg")));

            String ipv4 = BaseConversionUtils.long2Ip(jsonObject1.getLong("m_u32Ipv4"));
            String ipv4Mask = BaseConversionUtils.long2Ip(jsonObject1.getLong("m_u32Ipv4Mask"));
            String ipv6 = IPUtils.base64ToIp(jsonObject1.getString("m_strIpv6"));
            String ipv6Mask = IPUtils.base64ToIp(jsonObject1.getString("m_strIpv6Mask"));

            jsonObject1.put("m_u32Ipv4", ipv4);
            jsonObject1.put("m_u32Ipv4Mask", ipv4Mask);
            jsonObject1.put("m_strIpv6", ipv6);
            jsonObject1.put("m_strIpv6Mask", ipv6Mask);

            jsonObject.put("m_tDataSourceInfomationMsg", jsonObject1);
            jsonArray.set(i, jsonObject);
        }

        return jsonArray;
    }
}
