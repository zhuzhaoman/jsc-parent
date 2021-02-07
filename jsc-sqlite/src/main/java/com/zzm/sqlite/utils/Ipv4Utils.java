package com.zzm.sqlite.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Optional;
import java.util.Set;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-28
 * @description:
 **/
public class Ipv4Utils {


    /**
     * 将 int 转换为 ip 字符串
     *
     * @param ipInt 用 int 表示的 ip 值
     * @return ip字符串，如 127.0.0.1
     */
    public static String long2Ip(Long ipInt) {

        if (ipInt == 0) {
            return "any";
        }

        String[] ipString = new String[4];
        for (int i = 0; i < 4; i++) {
            ipString[3 - i] = String.valueOf(ipInt & 255);
            ipInt >>>= 8;
        }
        return String.join(".", ipString);
    }

    /**
     * 将 ip 字符串转换为 int 类型的数字
     *
     * @param ipStr ip字符串，如 127.0.0.1
     * @return ip字符串对应的 int 值
     */
    public static Long ipToLong(String ipStr) {

        String[] ipArr = ipStr.split("\\.");
        StringBuffer sb = new StringBuffer();
        for (String str : ipArr) {
            String hexString = Integer.toHexString(Integer.parseInt(str));
            if (hexString.length() <= 1) {
                sb.append("0" + hexString);
            } else {
                sb.append(hexString);
            }
        }
        Long result = Long.valueOf(sb.toString(), 16);
        return result;
    }

    public static JSONObject queryFieldCast(String criteria) {
        System.out.println(criteria);
        JSONObject result = new JSONObject();

        JSONObject jsonObject = JSONObject.parseObject("{" + criteria + "}");
        Set<String> keys = jsonObject.keySet();

        keys.forEach(k -> {

            if (jsonObject.get(k) instanceof String) {
                if (jsonObject.get(k).equals("")) {
                    return;
                }
            }

            if(k.equals("ruleId")) {
                result.put(k, jsonObject.get(k));
                return;
            }

            if (k.equals("srcIp") || k.equals("dstIp")) {
                result.put(k, ipToLong((String) jsonObject.get(k)));
                return;
            }

            if (k.equals("protocol")) {
                result.put(k, StatusUtils.getProtocolCode((String) jsonObject.get(k)));
                return;
            }

            if (k.equals("protocolMask") || k.equals("srcIpMask") || k.equals("dstIpMask")) {
                result.put(k, BaseConversionUtils.hex2Long((String) jsonObject.get(k)));
                return;
            }

            if (k.equals("srcPort") || k.equals("dstPort")) {
                result.put(k, jsonObject.get(k));
                return;
            }

            if (k.equals("srcPortMask") || k.equals("dstPortMask")) {
                result.put(k, BaseConversionUtils.hex2Long((String) jsonObject.get(k)));
                return;
            }
        });

        return result;
    }

}
