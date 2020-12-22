package com.zzm.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzm.utils.IPUtils;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2020-10-27
 * @description:
 **/
public class test {
    public static void main(String[] args) throws UnknownHostException {


//        InetAddress a = InetAddress.getByName("2001:0DB8:AC10:FE01:FFFF:EEEE:CCCC:AAAA");
//        InetAddress a = InetAddress.getByName("2402:3f40::8000:0:0");
//        byte[] bytes = a.getAddress();
//        System.out.println(Arrays.toString(bytes));
//        Integer.parseInt(bytes.toString());
//        String s = Base64.getEncoder().encodeToString(bytes);
//        System.out.println(s);
//
//        InetAddress byAddress = InetAddress.getByAddress(bytes);
//        System.out.println(byAddress.getHostAddress());
//
//        String FileBuf = Base64.getEncoder().encodeToString(bytes);
//        byte[] decode = Base64.getDecoder().decode("/psAgAAAAAAAAAAAAAAAAA==");
//        System.out.println(FileBuf);
//        System.out.println(InetAddress.getByAddress(decode).getHostAddress());
        System.out.println(IPUtils.base64ToIp("AAAAAAAAAAAAAAAAAAAAAA=="));
    }
}
