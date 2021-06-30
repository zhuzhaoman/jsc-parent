package com.zzm.service.impl.policy.systemManager.policy_sending.device;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * @author: zhuzhaoman
 * @date: 2021-02-08
 * @description:
 **/
public class test {
    public static void main(String[] args) {
        String s = "IP:\n\nARP:\nAddress                  HWtype  HWaddress           Flags Mask            Iface\n192.8.141.200            ether   c8:5b:76:dd:09:84   C                     eth1.3\n172.17.172.40                    (incomplete)                              eth1\n10.1.5.111               ether   00:11:22:33:44:55   C                     eth1.2\n11.0.0.101                       (incomplete)                              eth1\n192.8.141.66                     (incomplete)                              eth1.3\n10.1.5.110               ether   46:10:5e:56:05:c5   C                     eth1.2\n192.8.141.3                      (incomplete)                              eth1.3\n192.8.141.2                      (incomplete)                              eth1.3\nRoute:\nDestination     Gateway         Genmask         Flags Metric Ref    Use Iface\ndefault         192.8.141.66    0.0.0.0         UG    0      0        0 eth1.3\n10.1.5.0        *               255.255.255.0   U     0      0        0 eth1.2\n11.0.0.0        *               255.255.255.0   U     0      0        0 eth1\n172.17.172.0    *               255.255.255.0   U     0      0        0 eth1\nlocalnet        *               255.255.255.0   U     0      0        0 eth1.3\n";

        String[] split = s.split("\n");

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        String key = "";
        int arrIndex = 0;
        for (int i = 0; i < split.length; i++) {
            JSONArray jsonArray1 = new JSONArray();
            String[] dataSplit = split[i].split(" ");

            System.out.println(Arrays.toString(dataSplit));
            if (dataSplit.length == 1) {

                if (StringUtils.isBlank(dataSplit[0])) {
                    continue;
                }

                if (i > 0) {
                    jsonObject.put(key, jsonArray);
                    arrIndex = 0;
                    jsonArray = new JSONArray();
                }

                key = dataSplit[0];
                continue;
            }

            for (int j = 0, l = 0; j < dataSplit.length; j++) {
                if (dataSplit[j].length() >= 1) {
                    jsonArray1.set(l, dataSplit[j]);
                    l++;
                }
            }

            jsonArray.set(arrIndex, jsonArray1);
            arrIndex++;
        }

        jsonObject.put(key, jsonArray);

        System.out.println(jsonObject.toJSONString());

    }
}
