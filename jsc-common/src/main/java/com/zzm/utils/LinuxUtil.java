package com.zzm.utils;

import com.alibaba.pelican.chaos.client.RemoteCmd;
import com.alibaba.pelican.chaos.client.RemoteCmdClientConfig;
import com.alibaba.pelican.chaos.client.impl.RemoteCmdClient;
import com.zzm.enums.UserEnum;

import java.util.Arrays;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-03-01
 * @description:
 **/
public class LinuxUtil {

    public static List<String> adminFileName = Arrays.asList("admin_device_platform.txt", "admin_device_resource.txt", "admin_device_cfg.txt");
    private static List<String> importCmd = Arrays.asList("su admin", "JSC@3pass0k", "configure terminal" ,"config");
    private static List<String> exportCmd = Arrays.asList("su admin", "JSC@3pass0k", "configure terminal" ,"device");

    public static String commandExecution(String ip, String username, String password, String[] instruct) {
        RemoteCmdClientConfig remoteCmdClientConfig = new RemoteCmdClientConfig();
        remoteCmdClientConfig.setIp(ip);
        remoteCmdClientConfig.setUserName(username);
        remoteCmdClientConfig.setPassword(password);

        RemoteCmdClient remoteCmdClient = new RemoteCmdClient(remoteCmdClientConfig);

        return remoteCmdClient.execCmdGetString(new RemoteCmd(instruct));
    }

    public static boolean loadFileAdmin(String ip, String username, String password) {
        try {
            adminFileName.forEach(name -> {
                List<String> tempList = importCmd;
                tempList.add("load file " + name);

                String[] tempCmd = tempList.toArray(new String[0]);
                String result = commandExecution(ip, username, password, tempCmd);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean checkUserFileName(List<String> fileNames, String user) {
        switch (UserEnum.toType(user)) {
            case ADMIN:
                if (!adminFileName.containsAll(fileNames)) {
                    return false;
                }
                break;
            case USER1:
                break;
            case USER2:
                break;
            case USER3:
                break;
            case USER4:
                break;
        }

        return true;
    }

    public static void exportFileAdmin(String ip, String username, String password) {
        List<String> tempList = exportCmd;
        tempList.add("export running-config");

        String[] tempCmd = tempList.toArray(new String[0]);
        String result = commandExecution(ip, username, password, tempCmd);
    }
}
