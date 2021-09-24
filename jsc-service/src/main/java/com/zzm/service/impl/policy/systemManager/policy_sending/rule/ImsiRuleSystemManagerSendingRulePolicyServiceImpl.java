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
import com.zzm.pojo.bo.*;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.LogService;
import com.zzm.service.impl.policy.systemManager.policy_sending.BaseSystemManagerSendingPolicyServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class ImsiRuleSystemManagerSendingRulePolicyServiceImpl extends BaseSystemManagerSendingPolicyServiceImpl implements SystemManagerSendingRulePolicyService {


    @Resource
    private ClientServerSync clientServerSync;
    @Resource
    private LogService logService;

    @Override
    public String policyType() {
        return "imsi";
    }

    public ImsiRuleBO ruleHandle(ImsiRuleBO rule) throws UnsupportedEncodingException {

        List<CLteRuleContentMsgBO> m_tLteRuleContentMsgList = rule.getM_tLteRuleContentMsg();

        List<CLteRuleContentMsgBO> newM_tLteRuleContentMsgList = new ArrayList<>();

        for (CLteRuleContentMsgBO cLteRuleContentMsgBO : m_tLteRuleContentMsgList) {
            if (StringUtils.isNotBlank(cLteRuleContentMsgBO.getM_strRule()) &&
                    StringUtils.isNotBlank(cLteRuleContentMsgBO.getM_strRuleMask())) {

                String m_strRule = cLteRuleContentMsgBO.getM_strRule();
                String m_strRuleMask = cLteRuleContentMsgBO.getM_strRuleMask();

                byte[] bytes = m_strRule.getBytes("US-ASCII");
                m_strRule = Base64.getEncoder().encodeToString(bytes);
                cLteRuleContentMsgBO.setM_strRule(m_strRule);

                String ruleMaskBase64 = Base64.getEncoder().encodeToString(m_strRuleMask.getBytes());
                cLteRuleContentMsgBO.setM_strRuleMask(ruleMaskBase64);

                newM_tLteRuleContentMsgList.add(cLteRuleContentMsgBO);
            }
        }

        rule.setM_tLteRuleContentMsg(newM_tLteRuleContentMsgList);

        return rule;
    }

    @Override
    public Object addDataEncapsulation(RuleBO ruleBO) {
        ImsiRuleBO imsiRuleBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), ImsiRuleBO.class);

        System.out.println("进入misi");

        System.out.println("前端发送数据：" + ruleBO.getParam());

        try {
            imsiRuleBO = ruleHandle(imsiRuleBO);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display(e.getMessage());
        }

        JSONObject jsonObject = priorityHandle(imsiRuleBO);

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_ADD.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_ADD.getCode(),
                MessageCodeEnum.IMSI_RULE_ADD.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                jsonObject);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public Object delDataEncapsulation(RuleBO ruleBO){
        System.out.println(JSON.toJSONString(ruleBO.getParam()));
        RuleDelBO ruleDelBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), RuleDelBO.class);

        int ruleNumber = ruleDelBO.getM_u32RuleNum();

        int ruleType = ruleNumber > 1 ?
                MessageCodeEnum.IMSI_RULE_MORE_DEL.getReqCode() :
                MessageCodeEnum.IMSI_RULE_SOLO_DEL.getReqCode();

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_DEL.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_DEL.getCode(),
                ruleType,
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                ruleDelBO);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);

        return clientServerSync.sendMessage(content);
    }

    @Override
    public Object findDataEncapsulation(RuleBO ruleBO) {
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_DEL.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_GET.getCode(),
                MessageCodeEnum.IMSI_GET_ONE.getReqCode(),
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
            content.append(MessageCodeEnum.IMSI_RULE_ADD.getMsg());
        } else if ("del".equals(ruleBO.getRuleAction())) {
            RuleDelBO ruleDelBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), RuleDelBO.class);
            String ruleMsg = ruleDelBO.getM_u32RuleNum() > 1 ?
                    MessageCodeEnum.IMSI_RULE_MORE_DEL.getMsg() :
                    MessageCodeEnum.IMSI_RULE_SOLO_DEL.getMsg();
            content.append(ruleMsg);
        } else if ("find".equals(ruleBO.getRuleAction())) {
            content.append(MessageCodeEnum.IMSI_GET_ONE.getMsg());
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
