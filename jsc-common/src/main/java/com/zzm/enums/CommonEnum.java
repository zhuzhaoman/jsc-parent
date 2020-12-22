package com.zzm.enums;

public enum CommonEnum {

    SUCCESS(200, "成功！"),
    BODY_NOT_MATCH(400, "请求的数据格式不对！"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配！"),
    NOT_FOUND(404, "未找到资源！"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误！"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试！");

    private Integer code;
    private String msg;

    CommonEnum(Integer code, String msg) {
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
