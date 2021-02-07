package com.zzm.sqlite.enums;

public enum EthMacRuleTypeEnum {

    SOURCE_ONLY(0L, "Source Only"),
    DESTINATION_ONLY(1L, "Destination Only"),
    SOURCE_AND_DESTINATION(2L, "Source And Destination");

    private Long code;
    private String value;

    EthMacRuleTypeEnum(Long code, String value) {
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
        for (EthMacRuleTypeEnum p : EthMacRuleTypeEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
