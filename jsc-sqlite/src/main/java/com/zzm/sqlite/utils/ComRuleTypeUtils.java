package com.zzm.sqlite.utils;

import com.zzm.sqlite.enums.ComRuleTypeEnum;

import java.util.ArrayList;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-31
 * @description:
 **/
public class ComRuleTypeUtils {

    /**
     * ipv4、ipv6使用
     * @param values
     * @return
     */
    public String getComRuleType(ArrayList<Long> values) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < (values.size() / 2); i++) {
            if (values.get(i) != 0){
                String value = ComRuleTypeEnum.fromCode(values.get(i));
                String str = value + " " + values.get((values.size() / 2) + i) + " ";
                sb.append(str);
            }
        }

        return sb.length() > 0 ? sb.toString() : "null";
    }
}
