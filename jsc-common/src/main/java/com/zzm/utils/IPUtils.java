package com.zzm.utils;

import com.alibaba.druid.support.http.util.IPAddress;
import org.apache.tomcat.util.net.IPv6Utils;
import sun.net.util.IPAddressUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-17
 * @description:
 **/
public class IPUtils {


    public static String ipToBase64(String ip) {

        try {
            InetAddress a = InetAddress.getByName(ip);
            byte[] bytes = a.getAddress();
            System.out.println(Arrays.toString(bytes));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String ipMaskToBase64(String mask) {
        return null;
    }


    public static void main(String[] args) {

//        byte[] decode = Base64.getDecoder().decode("ESIzRA==");
//       String ss = "0B16212C";
//       String ss = "11223344";
//        byte[] bytes = ss.getBytes();

//        String s = strToBase64(bytes);
//        String s = Base64.getEncoder().encodeToString(bytes);
//        byte[] decode = Base64.getDecoder().decode(s);
//        String ss = new String(decode);
//        System.out.println(s);

        System.out.println(ipToBase64("88:77:66:55:44:33::"));

//        byte[] bytes = new byte[4];
//        bytes[0] = 17;
//        bytes[1] = 34;
//        bytes[2] = 51;
//        bytes[3] = 68;
//        String s = Base64.getEncoder().encodeToString(bytes);
//        System.out.println(s);

//        String s = strToBase64("EDF:ED::");
//        byte[] decode = Base64.getDecoder().decode(s);
//        String str = new String(decode);
//        System.out.println(s);

        // 90:00:A9:AF:D0:0B
        // ff:ff:ff:ff:ff:ff

//        String s = ipToBase64("90:00:A9:AF:D0:0B");
        String s = base64ToIp("ABEAAAAAAAAAAAAAAAAAAA==");
        System.out.println(s);

//        String str = "MDEwMjAzMDQwNTA2";
//        byte[] decode = Base64.getDecoder().decode(str);
//        System.out.println(Arrays.toString(decode));
//        String f = strToBase64("ffffffffffffffffffffffffffffffff");
//        System.out.println(f);
    }


    public static String strToBase64(String str) {

        byte[] bytes = str.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String base64ToIp(String base64) {
        try {
            byte[] decode = Base64.getDecoder().decode(base64);
            return InetAddress.getByAddress(decode).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }
}
