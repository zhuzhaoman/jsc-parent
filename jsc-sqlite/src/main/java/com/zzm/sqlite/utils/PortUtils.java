package com.zzm.sqlite.utils;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-31
 * @description:
 **/
public class PortUtils {

    public String portFormat(Long port) {
        if (port == 0){
            return "any";
        }

        return String.valueOf(port);
    }

}
