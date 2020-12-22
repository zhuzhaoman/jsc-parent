package com.zzm.service.impl.policy.systemManager.policy_sending.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.exception.GraceException;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.FixCharRuleBO;
import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.bo.RuleDelBO;
import com.zzm.pojo.bo.UrlRuleBO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.impl.policy.systemManager.policy_sending.BaseSystemManagerSendingPolicyServiceImpl;
import com.zzm.utils.BaseConversionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class UrlRuleSystemManagerSendingRulePolicyServiceImpl extends BaseSystemManagerSendingPolicyServiceImpl implements SystemManagerSendingRulePolicyService {


    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "url";
    }


    public UrlRuleBO ruleHandle(UrlRuleBO urlRuleBO) throws UnsupportedEncodingException {

        String m_strKeyCode = urlRuleBO.getM_strKeyCode();
        String m_strKeyCodeMask = urlRuleBO.getM_strKeyCodeMask();

        byte[] bytes = m_strKeyCode.getBytes("US-ASCII");
        urlRuleBO.setM_strKeyCode(Base64.getEncoder().encodeToString(bytes));

        String body = m_strKeyCodeMask.substring(2);
        byte[] bytes1 = new byte[(body.length() / 2) + (body.length() % 2)];

        for (int i = 0; i < bytes1.length; i++) {
            bytes1[i] = (byte) BaseConversionUtils.hex2int("0x" + body.substring(i * 2, (i + 1) * 2));
        }
        urlRuleBO.setM_strKeyCodeMask(Base64.getEncoder().encodeToString(bytes1));

        return urlRuleBO;
    }

    @Override
    public Object addDataEncapsulation(RuleBO ruleBO) {
        UrlRuleBO urlRuleBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), UrlRuleBO.class);

        System.out.println("进入url");
        System.out.println("前端发送数据：" + ruleBO.getParam());

        try {
            urlRuleBO = ruleHandle(urlRuleBO);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            GraceException.display(e.getMessage());
        }

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_ADD.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_ADD.getCode(),
                MessageCodeEnum.URL_RULE_ADD.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                urlRuleBO);

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
                MessageCodeEnum.URL_RULE_MORE_DEL.getReqCode() :
                MessageCodeEnum.URL_RULE_SOLO_DEL.getReqCode();

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
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

    @Override
    public Object findDataEncapsulation(RuleBO ruleBO) {
        return null;
    }
}
