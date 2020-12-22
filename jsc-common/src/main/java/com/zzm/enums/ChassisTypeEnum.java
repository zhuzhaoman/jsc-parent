package com.zzm.enums;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-24
 * @description: 机箱类型
 **/
public enum ChassisTypeEnum {
    ATCA_CHASIS_SLOT2(1, 2, "ATCA 2槽机箱"),
    ATCA_CHASIS_SLOT6(2, 6, "6槽机箱"),
    ATCA_CHASIS_SLOT14(3, 14, "14槽机箱"),
    CROSS_CHASIS_SLOT2(4, 2, "CROSS 2槽机箱"),
    CROSS_CHASIS_SLOT3(5, 3, "CROSS 3槽机箱"),
    CROSS_CHASIS_SLOT8(6, 8, "CROSS 8槽机箱"),
    CROSS_CHASIS_SLOT16(7, 16, "CROSS 16槽机箱"),
    BOX_CHASIS_SLOT1(8, 1, "盒式设备，1槽机箱"),
    CHASIS_UNKNOW(255, 0, "未知类型或者未使用");


    private int code;
    private int slotNumber;
    private String msg;

    ChassisTypeEnum(int code, int slotNumber, String msg) {
        this.code = code;
        this.slotNumber = slotNumber;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ChassisTypeEnum fromValue(int code) {
        for (ChassisTypeEnum c : ChassisTypeEnum.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }
}
