package com.zzm.exception;

import com.zzm.utils.JSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常拦截处理
 * 可以针对异常的类型进行捕获，然后返回json信息到前端
 */
@ControllerAdvice
public class GraceExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MyCustomException.class)
    public JSONResult returnMyException(MyCustomException e) {
        System.out.println("异常拦截处理器：" + e.getMessage());
        e.printStackTrace();
        return JSONResult.error(e.getError());
    }

}
