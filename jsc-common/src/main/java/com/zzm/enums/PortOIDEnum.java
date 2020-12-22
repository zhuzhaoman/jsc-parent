package com.zzm.enums;

/**
 * @author zhuzhaoman
 * @date 2020/8/10 0010 15:30
 * @description 描述
 */
public enum PortOIDEnum {

    PORT_INDEX(".1.3.6.1.4.1.200.1.2.1.1.1", "端口号"),
    PORT_COUNT(".1.3.6.1.4.1.200.1.2.1.1.4", "端口甩纤数量"),
    PORT_EXTEND(".1.3.6.1.4.1.200.1.2.1.1.5", "端口甩纤"),
    PORT_LINK_STATUS("1.3.6.1.4.1.200.1.2.1.1.7", "端口状态"),
    PORT_SPEED(".1.3.6.1.4.1.200.1.2.1.1.11", "端口速率"),
    PORT_IN_BYTES_RATE(".1.3.6.1.4.1.200.1.2.1.1.12", "端口输入字节速率"),
    PORT_IN_BYTES(".1.3.6.1.4.1.200.1.2.1.1.14", "端口输入字节数"),
    PORT_OUT_BYTES_RATE(".1.3.6.1.4.1.200.1.2.1.1.18", "端口输出字节速率"),
    PORT_OUT_BYTES(".1.3.6.1.4.1.200.1.2.1.1.20", "端口输出字节数"),
    PORT_IN_OPTICAL_POWER(".1.3.6.1.4.1.200.1.2.1.1.9", "端口输入光功率"),
    PORT_OUT_OPTICAL_POWER(".1.3.6.1.4.1.200.1.2.1.1.10", "端口输出光功率");

    private String code;
    private String msg;

    PortOIDEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
