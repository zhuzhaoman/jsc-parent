package com.zzm.service.impl.policy.systemManager.policy_sending.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.exception.GraceException;
import com.zzm.netty.systemmanager.ClientServerSync;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.EthMacRuleBO;
import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.bo.RuleDelBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.LogService;
import com.zzm.service.impl.policy.systemManager.policy_sending.BaseSystemManagerSendingPolicyServiceImpl;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
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
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "eth-mac";
    }

    private static String maskHandle(String mask) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(mask)) {
            return "";
        }
        String body = mask.substring(2);
        byte[] bytes = body.getBytes("US-ASCII");
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static EthMacRuleBO ruleHandle(EthMacRuleBO ethMacRuleBO) throws UnsupportedEncodingException {
        String m_strSourceMac = ethMacRuleBO.getM_strSourceMac();
        String m_strSourceMask = ethMacRuleBO.getM_strSourceMacMask();
        String m_strDestinationMac = ethMacRuleBO.getM_strDestinationMac();
        String m_strDestinationMacMask = ethMacRuleBO.getM_strDestinationMacMask();

        if (m_strSourceMac.contains("ffff")) {
            m_strSourceMac = IPUtils.strToBase64(m_strSourceMac);
        } else {
            String replace = m_strSourceMac.replace(":", "");
            byte[] bytes = replace.getBytes("US-ASCII");
            m_strSourceMac = Base64.getEncoder().encodeToString(bytes);
        }


        if (m_strDestinationMac.contains("ffff")) {
            m_strDestinationMac = IPUtils.strToBase64(m_strDestinationMac);
        } else {
            String replace = m_strDestinationMac.replace(":", "");
            byte[] bytes = replace.getBytes("US-ASCII");
            m_strDestinationMac = Base64.getEncoder().encodeToString(bytes);
        }


        m_strSourceMask = maskHandle(m_strSourceMask);
        m_strDestinationMacMask = maskHandle(m_strDestinationMacMask);

        ethMacRuleBO.setM_strSourceMac(m_strSourceMac);
        ethMacRuleBO.setM_strSourceMacMask(m_strSourceMask);
        ethMacRuleBO.setM_strDestinationMac(m_strDestinationMac);
        ethMacRuleBO.setM_strDestinationMacMask(m_strDestinationMacMask);

        return ethMacRuleBO;
    }

    private JSONObject nextProtocolHandle(JSONObject data) {
        String m_u32NextProtocol = data.get("m_u32NextProtocol").toString();
        String m_u32NextProtocolMask = data.get("m_u32NextProtocolMask").toString();

        data.put("m_u32NextProtocol", BaseConversionUtils.hex2int(m_u32NextProtocol));
        data.put("m_u32NextProtocolMask", BaseConversionUtils.hex2int(m_u32NextProtocolMask));

        return data;
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

        JSONObject jsonObject = priorityHandle(ethMacRuleBO);
        JSONObject params = nextProtocolHandle(jsonObject);

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_ADD.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_ADD.getCode(),
                MessageCodeEnum.ETHMAC_RULE_ADD.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                params);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
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

        return clientServerSync.sendMessage(content);
    }

    @Override
    public Object findDataEncapsulation(RuleBO ruleBO) {
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_DEL.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_GET.getCode(),
                MessageCodeEnum.ETH_GET_ONE.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                ruleBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    @Transactional
    public void recordUserLog(RuleBO ruleBO) {
        StringBuilder content = new StringBuilder();

        if ("add".equals(ruleBO.getRuleAction())) {
            content.append(MessageCodeEnum.ETHMAC_RULE_ADD.getMsg());
        } else if ("del".equals(ruleBO.getRuleAction())) {
            RuleDelBO ruleDelBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), RuleDelBO.class);
            String ruleMsg = ruleDelBO.getM_u32RuleNum() > 1 ?
                    MessageCodeEnum.ETHMAC_RULE_MORE_DEL.getMsg() :
                    MessageCodeEnum.ETHMAC_RULE_SOLO_DEL.getMsg();
            content.append(ruleMsg);
        } else if ("find".equals(ruleBO.getRuleAction())) {
            content.append(MessageCodeEnum.ETH_GET_ONE.getMsg());
        }

        try {
            JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(ruleBO.getParam()));
            content.append("【")
                    .append("规则ID:").append(params.getInteger("m_u32AclIndex"))
                    .append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }

        OperationLog operationLog = OperationLog.builder().username(ruleBO.getUsername())
                .operationTitle("规则管理")
                .operationContent(content.toString())
                .createTime(new Date()).build();
        logService.saveUserLog(operationLog);
    }
}
