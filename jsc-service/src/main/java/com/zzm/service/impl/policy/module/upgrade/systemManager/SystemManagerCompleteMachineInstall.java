package com.zzm.service.impl.policy.module.upgrade.systemManager;

import com.alibaba.fastjson.JSONObject;
import com.zzm.service.impl.policy.module.upgrade.Upgrade;
import com.zzm.utils.WebSocketSendMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-15
 * @description:
 **/
public class SystemManagerCompleteMachineInstall extends Upgrade {

//    private static final String PYTHON_URL = "D:/Anaconda/envs/python3.5/python";
    private static final String PYTHON_URL = "python";
//    private static final String RECOGNITION_MODEL = "D:/IDEA/jsc-parent/jsc-api/src/main/resources/complete_machine.py";
    private static final String RECOGNITION_MODEL = "/home/web/complete_machine_install.py";

//    public SystemManagerCompleteMachineInstall(MultipartFile[] files, String upgradePath, String sort) {
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
            result.put("title", "整机软件安装");
            result.put("msg", "整机软件安装成功");
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
