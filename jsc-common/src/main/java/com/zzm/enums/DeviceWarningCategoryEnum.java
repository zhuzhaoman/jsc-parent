package com.zzm.enums;

public enum DeviceWarningCategoryEnum {

    FAN_ERROR(1, "风扇告警"),
    POWER_SUPPLY_ERROR(2, "电源告警"),
    TEMPERATURE_ERROR(3, "温度告警"),
    CARD_ERROR(4, "板卡告警"),
    PORT_STATUS_ERROR(5, "端口状态告警"),
    PORT_ERROR(6, "丢错包告警");


    private Integer code;
    private String msg;

    DeviceWarningCategoryEnum(Integer code, String msg) {
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

    public static DeviceWarningCategoryEnum fromValue(Integer code)
    {
        for (DeviceWarningCategoryEnum d : DeviceWarningCategoryEnum.values())
        {
            if (d.getCode() == code)
            {
                return d;
            }
        }
        return null;
    }

}
