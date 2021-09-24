package com.zzm.netty.systemmanager;

import com.zzm.exception.GraceException;
import com.zzm.resource.NettyResource;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-23
 * @description:
 **/
@Component
public class ClientServerSync {

    /**
     * 客户端业务处理handler
     */
    private ClientHandlerSync clientHandlerSync = new ClientHandlerSync();

    /**
     * 事件池
     */
    private static EventLoopGroup group = null;

    @Resource
    private NettyResource nettyResource;

    /**
     * 客户端通道
     */
    private Channel clientChannel;

    public void doConnect() {

        String HOST = System.getProperty("host", nettyResource.getHost());
        int PORT = Integer.parseInt(System.getProperty("port", nettyResource.getPort()));

        try {
            group = new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    // .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65536))
                    // 如果不设置超时，连接会一直占用本地线程，端口，连接客户端一多，会导致本地端口用尽及CPU压力
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
                    .handler(new ClientChannelInitializerSync());

            // 发起同步连接操作
            ChannelFuture channelFuture = bootstrap.connect(HOST, PORT).sync();

            //注册连接事件
            channelFuture.addListener((ChannelFutureListener) future -> {
                // 如果连接成功
                if (future.isSuccess()) {
                    System.out.println("服务端[" + channelFuture.channel().localAddress().toString() + "]已连接...");
                    clientChannel = channelFuture.channel();
                } else { // 如果连接失败，尝试重新连接
                    System.out.println("服务端[" + channelFuture.channel().localAddress().toString() + "]连接失败，重新连接中...");
                    future.channel().close();
                    bootstrap.connect(HOST, PORT).sync();
                }
            });

            // 注册关闭事件
            channelFuture.channel().closeFuture().addListener(cfl -> {
                System.out.println("服务端[" + channelFuture.channel().localAddress().toString() + "]已断开...");
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
            GraceException.display(e.getMessage());
        }
    }

    /**
     * 客户端关闭
     */
    private void close() {
        //关闭客户端套接字
        if (clientChannel != null) {
            clientChannel.close();
        }
        //关闭客户端线程组
        if (group != null) {
            group.shutdownGracefully();
        }
    }

    /**
     * 客户端发送消息
     *
     * @param content
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public synchronized Object sendMessage(String content){

        if (clientChannel == null) {
            doConnect();
        } else if (!clientChannel.isActive()) {
            doConnect();
        }

        Object receiveSystemManagerDTO = null;

        ChannelPromise promise = clientHandlerSync.sendMessage(content);
        boolean await = false;

        try {
            await = promise.await(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            GraceException.display(e.getMessage());
        }

        if (await) {
            receiveSystemManagerDTO = clientHandlerSync.getData();
        }

        return receiveSystemManagerDTO;
    }
}
