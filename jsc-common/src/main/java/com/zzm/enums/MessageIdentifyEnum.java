package com.zzm.enums;

/**
 * @author zhuzhaoman
 * @date 2020/8/19 0019 15:58
 * @description 描述
 */
public enum MessageIdentifyEnum {

    Y1(209, "Y1消息"),
    Z(243, "Z消息");


    private Integer code;
    private String msg;

    MessageIdentifyEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
