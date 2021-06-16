package com.zzm.service.impl.policy.systemManager.policy_sending;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zzm.pojo.bo.IPV4RuleBO;
import com.zzm.pojo.dto.IPV4RuleDTO;
import com.zzm.utils.BaseConversionUtils;
import com.zzm.utils.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 17:15
 * @description 描述
 */
public class BaseSystemManagerSendingPolicyServiceImpl {
    public static void main(String[] args) throws IllegalAccessException {
        IPV4RuleBO ipv4RuleBO = new IPV4RuleBO();

        ipv4RuleBO.setM_u32SrcIpMask("0xffffff");
        ipv4RuleBO.setM_u32SrcIp("192.168.64.151");
        ipv4RuleBO.setM_u32DstIpMask("0xffffff");
        ipv4RuleBO.setM_u32DstIp("192.168.64.151");
        ipv4RuleBO.setM_u32ProtocolMask("0xff");
        ipv4RuleBO.setM_u32SrcPortMask("0xfff");
        ipv4RuleBO.setM_u32DstPortMask("0xfff");

        IPV4RuleDTO jsonObject = null;
        try {
            jsonObject = (IPV4RuleDTO) ruleBO2DTO(ipv4RuleBO, IPV4RuleDTO.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject.toString());
    }

    public static  JSONObject priorityHandle(Object o) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(o));
        Integer priority = jsonObject.getInteger("m_u32Priority");
        Integer comPound = jsonObject.getInteger("m_u32IsComPound");

        if (priority != null) {
            if (priority == 0) {
                jsonObject.remove("m_u32Priority");
            }
        }

        if(comPound != null) {
            if (comPound == 0) {
                jsonObject.remove("m_u32Protocol");
                jsonObject.remove("m_u32ProtocolMask");
                jsonObject.remove("m_u32SrcIp");
                jsonObject.remove("m_strSrcIpMask");
                jsonObject.remove("m_u32DstIp");
                jsonObject.remove("m_u32DstIpMask");
                jsonObject.remove("m_strDstIp");
                jsonObject.remove("m_strDstIpMask");
                jsonObject.remove("m_u32SrcPort");
                jsonObject.remove("m_u32SrcPortMask");
                jsonObject.remove("m_u32DstPort");
                jsonObject.remove("m_u32DstPortMask");
            }
        }

        return jsonObject;
    }

    /**
     * 将传入BO转换为对应的BO
     * @param ruleBO
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object ruleBO2DTO(Object ruleBO, Class<?> clazz) throws IllegalAccessException, InstantiationException {

        Object result = clazz.newInstance();
        BeanUtils.copyProperties(ruleBO, result);

        String resultStr = JSONObject.toJSONString(result, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty);
        JSONObject jsonObject = JSONObject.parseObject(resultStr);

        Class<?> aClass = ruleBO.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.getGenericType().toString().equalsIgnoreCase("class java.lang.String")) {
               if (StringUtils.isNotEmpty((String) field.get(ruleBO))) {
                   field.setAccessible(true);
                   if (field.getName().matches("m_u32(.*)Mask")) {
                       jsonObject.put(field.getName(), BaseConversionUtils.hex2Long((String) field.get(ruleBO)));
                   } else if (field.getName().matches("m_u32(.*)Ip")){
                       jsonObject.put(field.getName(), BaseConversionUtils.ip2Long((String) field.get(ruleBO)));
                   }
               }
           }
        }

        result = jsonObject.toJavaObject(clazz);

        return result;
    }

    /**
     * 将16进制String加密问base64
     * @param rule
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Object ruleHexString2Base64(Object rule) throws IllegalAccessException, InstantiationException {

        Class<?> aClass = rule.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.getGenericType().toString().equalsIgnoreCase("class java.lang.String")) {
                field.setAccessible(true);

                String content = (String) field.get(rule);
                String header = content.substring(0,2);

                if (header.equals("0x")) {
                    String body = content.substring(2);
                    byte[] bytes = new byte[(body.length() / 2) + (body.length() % 2)];

                    for (int i = 0; i < bytes.length; i++) {
                        bytes[i] = (byte) BaseConversionUtils.hex2int("0x" + body.substring(i*2, (i+1) * 2));
                    }

                    field.set(rule, Base64.getEncoder().encodeToString(bytes));
                } else {
                    field.set(rule, IPUtils.strToBase64(content));
                }
            }
        }

        return rule;
    }
}
