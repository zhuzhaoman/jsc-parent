package com.zzm.sqlite.enums;

public enum RuleResourceEnum {

    USER_TERMINAL(0L, "UserTerminal");

    private Long code;
    private String value;

    RuleResourceEnum(Long code, String value) {
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
        for (RuleResourceEnum p : RuleResourceEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
