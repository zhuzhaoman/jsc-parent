package com.zzm.netty.sys_log;

import com.google.gson.JsonObject;
import com.zzm.utils.WebSocketSendMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/**
 * @author: zhuzhaoman
 * @date: 2021-07-13
 * @description:
 **/
public class SysLogHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet)
            throws Exception {
        // 读取收到的数据
        ByteBuf buf = (ByteBuf) packet.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, CharsetUtil.UTF_8);
        System.out.println("【NOTE】>>>>>> 收到客户端的数据：" + body);
        WebSocketSendMessage.sendTopicSysLogMessage(body);

        // 回复一条信息给客户端
//        ctx.writeAndFlush(new DatagramPacket(
//                Unpooled.copiedBuffer("Hello，我是Server，我的时间戳是" + System.currentTimeMillis()
//                        , CharsetUtil.UTF_8)
//                , packet.sender())).sync();
    }
}
