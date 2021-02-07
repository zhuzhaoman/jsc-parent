package com.zzm.sqlite.utils;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.enums.VlanTypeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-04
 * @description:
 **/
public class VlanUtils {

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

            if (k.equals("vlanType1") || k.equals("vlanType2") || k.equals("vlanType3") || k.equals("vlanType4")) {
                result.put(k, jsonObject.get(k));
                return;
            }

            if (k.equals("vlanId1") || k.equals("vlanId2") || k.equals("vlanId3") || k.equals("vlanId4")) {
                if (StringUtils.isBlank((String) jsonObject.get(k))) {
                    return;
                }
                result.put(k, BaseConversionUtils.hex2Long((String) jsonObject.get(k)));
                return;
            }

            if (k.equals("vlanIdMask1") || k.equals("vlanIdMask2") || k.equals("vlanIdMask3") || k.equals("vlanIdMask4")) {
                if (StringUtils.isBlank((String) jsonObject.get(k))) {
                    return;
                }
                result.put(k, BaseConversionUtils.hex2Long((String) jsonObject.get(k)));
                return;
            }

        });

        return result;
    }

    public String vlanTypeFormat(ArrayList<Long> values) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < (values.size()); i++) {
            if (values.get(i) != 0) {
                String value = VlanTypeEnum.fromCode(values.get(i));
                String str = value + " ";
                sb.append(str);
            }
        }

        return sb.length() > 0 ? sb.toString() : "null";
    }

    public String vlanIdFormat(ArrayList<Long> values) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < (values.size()); i++) {
            if (values.get(i) != 0) {
                String value = BaseConversionUtils.long2Hex(values.get(i));
                String str = value + " ";
                sb.append(str);
            }
        }

        return sb.length() > 0 ? sb.toString() : "null";
    }


    public String VlanIdMaskFormat(ArrayList<Long> values) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < (values.size()); i++) {
            if (values.get(i) != 0) {
                String value = BaseConversionUtils.long2Hex(values.get(i));
                String str = value + " ";
                sb.append(str);
            }
        }

        return sb.length() > 0 ? sb.toString() : "null";
    }

    public String VlanIdAndTypeAndMaskFormat(ArrayList<Long> values) {

        List<Long> vlanId = new ArrayList<>();
        List<Long> vlanType = new ArrayList<>();
        List<Long> vlanMask = new ArrayList<>();

        for (int i = 0; i < (values.size()); i++) {
            if (i < 4) {
                vlanId.add(values.get(i));
            } else if (i < 8) {
                vlanType.add(values.get(i));
            } else {
                vlanMask.add(values.get(i));
            }
        }

        StringBuffer vlanInfo = new StringBuffer();

        for (int j = 0; j < vlanId.size(); j++) {
            if (vlanId.get(j) != 0) {
                StringBuffer sb = new StringBuffer();

                sb.append(VlanTypeEnum.fromCode(vlanType.get(j)))
                        .append(":")
                        .append(BaseConversionUtils.long2Hex(vlanId.get(j)))
                        .append("/")
                        .append(BaseConversionUtils.long2Hex(vlanMask.get(j)));

                vlanInfo.append(sb.toString())
                        .append(" ");
            }
        }

        return vlanInfo.toString();
    }

}
