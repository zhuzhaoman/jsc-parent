package com.zzm.netty.sys_log;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: zhuzhaoman
 * @date: 2021-07-13
 * @description:
 **/
@Component
public class SysLogServer {

    @PostConstruct
    public void init() throws InterruptedException {
        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioDatagramChannel.class)
                .handler(new SysLogHandler());

        // 服务端监听在9999端口
        b.bind(8081).sync().channel().closeFuture();
    }
}
