package com.zzm.sqlite.enums;

public enum UrlRuleTypeEnum {

    URL_COMMON(26L, "url-common"),
    URL_HOST(27L, "url-host"),
    URL_URI(28L, "url-uri");

    private Long code;
    private String value;

    UrlRuleTypeEnum(Long code, String value) {
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
        for (UrlRuleTypeEnum p : UrlRuleTypeEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
