package com.zzm.enums;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-21
 * @description:
 **/
public enum UpgradeModeEnum {

    INSTALL(1, "安装"),
    UPDATE(2, "更新");

    private Integer value;
    private String description;

    UpgradeModeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer value() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
