package com.zzm.sqlite.enums;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-30
 * @description:
 **/
public enum HitStatEnum {

    NO_STAT(0L, "no-stat"),
    STAT(1L, "stat");

    private Long code;
    private String value;

    HitStatEnum(Long code, String value) {
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
        for (HitStatEnum p : HitStatEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
