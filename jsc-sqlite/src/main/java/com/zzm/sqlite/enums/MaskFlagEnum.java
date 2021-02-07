package com.zzm.sqlite.enums;

public enum MaskFlagEnum {

    NO_MASK(0L, "no-mask"),
    WITH_MASK(1L, "with-mask");

    private Long code;
    private String value;

    MaskFlagEnum(Long code, String value) {
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
        for (MaskFlagEnum p : MaskFlagEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }

}
