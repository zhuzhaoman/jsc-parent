package com.zzm.service.impl.policy.systemManager.policy_sending.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.exception.GraceException;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.*;
import com.zzm.pojo.dto.IPV6RuleDTO;
import com.zzm.pojo.dto.ProtocolRuleDTO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.impl.policy.systemManager.policy_sending.BaseSystemManagerSendingPolicyServiceImpl;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Base64;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class ProtocolRuleSystemManagerSendingRulePolicyServiceImpl extends BaseSystemManagerSendingPolicyServiceImpl implements SystemManagerSendingRulePolicyService {


    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "protocol";
    }

    private static ProtocolRuleDTO ruleHandle(ProtocolRuleDTO rule) throws IllegalAccessException {

        Class<?> aClass = rule.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.getGenericType().toString().equalsIgnoreCase("class java.lang.String")) {
                field.setAccessible(true);

                String name = field.getName();
                System.out.println(name + ":" + field.get(rule));

                if ("m_strImsi".equals(name)) {

                    String content = (String) field.get(rule);
                    System.out.println(content);
                    String body = content.substring(2);

                    byte[] bytes = new byte[(body.length() / 2) + (body.length() % 2)];

                    for (int i = 0; i < bytes.length; i++) {
                        if ((i+1) < bytes.length) {
                            bytes[i] = (byte) BaseConversionUtils.hex2int("0x" + body.substring(i * 2, (i + 1) * 2));
                        } else {
                            bytes[i] = (byte) BaseConversionUtils.hex2int("0x" + body.substring(i * 2));
                        }
                    }

                    field.set(rule, Base64.getEncoder().encodeToString(bytes));
                }else {
                    field.set(rule, IPUtils.ipToBase64((String) field.get(rule)));
                }
            }
        }

        return rule;
    }

    @Override
    public Object addDataEncapsulation(RuleBO ruleBO) {
        ProtocolRuleBO protocolRuleBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), ProtocolRuleBO.class);

        System.out.println("进入protocol");

        System.out.println("前端发送数据：" + ruleBO.getParam());

        ProtocolRuleDTO protocolRuleDTO = null;
        try {
            protocolRuleDTO = (ProtocolRuleDTO) ruleBO2DTO(protocolRuleBO, ProtocolRuleDTO.class);
            System.out.println("转换后的数据：" + protocolRuleDTO.toString());
            protocolRuleDTO = ruleHandle(protocolRuleDTO);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display(e.getMessage());
        }

        JSONObject jsonObject = priorityHandle(protocolRuleDTO);

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_ADD.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_ADD.getCode(),
                MessageCodeEnum.PROTOCOL_RULE_ADD.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                jsonObject);

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
                MessageCodeEnum.PROTOCOL_RULE_MORE_DEL.getReqCode() :
                MessageCodeEnum.PROTOCOL_RULE_SOLO_DEL.getReqCode();

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
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_DEL.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_GET.getCode(),
                MessageCodeEnum.PROTOCOL_GET_ONE.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                ruleBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }
}
