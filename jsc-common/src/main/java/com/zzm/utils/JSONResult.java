package com.zzm.utils;

/**
 * 统一数据返回类型
 */
public class JSONResult {

    private Integer code;
    private String msg;
    private Object data;

    public JSONResult() {
        this.code = 200;
        this.msg = "OK";
        this.data = null;
    }

    public JSONResult(Object data) {
        this.code = 200;
        this.msg = "OK";
        this.data = data;
    }

    public JSONResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JSONResult ok() {
        return new JSONResult();
    }

    public static JSONResult ok(Object data) {
        return new JSONResult(data);
    }

    public static JSONResult ok(String msg, Object data) {
        return new JSONResult(200, msg, data);
    }

    public static JSONResult error(String msg) {
        return new JSONResult(500, msg, null);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
