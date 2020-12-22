package com.zzm.enums;

/**
 * @author zhuzhaoman
 * @date 2020/8/19 0019 16:01
 * @description 描述
 */
public enum MessageTypeEnum {

    LOGIN(1, "用户登录"),
    DEVICE_INFO(143, "设备基本信息"),
    DEVICE_DOMAIN(141, "设备域信息"),
    FIND_FLOW_BY_SLOT(137, "根据曹魏查询端口流量"),
    FIND_FLOW_BY_INPUT_OR_OUTPUT(133, "根据输入/输入端口组查询端口流量"),
    DEVICE_THRESHOLD_CONFIG(142, "设备阈值的配置"),
    DEVICE_THRESHOLD_GET(143, "设备阈值的获取"),
    DOMAIN_ALL_GET(141, "设备全部域信息"),

    /**
     * 规则下发、删除
     */
    RULE_ADD(2, "规则下发"),
    RULE_DEL(5, "规则删除(单条、多条删除)"),

    /**
     * 资源配置获取
     */
    RESOURCES_CONFIG(129, "资源配置"),
    RESOURCES_GET(130, "资源获取"),

    /**
     * 业务策略
     */
    SERVICE_PROFILE_ADD(145, "添加业务策略"),
    SERVICE_PROFILE_DEL(146, "删除业务策略"),
    SERVICE_PROFILE_GET(147, "查询业务策略"),
    SERVICE_PROFILE_CONFIG(145, "配置业务策略"),
    SERVICE_PROFILE_RECOVERY(145, "恢复业务策略默认配置"),

    /**
     * 端口组
     */
    PORT_GROUP_ADD(131, "添加端口组"),
    PORT_GROUP_DEL(132, "删除端口组"),
    PORT_GROUP_GET(133, "查询端口组"),
    PORT_GROUP_CONFIG(131, "配置端口组"),

    SYSTEM_CONFIG(8, "系统配置");


    private Integer code;
    private String msg;

    MessageTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
