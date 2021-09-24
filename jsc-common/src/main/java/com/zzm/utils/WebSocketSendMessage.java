package com.zzm.utils;

import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * 全局通用WebSocket工具类
 */
public class WebSocketSendMessage {

    public static SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 全局消息
     * @param message
     */
    public static void sendAppMessage(Object message) {
        System.out.println("推送到app页面");
        simpMessagingTemplate.convertAndSend("/app/all", message);
    }

    /**
     * 通用消息：客户端只要订阅了/topic/all主题，调用这个方法即可
     * @param message 发送的数据
     */
    public static void sendTopicMessage(Object message) {
        System.out.println("推送到topic");
        simpMessagingTemplate.convertAndSend("/topic/all", message);
    }

    public static void sendTopicImportMessage(Object message) {
        simpMessagingTemplate.convertAndSend("/topic/import", message);
    }

    public static void sendTopicSysLogMessage(Object message) {
        simpMessagingTemplate.convertAndSend("/topic/sysLog", message);
    }

    /**
     * 发送信息给指定用户
     * @param user 接受信息的用户
     * @param data 发送的数据
     */
    public static void sendMessageToUser(String user, Object data){
        System.out.println("来自服务端的消息,推送给" + user + "用户");
        simpMessagingTemplate.convertAndSendToUser(user,"/appoint", data);
    }

    /**
     * 发送信息给指定用户下的不同订阅
     * @param user 接受信息的用户
     * @param data 发送的数据
     */
    public static void sendMessageToUserSubscribe(String user, String subscribe, Object data){
        System.out.println("来自服务端的消息,推送给" + user + "用户");
        simpMessagingTemplate.convertAndSendToUser(user,"/" + subscribe, data);
    }

}