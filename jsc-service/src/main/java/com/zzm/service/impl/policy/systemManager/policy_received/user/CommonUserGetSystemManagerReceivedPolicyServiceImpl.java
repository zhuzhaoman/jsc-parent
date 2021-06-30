package com.zzm.service.impl.policy.systemManager.policy_received.user;

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
public class CommonUserGetSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.COMMON_USER_GET.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        if(receiveSystemManagerDTO.getCode() == 200) {
            receiveSystemManagerDTO.setMsg("获取成功！");
        }

        if (receiveSystemManagerDTO.getMessageCode() == 1794) {
            JSONArray userInfo = getUserInfo(receiveSystemManagerDTO.getData());
            receiveSystemManagerDTO.setData(userInfo);
        }

        return receiveSystemManagerDTO;
    }

    public JSONArray getUserInfo(Object data) {
        JSONArray jsonArray = JSONArray.parseArray(data.toString());
        return jsonArray;
    }
}
