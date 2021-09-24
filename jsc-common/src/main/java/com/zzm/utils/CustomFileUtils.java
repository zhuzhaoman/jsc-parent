package com.zzm.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: zhuzhaoman
 * @date: 2021-07-02
 * @description:
 **/
public class CustomFileUtils {
    /**
     * @return 获取当前项目所在服务器位置
     */
    public static String getLocalTemPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String savePath = request.getSession().getServletContext().getRealPath("");
        int indexOf = savePath.lastIndexOf("\\");
        if (indexOf == -1) { // Linux下临时路径获取， 即获取当前项目所在目录--相对路径
            savePath = System.getProperty("user.dir");
        } else { // 获取绝对路径
            savePath = savePath.substring(0, savePath.lastIndexOf("\\"));
        }
        return savePath + "/";
    }

    /**
     * @param file   文件信息
     * @param dir    临时文件夹路径
     * @return 本地文件生成
     * @throws IOException
     */
    public static File createLocalFile(MultipartFile file, String dir) throws IOException {
        String url = getLocalTemPath() + (ObjectUtils.isEmpty(dir) ? "" : (dir + File.separator)) + file.getOriginalFilename();
        File toFile = new File(url);
        file.getBytes();
        InputStream ins = file.getInputStream();
        FileUtils.copyInputStreamToFile(ins, toFile);
        if (ins != null) {
            ins.close();
        }
        return toFile;
    }
}
