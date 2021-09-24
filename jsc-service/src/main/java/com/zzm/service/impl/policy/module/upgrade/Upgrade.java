package com.zzm.service.impl.policy.module.upgrade;

import com.zzm.exception.GraceException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-15
 * @description:
 **/
public abstract class Upgrade {
    private final Logger log = LoggerFactory.getLogger(Upgrade.class);

    public void exchange(String sort) {
        versionUpgrade(sort);
    }

    public boolean fileUpload(MultipartFile[] files, String upgradePath) {
        List<String> fileNameList = new ArrayList<>();

        for (MultipartFile file : files) {
            fileNameList.add(file.getOriginalFilename());
        }

        boolean checkFlag = checkUpgradeFileName(fileNameList);
        if (!checkFlag) {
            GraceException.display("文件名称校验不通过");
            return false;
        }

        try {
            for (MultipartFile file : files) {
                // 获得文件上传的文件名称
                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    try {
                        String filePath = upgradePath + fileName;
                        File newFile = new File(filePath);

                        file.transferTo(newFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            GraceException.display("配置文件导入失败");
            return false;
        }

        return true;
    }

    protected abstract void versionUpgrade(String sort);

    protected abstract boolean checkUpgradeFileName(List<String> fileNameList);

}
