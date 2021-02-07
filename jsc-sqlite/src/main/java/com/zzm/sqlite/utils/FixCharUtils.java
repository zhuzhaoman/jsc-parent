package com.zzm.sqlite.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Set;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-30
 * @description:
 **/
public class FixCharUtils {

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

            if (k.equals("keyCode")) {
                result.put(k, formatKeyCode((String) jsonObject.get(k)));
                return;
            }

            if (k.equals("keyCodeMask")) {
                result.put(k, formatKeyCode((String) jsonObject.get(k)));
                return;
            }

            if (k.equals("beginPos")) {
                result.put(k, jsonObject.get(k));
                return;
            }
        });

        return result;
    }

    public String portFormat(Long port) {
        if (port == 0) {
            return "any";
        }

        return String.valueOf(port);
    }

    /**
     * 666 -> 0x666
     *
     * @param keyCode
     * @return
     */
    public String keyCodeFormat(String keyCode) {
        return "0x" + keyCode;
    }


    /**
     * 0x666 -> 666
     *
     * @param keyCode
     * @return
     */
    public static String formatKeyCode(String keyCode) {
        if (keyCode.contains("0x")) {
            return keyCode.substring(2);
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < keyCode.length(); i++) {
            sb.append(BaseConversionUtils.long2Hex((long) keyCode.charAt(i)).substring(2));
        }

        return sb.toString();
    }
}
