package com.zzm.sqlite.enums;

public enum FullCharCompoundEnum {

    NORMAL(0L, "Normal"),
    COMPOUND(1L, "Compound");

    private Long code;
    private String value;

    FullCharCompoundEnum(Long code, String value) {
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
        for (FullCharCompoundEnum p : FullCharCompoundEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }

}
