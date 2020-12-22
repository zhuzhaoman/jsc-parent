package com.zzm.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-09
 * @description: 进制转换
 **/
public class BaseConversionUtils {

    public static void main(String[] args) {
//        Long aLong = ip2Long("255.255.255.200");
//        System.out.println(aLong);
        Long aLong = hex2Long("#ffffff");
        System.out.println(aLong);
    }

    public static Long ip2Long(String ipString) {
        String[] ip = ipString.split("\\.");
        StringBuffer sb = new StringBuffer();
        for (String str : ip) {
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
        String valid = hex.substring(2);
        Integer result = Integer.valueOf(valid, 16);
        return result;
    }

    /**
     * 将10进制转为指定长度的2进制
     *
     * @param decNum int数据
     * @param digit  指定长度
     * @return
     */
    public static String decimal2binary(int decNum, int digit) {
        String binStr = "";
        for (int i = digit - 1; i >= 0; i--) {
            binStr += (decNum >> i) & 1;
        }
        return binStr;
    }

    /**
     * 将2进制转为10进制
     *
     * @param n 2进制
     * @return
     */
    public static int binary2decimal(int n) {
        BigInteger b = new BigInteger(String.valueOf(n), 2);
        return Integer.parseInt(b.toString());
    }

    /**
     * bps转换为GBps
     *
     * @param bit
     * @return
     */
    public static float bit2Gbps(String bit) {
        float f = Long.parseLong(bit);
        BigDecimal b = new BigDecimal(f / (1000 * 1000 * 1000));
        float result = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
        return result;
    }

    /**
     * bps转换为GBps
     *
     * @param bit
     * @return
     */
    public static float bit2Gbps(float bit) {
        BigDecimal b = new BigDecimal(bit / (1000 * 1000 * 1000));
        float result = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
        return result;
    }

}
