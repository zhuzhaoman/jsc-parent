package com.zzm.sqlite.enums;

public enum FixCharCompoundEnum {

    NO(0L, "No"),
    YES(1L, "Yes");

    private Long code;
    private String value;

    FixCharCompoundEnum(Long code, String value) {
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
        for (FixCharCompoundEnum p : FixCharCompoundEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }

}
