package com.zzm.enums;

public enum MessageBlockTypeEnum {

    LOGIN(12, "用户登录"),
    DEVICE_INFO(136, "设备基本信息"),
    DEVICE_DOMAIN(135, "设备域信息"),
    FIND_FLOW_BY_SLOT_OR_INPUT_OR_OUTPUT(130, "根据板卡/输入端口组/输出端口查询端口流量"),
    DEVICE_THRESHOLD_CONFIG_OR_GET(136, "设备阈值的设置/获取"),
    DOMAIN_ALL(135, "查询设备全部域信息"),
    IPV4_RULE_ADD(0, "IPV4规则下发"),
    RULE_ADD(0, "规则下发"),
    RULE_DEL(0, "规则删除"),
    RULE_GET(0, "规则删除"),
    RESOURCES_CONFIG(0, "资源配置"),
    SERVICE_PROFILE(0, "业务策略"),
    PORT_GROUP(0, "端口组"),
    SYSTEM_CONFIG(0, "系统配置"),
    INTERFACE(0, "端口管理"),
    DEVICE(0, "设备管理"),
    USER(51, "用户管理");


    private int code;
    private String msg;

    MessageBlockTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
