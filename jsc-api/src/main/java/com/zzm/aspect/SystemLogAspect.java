package com.zzm.aspect;

import com.google.gson.Gson;
import com.zzm.annotation.SystemLog;
import com.zzm.pojo.OperationLog;
import com.zzm.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-09
 * @description:
 **/
@Aspect
@Slf4j
@Component
public class SystemLogAspect {

    @Resource
    private LogService logService;

    /**
     * 自定义一注解为切点
     */
    @Pointcut("@annotation(com.zzm.annotation.SystemLog)")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录时间
        long begin = System.currentTimeMillis();

        // 执行切点
        Object proceed = joinPoint.proceed();

        // 记录时间结束
        long end = System.currentTimeMillis();

        long spendTime = end - begin;

        if (spendTime > 3000) {
            log.error("----------------------------->  end 耗时: {} 毫秒  <-----------------------------",
                    spendTime);
        } else if (spendTime > 2000) {
            log.warn("----------------------------->  end 耗时: {} 毫秒  <-----------------------------",
                    spendTime);
        } else {
            log.info("----------------------------->  end 耗时: {} 毫秒  <-----------------------------",
                    spendTime);
        }

        return proceed;
    }

    @Before("logPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);
        String username = request.getHeader("X-Token");

        try {
            OperationLog operationLog = OperationLog.builder()
                    .username(username)
                    .operationTitle(methodDescription)
                    .createTime(new Date())
                    .build();

            logService.saveUserLog(operationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("------------------------------------> Start <------------------------------------");
        log.info("URL               ：{}", request.getRequestURL().toString());
        log.info("Description       ：{}", methodDescription);
        log.info("HTTP Method       ：{}", request.getMethod());
        log.info("Class Method      ：{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("IP                ：{}", request.getRemoteAddr());
//        log.info("Request Args      ：{}", new Gson().toJson(joinPoint.getArgs()));
    }

    @After("logPointcut()")
    public void doAfter() {
        // log.info("------------------------------------>  end  <------------------------------------", System.lineSeparator());
    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint) {

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            log.error("发生异常：", e);
        }

        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    description.append(method.getAnnotation(SystemLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }
}
