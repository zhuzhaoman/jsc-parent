package com.zzm.sqlite.enums;

public enum ProtocolEnum {

    ANY(0L, "any"),
    TCP(6L, "TCP"),
    UDP(17L, "UDP");

    private Long code;
    private String value;

    ProtocolEnum(Long code, String value) {
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
        for (ProtocolEnum p : ProtocolEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }

    public static Long fromValue(String value) {
        for (ProtocolEnum p : ProtocolEnum.values()) {
            if (p.getValue().equals(value)) {
                return p.getCode();
            }
        }
        return null;
    }
}
