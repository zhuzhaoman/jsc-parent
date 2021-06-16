package com.zzm.enums;

public enum CardTypeEnum {
    NETVIS_PPB3320(12, 60, "NETVIS_PPB3320","正交2.0双路NPS卡"),
    RTM(13, 90, "RTM","RTM"),
    NETVIS_BB2032(14, 32,"NETVIS_BB2032", "32口盒式汇聚分流设备"),
    NETVIS_PPB3321(18, 60, "NETVIS_PPB3321","正交2.0双路NPS卡+"),
    NETVIS_AB1048S(21, 54, "NETVIS_AB1048S","48口盒式 单路NPS + barefoot");

    private int code;
    private int portNumber;
    private String msg;
    private String info;

    CardTypeEnum(int code, int portNumber, String msg, String info) {
        this.code = code;
        this.portNumber = portNumber;
        this.msg = msg;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static CardTypeEnum fromValue(int code) {
        for (CardTypeEnum c : CardTypeEnum.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }
}
