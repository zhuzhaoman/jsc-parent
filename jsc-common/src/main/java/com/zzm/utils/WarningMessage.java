package com.zzm.utils;

import java.util.List;

/**
 * @author zhuzhaoman
 * @date 2020/8/12 0012 17:07
 * @description 英文异常转中文异常
 */
public class WarningMessage {

    public static String fanMessage(List<String> params) {
        String message = String.format("风扇%s，当前转速为%s转，低于风扇固定%s转，状态异常！", params.toArray());
        return message;
    }

    public static String tempreatureMessage(List<String> params) {
        String message = String.format("当前温度为%s℃，超出设定的阈值，阈值为%s℃，状态异常！", params.toArray());
        return message;
    }

    public static String cardCPUMessage(List<String> params) {
        String message = String.format("当前CPU使用率为%s，超出设定的阈值，阈值为%s，状态异常！", params.toArray());
        return message;
    }

    public static String cardMemoryMessage(List<String> params) {
        String message = String.format("当前内存使用率为%s，超出设定的阈值，阈值为%s，状态异常！", params.toArray());
        return message;
    }

    public static String portStatusUpMessage(List<String> params) {
        String message= String.format("插槽%s端口%s链路状态为改变，当前状态为UP！", params.toArray());
        return message;
    }

    public static String portStatusDownMessage(List<String> params) {
        String message= String.format("插槽%s端口%s链路状态为改变，当前状态为DOWN！", params.toArray());
        return message;
    }
}
