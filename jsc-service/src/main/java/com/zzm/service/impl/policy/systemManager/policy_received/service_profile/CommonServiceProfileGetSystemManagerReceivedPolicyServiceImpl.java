package com.zzm.service.impl.policy.systemManager.policy_received.service_profile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import org.springframework.stereotype.Service;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:38
 * @description 用户登录，systemManager返回数据
 */
@Service
@SuppressWarnings("all")
public class CommonServiceProfileGetSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.COMMON_SERVICE_PROFILE_GET.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        if (receiveSystemManagerDTO.getCode() == 200) {
            JSONArray jsonArray = JSONArray.parseArray(receiveSystemManagerDTO.getData().toString());
            receiveSystemManagerDTO.setData(jsonArray.get(0));
            receiveSystemManagerDTO.setMsg("业务策略获取成功！");
        }

        return receiveSystemManagerDTO;
    }
}
