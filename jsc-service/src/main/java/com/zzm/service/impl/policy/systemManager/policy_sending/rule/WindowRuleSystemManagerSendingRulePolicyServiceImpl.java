package com.zzm.service.impl.policy.systemManager.policy_sending.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.exception.GraceException;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.bo.RuleDelBO;
import com.zzm.pojo.bo.VlanRuleBO;
import com.zzm.pojo.bo.WindowRuleBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.impl.policy.systemManager.policy_sending.BaseSystemManagerSendingPolicyServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class WindowRuleSystemManagerSendingRulePolicyServiceImpl extends BaseSystemManagerSendingPolicyServiceImpl implements SystemManagerSendingRulePolicyService {


    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "window";
    }


    @Override
    public Object addDataEncapsulation(RuleBO ruleBO) {
        WindowRuleBO windowRuleBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), WindowRuleBO.class);

        System.out.println("前端发送数据：" + ruleBO.getParam());
        System.out.println("进入window");

        try {
            windowRuleBO = (WindowRuleBO) ruleHexString2Base64(windowRuleBO);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display(e.getMessage());
        }

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_ADD.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_ADD.getCode(),
                MessageCodeEnum.WINDOW_RULE_ADD.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                windowRuleBO);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

    @Override
    public Object delDataEncapsulation(RuleBO ruleBO) {
        System.out.println(JSON.toJSONString(ruleBO.getParam()));
        RuleDelBO ruleDelBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), RuleDelBO.class);

        int ruleNumber = ruleDelBO.getM_u32RuleNum();

        int ruleType = ruleNumber > 1 ?
                MessageCodeEnum.WINDOW_RULE_MORE_DEL.getReqCode() :
                MessageCodeEnum.WINDOW_RULE_SOLO_DEL.getReqCode();

        Map<String, Integer> sendMap = new HashMap<>();
        sendMap.put("m_u32AclIndex", ruleDelBO.getM_u32AclIndex());

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_DEL.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_DEL.getCode(),
                ruleType,
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                sendMap);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

    @Override
    public Object findDataEncapsulation(RuleBO ruleBO) {
        return null;
    }
}
