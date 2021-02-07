package com.zzm.sqlite.enums;

public enum ProtocolTypeApplicationEnum {

    HTTP(0L, "http"),
    HTTPS(1L, "https"),
    FTP(2L, "ftp"),
    POP3(3L, "pop3"),
    SMTP(4L, "smtp"),
    IMAP(5L, "imap"),
    DNS(6L, "dns"),
    RADIUS(7L, "radius"),
    COAP(8L, "coap");

    private Long code;
    private String value;

    ProtocolTypeApplicationEnum(Long code, String value) {
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
        for (ProtocolTypeApplicationEnum p : ProtocolTypeApplicationEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
