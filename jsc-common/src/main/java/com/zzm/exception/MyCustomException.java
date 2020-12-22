package com.zzm.exception;

/**
 * 自定义异常
 * 目的：统一处理异常信息
 *      便于解耦，service与controller错误的解耦，不会被service返回的类型而限制
 */
public class MyCustomException extends RuntimeException {

    public String error;

    public MyCustomException(String error) {
        super("具体异常信息为：" + error);
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
