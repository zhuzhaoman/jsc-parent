package com.zzm.exception;

/**
 * 优雅的处理异常，统一封装
 */
public class GraceException {

    public static void display(String error) {
        throw new MyCustomException(error);
    }

}
