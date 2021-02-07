package com.zzm.sqlite.enums;

public enum UserIdEnum {

    ADMIN(0L, "admin"),
    USER1(1L, "user1"),
    USER2(2L, "user2"),
    USER3(3L, "user3"),
    USER4(4L, "user4");

    private Long code;
    private String value;

    UserIdEnum(Long code, String value) {
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
        for (UserIdEnum p : UserIdEnum.values()) {
            if (p.getCode().longValue() == code.longValue()) {
                return p.getValue();
            }
        }
        return String.valueOf(code);
    }
    
}
