package com.zzm.sqlite.enums;

public enum CompoundEnum {

    FALSE(0L, "false"),
    TRUE(1L, "true");

    private Long code;
    private String value;

    CompoundEnum(Long code, String value) {
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
        for (CompoundEnum p : CompoundEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }

}
