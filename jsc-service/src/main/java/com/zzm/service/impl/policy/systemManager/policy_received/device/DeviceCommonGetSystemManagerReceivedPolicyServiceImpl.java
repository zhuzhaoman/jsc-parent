package com.zzm.service.impl.policy.systemManager.policy_received.device;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import org.springframework.stereotype.Service;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:38
 * @description 设备域信息，systemManager返回数据
 */
@Service
@SuppressWarnings("all")
public class DeviceCommonGetSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.COMMON_DEVICE_GET.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        if (receiveSystemManagerDTO.getCode() == 200) {

            System.out.println("---------------");
            System.out.println(receiveSystemManagerDTO.getData().toString());

            if (receiveSystemManagerDTO.getMessageCode() == 36614) {
                JSONArray result = JSONArray.parseArray(receiveSystemManagerDTO.getData().toString());
                receiveSystemManagerDTO.setData(result);
            } else if (receiveSystemManagerDTO.getMessageCode() == 36610) {
                JSONObject result = JSONObject.parseObject(JSONObject.toJSONString(receiveSystemManagerDTO.getData()));
                result = textHandle(result);
                receiveSystemManagerDTO.setData(result);
            } else {
                JSONObject result = JSONObject.parseObject(receiveSystemManagerDTO.getData().toString());
                receiveSystemManagerDTO.setData(result);
            }

            receiveSystemManagerDTO.setMsg("设备信息获取成功！");
        }


        return receiveSystemManagerDTO;
    }

    private JSONObject textHandle(JSONObject jsonObject) {
        String msg = jsonObject.getString("m_strNetConfigMsg");



//        String msgReplace = (msg.replace("\n", "<br>")).replace(" ", "&ensp;");
//        String msgReplace = msg.replace("\n", "<br>");

//        jsonObject.put("m_strNetConfigMsg", msgReplace);

        return jsonObject;
    }
}
