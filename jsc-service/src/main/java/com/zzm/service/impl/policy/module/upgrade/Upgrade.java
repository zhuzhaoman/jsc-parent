package com.zzm.service.impl.policy.module.upgrade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-15
 * @description:
 **/
public abstract class Upgrade {
    private final Logger log = LoggerFactory.getLogger(Upgrade.class);

    private MultipartFile[] files;
    private String upgradePath;


    public Upgrade(MultipartFile[] files, String upgradePath) {
        this.files = files;
        this.upgradePath = upgradePath;
    }

    public boolean exchange() {
        if (!fileUpload(files, upgradePath)) {
            return false;
        }

        versionUpgrade();
        return false;
    }

    protected abstract boolean fileUpload(MultipartFile[] files, String upgradePath);

    protected abstract void versionUpgrade();

}
