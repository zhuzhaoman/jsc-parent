package com.zzm.enums;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-21
 * @description:
 **/
public enum UpgradeEnum {

    SINGLE_BOARD(0, "单板"),
    COMPLETE_MACHINE(1, "整机");

    private Integer value;
    private String description;

    UpgradeEnum(Integer value, String description) {
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
