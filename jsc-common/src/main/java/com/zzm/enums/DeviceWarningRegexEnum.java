package com.zzm.enums;

/**
 * @author zhuzhaoman
 * @date 2020/8/12 0012 16:30
 * @description 描述
 */
public enum DeviceWarningRegexEnum {

    FAN(1, 1, "Fan(.*)", "风扇告警", "风扇告警正则表达式"),
    Power(2, 2, "Power(.*)", "电源告警", "电源告警正则表达式"),
    TEMPERATURE(3, 3, "Tempreature(.*)", "温度告警", "温度告警正则表达式"),
    CARD_CPU(4, 4, "CPU(.*)", "CPU告警", "CPU告警正则表达式"),
    CARD_MEMORY(5, 4, "Memory(.*)", "内存告警", "内存告警正则表达式"),
    PORT_STATUS_UP(6, 5, "Port(.*)up(.*)", "端口状态改为up", "端口状态改变正则表达式"),
    PORT_STATUS_DOWN(7, 5, "Port(.*)down(.*)", "端口状态改为down", "端口状态改变正则表达式"),
    PORT_INPUT_DROP_PACKETS_RARE(8, 6, "The InputDropPacketsRate(.*)", "输入丢包告警", "端口输入丢包率表达式"),
    PORT_INPUT_ERROR_PACKETS_RARE(9, 6, "The InputErrorPacketsRate(.*)", "输入错报告警", "端口输入错包率表达式"),
    PORT_OUTPUT_DROP_PACKETS_RARE(10, 6, "The OutputDropPacketsRate(.*)", "输出丢包告警", "端口输出丢包率表达式"),
    PORT_OUTPUT_ERROR_PACKETS_RARE(11, 6, "The OutputErrorPacketsRate(.*)", "输出错报告警", "端口输出错包率表达式");

    private Integer code;
    private Integer category;
    private String regex;
    private String errorName;
    private String msg;

    DeviceWarningRegexEnum(Integer code, Integer category, String regex, String errorName, String msg) {
        this.code = code;
        this.category = category;
        this.regex = regex;
        this.errorName = errorName;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
