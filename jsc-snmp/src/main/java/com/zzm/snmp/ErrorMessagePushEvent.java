package com.zzm.snmp;

import org.springframework.context.ApplicationEvent;

/**
 * @author zhuzhaoman
 * @date 2020/8/25 0025
 * @description 异常消息推送监听事件
 */
public class ErrorMessagePushEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ErrorMessagePushEvent(Object source) {
        super(source);
    }
}
