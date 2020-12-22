package com.zzm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author zhuzhaoman
 * @date 2020/6/14 14:21:47
 * @description WebSocket配置
 */
@SuppressWarnings("all")
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册stomp的端点
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        /**
         * 允许使用socketJs方式访问，访问点为websocket，允许跨域
         * 网页上我们可以通过http://localhost:8080/websocket来访问服务器的WebSocket连接
         */
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * 配置信息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        /**
         * 订阅Broker名称
         * /topic代表发布方，即群发
         * /user代表点对点，即发送给指定用户
         *
         * 表示设置消息代理的前缀，即如果消息的前缀是"/topic"，就会将消息转发给消息代理
         * 再由消息代理将消息广播给当前连接的客户端
         */
        registry.enableSimpleBroker("/topic", "/user");

        /**
         * 全局使用的消息前缀
         * 客户端发送消息的目的地为/app/sendTest，即对应的控制层@MessageMapping("/sendTest")
         * 客户端订阅住的地目的地为/app/subscribeTest，即对应的控制层@SubscribeMapping("/subscribeTest")
         */
        registry.setApplicationDestinationPrefixes("/app");
    }

}

