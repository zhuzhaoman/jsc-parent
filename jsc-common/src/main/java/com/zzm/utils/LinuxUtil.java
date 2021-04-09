package com.zzm.utils;

import com.zzm.enums.UserEnum;
import com.zzm.enums.UserFileConfig;
import com.zzm.enums.UserPasswordEnum;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: zhuzhaoman
 * @date: 2021-03-01
 * @description:
 **/
public class LinuxUtil {

    public static List<String> exportParamsTemplate = Arrays.asList("127.0.0.1",
            "admin", "JSC@3pass0k", "enable", "JSC@3pass0k",
            "configure terminal", "device", "export running-config");
    public static List<String> importParamsTemplate = Arrays.asList("127.0.0.1",
            "admin", "JSC@3pass0k", "enable", "JSC@3pass0k",
            "configure terminal");


    public static boolean checkUserFileName(List<String> fileNames, String user) {
        List<String> mainFileNames = UserFileConfig.getFileList(user);

        for (String file : fileNames) {
            if (!mainFileNames.contains(file)) {
                return false;
            }
        }

        return true;
    }


    /**
     * admin用户配置文件导出
     *
     * @param pythonUrl
     * @param exportProcedure
     * @return
     */
    public static String exportFile(String user, String ip, String pythonUrl, String exportProcedure) {
        List<String> tempList = exportParamsTemplate;

        String password = UserPasswordEnum.getUserPassword(user);

        tempList.set(0, ip);
        tempList.set(1, user);
        tempList.set(2, password);
        tempList.set(4, password);
        return executeCommandSimple(pythonUrl, exportProcedure, tempList);
    }

    public static String executeCommandExplicit(String pythonUrl, String exportProcedure, List<String> paramList) {
        String params = String.join(",", paramList);

        BufferedReader in = null;
        Process process;
        StringBuilder sb = new StringBuilder();

        try {
            String[] python = new String[]{pythonUrl, exportProcedure, params};
            process = Runtime.getRuntime().exec(python);

            String line = "";
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = in.readLine()) != null) {
                if (!"".equals(line)) {
                    sb.append(line);
                    sb.append("<br>");
                }
            }

            long startTime = System.currentTimeMillis();
            boolean processFinished = false;
            while(System.currentTimeMillis() - startTime < 10*1000
                    && !processFinished){
                try {
                    process.exitValue();
                } catch (IllegalThreadStateException e) {
                    continue;
                }
                processFinished = true;
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


    public static String executeCommandSimple(String pythonUrl, String exportProcedure, List<String> paramList) {
        String params = String.join(",", paramList);

        BufferedReader in = null;
        Process process;
        String result = "error";

        try {
            String[] python = new String[]{pythonUrl, exportProcedure, params};
            process = Runtime.getRuntime().exec(python);

            String line = "";
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = in.readLine()) != null) {
                result = line;
                break;
            }

            long startTime = System.currentTimeMillis();
            boolean processFinished = false;
            int i = 1;
            while(System.currentTimeMillis() - startTime < 10*1000
                    && !processFinished){
                try {
                    process.exitValue();
                } catch (IllegalThreadStateException e) {
                    continue;
                }
                processFinished = true;
            }

            process.waitFor();
            processFinished = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
