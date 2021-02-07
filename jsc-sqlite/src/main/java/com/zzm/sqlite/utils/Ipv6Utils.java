package com.zzm.sqlite.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-30
 * @description:
 **/
public class Ipv6Utils {

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

            if (k.equals("srcIp") || k.equals("dstIp")) {
                result.put(k, formatIp((String) jsonObject.get(k)));
                return;
            }

            if (k.equals("protocol")) {
//                result.put(k, StatusUtils.getProtocolCode((String) jsonObject.get(k)));
                result.put(k, Long.parseLong((String) jsonObject.get(k)));
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

    /**
     * 00aa00bb00cc -> 00aa:00bb:00cc
     * @param ip
     * @return
     */
    public static String ipFormat(String ip) {

        List<String> split = new ArrayList<>();

        if (ip.equals("00000000000000000000000000000000")) {
            return "any";
        }

        for (int i = 0; i < ip.length(); i+=4) {
            split.add(ip.substring(i, i+4));
        }

        return String.join(":", split);
    }

    /**
     * aa:bb:cc -> 00aa00bb00cc
     * @param ip
     * @return
     */
    public static String formatIp(String ip) {

        StringBuffer sb = new StringBuffer();
        String[] ipSplit = ip.split(":");

        Arrays.stream(ipSplit).forEach(s -> {
            sb.append(padLeft(s, 4, '0'));
        });

        return sb.toString();
    }


    /**
     * 右左补齐
     */
    public static String padRight(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, 0, src.length());
        for (int i = src.length(); i < len; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }
    /**
     * 左补齐
     */
    public static String padLeft(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, diff, src.length());
        for (int i = 0; i < diff; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }

}
