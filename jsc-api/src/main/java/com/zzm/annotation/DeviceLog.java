package com.zzm.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解实现log日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeviceLog {

    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";
}
