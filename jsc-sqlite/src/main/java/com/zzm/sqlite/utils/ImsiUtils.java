package com.zzm.sqlite.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Set;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
public class ImsiUtils {


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

            if (k.equals("imsi")) {
                result.put(k,jsonObject.get(k));
                return;
            }
        });

        return result;
    }
}
