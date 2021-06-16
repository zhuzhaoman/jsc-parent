package com.zzm.service.impl.policy.module.upgrade.systemManager;

import com.zzm.exception.GraceException;
import com.zzm.service.impl.policy.module.upgrade.Upgrade;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-15
 * @description:
 **/
public class SystemManagerUpgrade extends Upgrade {

    public SystemManagerUpgrade(MultipartFile[] files, String upgradePath) {
        super(files, upgradePath);
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
    protected void versionUpgrade() {

    }

    private boolean checkUpgradeFileName(List<String> fileName) {
        return false;
    }
}
