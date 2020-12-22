package com.zzm.netty;

import com.alibaba.fastjson.JSON;
import com.zzm.enums.CommonSystemManagerReceivedEnum;
import com.zzm.exception.GraceException;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.vo.MessageVO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedComponent;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import com.zzm.utils.WebSocketSendMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 15:14
 * @description netty客户端接收服务端响应数据
 */
public class ClientHandlerSync extends ChannelInboundHandlerAdapter {

    public static final Logger log = LoggerFactory.getLogger(ClientHandlerSync.class);
    private static ChannelHandlerContext ctx;
    private static ChannelPromise promise;
    private static Object data = null;

    /**
     * 与服务端连接成功会触发
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        try {
            super.channelActive(ctx);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("与服务的连接失败...");
        }
        this.ctx = ctx;
        log.info("--------------------> 与服务端连接成功 <--------------------");
    }

    /**
     * 接口到服务端发送的数据会触发
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String receiveData = (String) msg;

        log.info("接收数据：{}", receiveData);

        ReceiveSystemManagerDTO receiveSystemManagerDTO = JSON.parseObject(receiveData, ReceiveSystemManagerDTO.class);

        log.info("转换数据：{}", receiveSystemManagerDTO);

        /* 响应请求对应messageCode，用于判断对应的操作 */
        int messageCode = receiveSystemManagerDTO.getMessageCode();

        ReceiveSystemManagerDTO o = null;
        SystemManagerReceivedPolicyService systemManagerReceivedPolicyService = null;

        CommonSystemManagerReceivedEnum commonSystemManagerReceivedEnum = CommonSystemManagerReceivedEnum.fromCode(messageCode);

        if (commonSystemManagerReceivedEnum != null) {
            systemManagerReceivedPolicyService =
                    SystemManagerReceivedComponent.systemManagerPolicyServiceMap.get(commonSystemManagerReceivedEnum.getResCode());
        } else {
            systemManagerReceivedPolicyService =
                    SystemManagerReceivedComponent.systemManagerPolicyServiceMap.get(messageCode);
        }

        try {
            o = (ReceiveSystemManagerDTO) systemManagerReceivedPolicyService.dataProcessing(receiveSystemManagerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        if (o != null) {
            data = o;
            promise.setSuccess();
        }
    }

    public ChannelPromise sendMessage(Object message) {
        while (ctx == null) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("发送数据：" + message);

        promise = ctx.newPromise();
        ctx.writeAndFlush(message);

        return promise;
    }

    public Object getData() {
        return data;
    }

    /**
     * 产生异常会触发
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("netty发生异常【{}】", cause.getMessage());

        /* 向前端主动推送错误信息 */
        MessageVO message = new MessageVO(404, "错误", "客户端发生异常", "error");
        WebSocketSendMessage.sendTopicMessage(message);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
        log.error("掉线...");
    }
}
