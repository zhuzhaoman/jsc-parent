package com.zzm.sqlite.enums;

public enum ComRuleTypeEnum {

    FIX_CHAR(4L, "fix-char"),
    WINDOW(6L, "window"),
    FULL_CHAR(7L, "full-char"),
    IMSI(11L, "imsi"),
    URL(26L, "url"),
    URL_HOST(27L, "url-host"),
    URL_URI(28L, "url-uri");

    private Long code;
    private String value;

    ComRuleTypeEnum(Long code, String value) {
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
        for (ComRuleTypeEnum p : ComRuleTypeEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
