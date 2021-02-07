package com.zzm.sqlite.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Set;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
public class EthMacUtils {

    public static JSONObject queryFieldCast(String criteria) {
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

            if (k.equals("srcMac") || k.equals("dstMac") || k.equals("srcMacMask") || k.equals("dstMacMask")) {
                result.put(k, formatMac((String) jsonObject.get(k)));
                return;
            }
        });

        return result;
    }

    public static String macFormat(String value) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < value.length(); i += 2) {
            if (i % 4 == 0 && i > 0) {
                sb.append(":");
            }
            sb.append((char) BaseConversionUtils.hex2int(value.substring(i, i + 2)));
        }

        return sb.toString();
    }

    public static String formatMac(String value) {
        StringBuffer sb = new StringBuffer();

        String[] split = value.split(":");
        Arrays.stream(split).forEach(s -> {
            for (int i = 0; i < s.length(); i++) {
                sb.append(hexToAscii(s.charAt(i)));
            }
        });

        return sb.toString();
    }

    public static String hexToAscii(char hex) {
        return Integer.toHexString(Integer.valueOf(hex));
    }
}
