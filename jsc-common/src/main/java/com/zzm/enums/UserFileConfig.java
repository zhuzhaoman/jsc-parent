package com.zzm.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum UserFileConfig {

    ADMIN_FILE("admin", "admin_device_platform.txt,admin_device_resource.txt,admin_device_cfg.txt"),
    USER1_FILE("user1", "user1_device_cfg.txt"),
    USER2_FILE("user2", "user2_device_cfg.txt"),
    USER3_FILE("user3", "user3_device_cfg.txt"),
    USER4_FILE("user4", "user4_device_cfg.txt");

    private String user;
    private String files;

    UserFileConfig(String user, String files) {
        this.user = user;
        this.files = files;
    }

    public static List<String> getFileList(String user) {
        List<String> list = new ArrayList<>();
        for (UserFileConfig value : UserFileConfig.values()) {
            if (value.user.equals(user)) {
                String[] fileList = value.files.split(",");
                list = Arrays.asList(fileList);
                break;
            }
        }

        return list;
    }
}
