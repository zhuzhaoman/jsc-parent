package com.zzm.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 15:14
 * @description netty客户端发送数据编码和解码
 */
public class ClientChannelInitializerSync extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        ByteBuf buf = Unpooled.copiedBuffer("\n".getBytes());
        pipeline.addLast(new DelimiterBasedFrameDecoder(65535, buf));

        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast(new ClientHandlerSync());
    }
}