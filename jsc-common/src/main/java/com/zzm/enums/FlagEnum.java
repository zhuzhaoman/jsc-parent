package com.zzm.enums;

public enum FlagEnum {

    NOT_LOOK(0, "未查看"),
    LOOK(1, "已查看");

    private Integer code;
    private String msg;

    FlagEnum(Integer code, String msg) {
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
