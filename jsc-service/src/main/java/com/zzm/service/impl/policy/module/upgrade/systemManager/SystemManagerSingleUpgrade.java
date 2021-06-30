package com.zzm.service.impl.policy.module.upgrade.systemManager;

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
import java.util.Arrays;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-15
 * @description:
 **/
public class SystemManagerSingleUpgrade extends Upgrade {

    private static final String PYTHON_URL = "D:/Anaconda/envs/python3.5/python";
    private static final String RECOGNITION_MODEL = "D:/IDEA/jsc-parent/jsc-api/src/main/resources/single_board.py";

    public SystemManagerSingleUpgrade(MultipartFile[] files, String upgradePath, String sort) {
        super(files, upgradePath, sort);
    }

    @Override
    protected boolean fileUpload(MultipartFile[] files, String upgradePath) {
        List<String> fileNameList = new ArrayList<>();

        for (MultipartFile file : files) {
            fileNameList.add(file.getOriginalFilename());
        }

        boolean checkFlag = checkUpgradeFileName(fileNameList);
        if (!checkFlag) {
            GraceException.display("文件名称校验不通过");
        }

        try {
            for (MultipartFile file : files) {
                // 获得文件上传的文件名称
                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    try {
                        String filePath = upgradePath + fileName;
                        File newFile = new File(filePath);
                        if (!newFile.exists()) {
                            newFile.mkdirs();
                        }
                        File savedFile = new File(filePath);
                        file.transferTo(savedFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            GraceException.display("配置文件导入失败");
        }
        return true;
    }

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

            WebSocketSendMessage.sendTopicMessage("安装成功");
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

    private boolean checkUpgradeFileName(List<String> fileName) {
        return true;
    }
}
