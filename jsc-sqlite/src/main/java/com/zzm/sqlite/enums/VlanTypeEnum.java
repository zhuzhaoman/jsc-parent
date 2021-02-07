package com.zzm.sqlite.enums;

public enum VlanTypeEnum {

    INNER_VLAN(1L, "inner-vlan"),
    FIRST_VLAN(2L, "first-vlan"),
    SECOND_VLAN(3L, "second-vlan"),
    THIRD_VLAN(4L, "third-vlan");

    private Long code;
    private String value;

    VlanTypeEnum(Long code, String value) {
        this.code = code;
        this.value = value;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    public static String fromCode(Long code) {
        for (VlanTypeEnum p : VlanTypeEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return null;
    }
    
}
