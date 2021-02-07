package com.zzm.sqlite.enums;

public enum RelatedTypeEnum {

    WINDOW(6L, "window"),
    FULL_CHAR(7L, "full-char"),
    URL(26L, "url");

    private Long code;
    private String value;

    RelatedTypeEnum(Long code, String value) {
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
        for (RelatedTypeEnum p : RelatedTypeEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
