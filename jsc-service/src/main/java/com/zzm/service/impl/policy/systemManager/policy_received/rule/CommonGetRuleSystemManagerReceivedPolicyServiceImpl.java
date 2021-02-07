package com.zzm.service.impl.policy.systemManager.policy_received.rule;

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
public class CommonGetRuleSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.COMMON_RULE_GET.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        int messageCode = receiveSystemManagerDTO.getMessageCode();
        MessageCodeEnum messageCodeEnum = MessageCodeEnum.fromValue(messageCode);

        if(receiveSystemManagerDTO.getCode() == 200) {
            receiveSystemManagerDTO.setMsg("规则查询成功！");

            String[] params = messageCodeEnum.getParams().split(",");

            JSONArray jsonArray = JSONArray.parseArray(receiveSystemManagerDTO.getData().toString());
            JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.get(0).toString());
            JSONObject jsonObject2 = JSONObject.parseObject(jsonObject1.get(params[0]).toString());
            Object parse = JSONObject.parse(jsonObject2.toString());

            receiveSystemManagerDTO.setData(parse);
        }

        return receiveSystemManagerDTO;
    }
}
