package com.zzm.service.impl.policy.systemManager.policy_sending.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.exception.GraceException;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.*;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.pojo.dto.VlanRuleDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.impl.policy.systemManager.policy_sending.BaseSystemManagerSendingPolicyServiceImpl;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class EthMacRuleSystemManagerSendingRulePolicyServiceImpl extends BaseSystemManagerSendingPolicyServiceImpl implements SystemManagerSendingRulePolicyService {


    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "eth-mac";
    }

    private static EthMacRuleBO ruleHandle(EthMacRuleBO ethMacRuleBO) throws UnsupportedEncodingException {
        String m_strSourceMac = ethMacRuleBO.getM_strSourceMac();
        String m_strDestinationMac = ethMacRuleBO.getM_strDestinationMac();

        if (m_strSourceMac.indexOf("ffff") != -1) {
            m_strSourceMac = IPUtils.strToBase64(m_strSourceMac);
        } else {
            String replace = m_strSourceMac.replace(":", "");
            byte[] bytes = replace.getBytes("US-ASCII");
            m_strSourceMac = Base64.getEncoder().encodeToString(bytes);
        }

        if (m_strDestinationMac.indexOf("ffff") != -1) {
            m_strDestinationMac = IPUtils.strToBase64(m_strDestinationMac);
        } else {
            String replace = m_strDestinationMac.replace(":", "");
            byte[] bytes = replace.getBytes("US-ASCII");
            m_strDestinationMac = Base64.getEncoder().encodeToString(bytes);
        }

        ethMacRuleBO.setM_strSourceMac(m_strSourceMac);
        ethMacRuleBO.setM_strDestinationMac(m_strDestinationMac);

        return ethMacRuleBO;
    }


    @Override
    public Object addDataEncapsulation(RuleBO ruleBO) {
        EthMacRuleBO ethMacRuleBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), EthMacRuleBO.class);
        System.out.println("进入eth-mac");

        System.out.println("前端发送数据：" + ruleBO.getParam());

        try {
            ethMacRuleBO = ruleHandle(ethMacRuleBO);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            GraceException.display(e.getMessage());
        }

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_ADD.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_ADD.getCode(),
                MessageCodeEnum.ETHMAC_RULE_ADD.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                ethMacRuleBO);

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
                MessageCodeEnum.ETHMAC_RULE_MORE_DEL.getReqCode() :
                MessageCodeEnum.ETHMAC_RULE_SOLO_DEL.getReqCode();

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