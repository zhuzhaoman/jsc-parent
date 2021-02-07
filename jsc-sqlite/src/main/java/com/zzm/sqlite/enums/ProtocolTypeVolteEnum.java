package com.zzm.sqlite.enums;

public enum ProtocolTypeVolteEnum {

    SIP(0L, "sip"),
    RTP(1L, "rtp"),
    RTCP(2L, "rtcp");

    private Long code;
    private String value;

    ProtocolTypeVolteEnum(Long code, String value) {
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
        for (ProtocolTypeVolteEnum p : ProtocolTypeVolteEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
