package com.zzm.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zhuzhaoman
 * @date: 2021-03-01
 * @description:
 **/
public enum UserEnum {

    ADMIN("admin", "admin用户"),
    USER1("user1", "user1用户"),
    USER2("user2", "user2用户"),
    USER3("user3", "user3用户"),
    USER4("user4", "user4用户");

    private String value;
    private String description;

    UserEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static UserEnum toType(String value) {
        return Stream.of(UserEnum.values())
                .filter(c -> c.value.equals(value))
                .findAny()
                .orElse(null);
    }

    public static List<String> userList() {
        return Stream.of(UserEnum.values())
                .map(u -> u.value).collect(Collectors.toList());
    }
}
