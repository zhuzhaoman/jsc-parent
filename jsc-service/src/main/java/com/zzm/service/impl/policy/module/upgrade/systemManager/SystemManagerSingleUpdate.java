package com.zzm.service.impl.policy.module.upgrade.systemManager;

import com.alibaba.fastjson.JSONObject;
import com.zzm.exception.GraceException;
import com.zzm.service.impl.policy.module.upgrade.Upgrade;
import com.zzm.utils.WebSocketSendMessage;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-15
 * @description:
 **/
public class SystemManagerSingleUpdate extends Upgrade {

//    private static final String PYTHON_URL = "D:/Anaconda/envs/python3.5/python";
    private static final String PYTHON_URL = "python";
//    private static final String RECOGNITION_MODEL = "D:/IDEA/jsc-parent/jsc-api/src/main/resources/single_board_update.py";
    private static final String RECOGNITION_MODEL = "/home/web/single_board_update.py";

//    public SystemManagerSingleUpdate(MultipartFile[] files, String upgradePath, String sort) {
//        super(files, upgradePath, sort);
//    }


    @Override
    protected void versionUpgrade(String sort) {

        BufferedReader in = null;
        Process process;
        StringBuilder sb = new StringBuilder();

        try {
            String[] python = new String[]{PYTHON_URL, RECOGNITION_MODEL, sort};
            process = Runtime.getRuntime().exec(python);

            String line = "";
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            JSONObject result = new JSONObject();
            result.put("title", "软件升级");
            result.put("msg", "软件升级完成");
            result.put("type", "success");
            WebSocketSendMessage.sendTopicMessage(result.toJSONString());
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
    }

    @Override
    public boolean checkUpgradeFileName(List<String> fileName) {
        return true;
    }
}
