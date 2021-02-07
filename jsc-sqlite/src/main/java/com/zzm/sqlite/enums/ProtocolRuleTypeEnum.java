package com.zzm.sqlite.enums;

public enum ProtocolRuleTypeEnum {

    SIGNAL(0L, "signal"),
    APPLICATION(1L, "application"),
    VOLTE(2L, "volte"),
    IPV4_SIGNAL(3L, "signal"),
    IPV4_APPLICATION(4L, "application"),
    IPV6_SIGNAL(5L, "signal"),
    IPV6_APPLICATION(6L, "application");

    private Long code;
    private String value;

    ProtocolRuleTypeEnum(Long code, String value) {
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
        for (ProtocolRuleTypeEnum p : ProtocolRuleTypeEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }


    public static Long fromValue(String value) {
        for (ProtocolRuleTypeEnum p : ProtocolRuleTypeEnum.values()) {
            if (p.getValue().equals(value)) {
                return p.getCode();
            }
        }
        return null;
    }
    
}
