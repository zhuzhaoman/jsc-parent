package com.zzm.sqlite.enums;

public enum ProtocolTypeSignalEnum {

    S1AP(0L, "s1ap"),
    DIAMETER(1L, "diameter"),
    SGS_AP(2L, "sgs-ap"),
    SIGNAL_SIP(3L, "signal-sip"),
    GTPV0(4L, "gtpv0"),
    GTPV1(5L, "gtpv1"),
    GTPV2(6L, "gtpv2"),
    NGAP(7L, "ngap"),
    HTTP2(8L, "http2");

    private Long code;
    private String value;

    ProtocolTypeSignalEnum(Long code, String value) {
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
        for (ProtocolTypeSignalEnum p : ProtocolTypeSignalEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
