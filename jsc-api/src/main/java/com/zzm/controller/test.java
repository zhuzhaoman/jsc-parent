package com.zzm.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzm.utils.IPUtils;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zhuzhaoman
 * @date: 2020-10-27
 * @description:
 **/
public class test {
    public static void main(String[] args) throws UnknownHostException, ParseException {


//        InetAddress a = InetAddress.getByName("2001:0DB8:AC10:FE01:FFFF:EEEE:CCCC:AAAA");
//        InetAddress a = InetAddress.getByName("2402:3f40::8000:0:0");
//        byte[] bytes = a.getAddress();
//        System.out.println(Arrays.toString(bytes));
//        String s = Base64.getEncoder().encodeToString(bytes);
//        System.out.println(s);
//        System.out.println(Base64.getDecoder().decode(s).toString());
//
//        InetAddress byAddress = InetAddress.getByAddress(bytes);
//        System.out.println(byAddress.getHostAddress());
//
//        String FileBuf = Base64.getEncoder().encodeToString(bytes);
        byte[] decode = Base64.getDecoder().decode("/psAgAAAAAAAAAAAAAAAAA==");
//        System.out.println(FileBuf);
        System.out.println(InetAddress.getByAddress(decode).getHostAddress());
//        System.out.println(IPUtils.base64ToIp("AAAAAAAAAAAAAAAAAAAAAA=="));


//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date parse = sdf.parse("1970-01-01");

//        sdf.parse()


//        Long time = 1420809950L;
//        Date date = new Date(time);
//        Date date = new Date();

//        System.out.println(date.getTime() - parse.getTime());
//        System.out.println(date);
    }
}
