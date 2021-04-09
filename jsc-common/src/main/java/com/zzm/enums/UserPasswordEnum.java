package com.zzm.enums;

/**
 * @author: zhuzhaoman
 * @date: 2021-03-03
 * @description:
 **/
public enum UserPasswordEnum {

    ADMIN("admin", "JSC@3pass0k"),
    USER1("user1", "JSC@3pass1k"),
    USER2("user2", "JSC@3pass2k"),
    USER3("user3", "JSC@3pass3k"),
    USER4("user4", "JSC@3pass4k");;

    private String user;
    private String password;

    UserPasswordEnum(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public static String getUserPassword(String user) {
        for (UserPasswordEnum value : UserPasswordEnum.values()) {
            if (value.user.equals(user)) {
                return value.password;
            }
        }

        return "";
    }
}
