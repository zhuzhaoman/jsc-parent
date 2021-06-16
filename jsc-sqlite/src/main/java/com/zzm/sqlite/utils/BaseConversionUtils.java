package com.zzm.sqlite.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-30
 * @description:
 **/
public class BaseConversionUtils {

    public static void main(String[] args) {
        System.out.println(hex2Long("0xabcd"));
        System.out.println(slotIdFormat(129L));
    }

    public static String slotIdFormat(Long value) {

        if (value == 0) {
            return "";
        }

        String slotStr = Integer.toBinaryString(value.intValue());
        List<String> result = new ArrayList<>();
        for (int i = slotStr.length() - 1; i >= 0; i--) {
            char c = slotStr.charAt(i);
            if (c == '1') {
                result.add(String.valueOf(Integer.valueOf((slotStr.length() - i))));
            }
        }
        return String.join(",", result);
    }


    public static String long2Hex(Long value) {

        try {
            if (value == 0) {
                return "any";
            }

            return "0x" + Long.toHexString(value);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------------");
            System.out.println(value);
            return "";
        }
    }

    public static Long hex2Long(String hex) {
        if (hex.equals("0")) {
            return Long.parseLong(hex, 10);
        }
        String valid = hex.substring(2);
        Long result = Long.valueOf(valid, 16);
        return result;
    }


    public static int hex2int(String hex) {
        if (hex.equals("0")) {
            return Integer.parseInt(hex, 10);
        }
        String valid = hex.substring(0, 2);
        Integer result = Integer.valueOf(valid, 16);
        return result;
    }

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


}
