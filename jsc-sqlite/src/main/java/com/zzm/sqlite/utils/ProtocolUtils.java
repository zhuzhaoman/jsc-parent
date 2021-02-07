package com.zzm.sqlite.utils;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.enums.*;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
public class ProtocolUtils {

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

            if (k.equals("ruleType")) {
                result.put(k, jsonObject.get(k));
                return;
            }
        });

        return result;
    }

    public String protocolTypeFormat(ArrayList<Long> values) {

        Long protocolTypeCode = values.get(0);
        Long ruleType = values.get(1);
        String protocolType = "";

        if (ruleType == 2L) {
            protocolType = ProtocolTypeVolteEnum.fromCode(protocolTypeCode);
        } else if (ruleType == 3L || ruleType == 5L) {
            protocolType = ProtocolTypeSignalEnum.fromCode(protocolTypeCode);
        } else if (ruleType == 4L || ruleType == 6L) {
            protocolType = ProtocolTypeApplicationEnum.fromCode(protocolTypeCode);
        }

        return protocolType;
    }

    public String ipFormat(ArrayList<Object> values) {

        Long ruleType = (Long) values.get(2);

        String ip = "";

        if (ruleType == 3 || ruleType == 4) {
            ip = ipv4Format((Long) values.get(0));
        } else if (ruleType == 5 || ruleType == 6) {
            ip = ipv6Format((String) values.get(1));
        }

        return ip;
    }


    public static String ipv6Format(String ip) {

        List<String> split = new ArrayList<>();

        if (ip.equals("00000000000000000000000000000000")) {
            return "any";
        }

        for (int i = 0; i < ip.length(); i+=4) {
            split.add(ip.substring(i, i+4));
        }

        return String.join(":", split);
    }

    public static String ipv4Format(Long ipInt) {

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

}
