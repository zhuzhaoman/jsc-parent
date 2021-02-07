package com.zzm.sqlite.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-30
 * @description:
 **/
public class TimeUtils {

    public static String dateFormat(Long time) {

        String pattern ="yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(time * 1000);

        return sdf.format(date);
    }
}
