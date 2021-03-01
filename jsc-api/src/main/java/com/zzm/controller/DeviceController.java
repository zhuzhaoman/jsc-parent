package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.exception.GraceException;
import com.zzm.pojo.bo.DeviceBO;
import com.zzm.pojo.bo.DeviceThresholdConfigBO;
import com.zzm.pojo.bo.PortBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.DeviceService;
import com.zzm.utils.JSONResult;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 设备信息查询
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/device")
public class DeviceController {

    public static final Logger log = LoggerFactory.getLogger(DeviceController.class);

    @Resource
    private DeviceService deviceService;

    @PostMapping("/importConfigFile")
    public JSONResult importConfigFile(@RequestParam("files") MultipartFile[] files,
                                       @RequestParam("user") String user) {

        boolean flag = deviceService.importConfigFile(files, user);
        if (!flag) {
            return JSONResult.error("导入文件失败");
        }

        return JSONResult.ok();
    }


    @GetMapping("/exportConfigFile")
    public void exportConfigFile(@RequestParam("user") String user,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        deviceService.exportConfigFile(user);
    }

    @PostMapping("/download")
    public void download(@RequestParam("fileName") String fileName,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        deviceService.downloadConfigFile(fileName, response);
    }


    /**
     * 查询设备信息
     *
     * @param username 当前用户
     * @throws InterruptedException
     */
    @GetMapping("/info")
    @SystemLog(description = "查询设备信息")
    public ReceiveSystemManagerDTO info(String username) throws InterruptedException {
        try {
            return deviceService.info(username);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("查询设备信息失败");
        }
        return null;
    }

    /**
     * 获取设备阈值
     * @throws InterruptedException
     */
    @GetMapping("/threshold")
    @SystemLog(description = "获取设备阈值")
    public ReceiveSystemManagerDTO getThreshold(String username) {
        try {
            return deviceService.getThreshold(username);
        } catch (Exception e) {
            e.printStackTrace();
           GraceException.display("获取阈值信息失败");
        }
        return null;
    }

    /**
     * 配置设备的阈值
     * @param deviceThresholdConfigBO 传递的参数
     */
    @PostMapping("/thresholdConfig")
    @SystemLog(description = "设置设备阈值")
    public ReceiveSystemManagerDTO configThreshold(@RequestBody DeviceThresholdConfigBO deviceThresholdConfigBO) throws Exception {
        try {
            return deviceService.configThreshold(deviceThresholdConfigBO);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("设置阈值失败");
        }
        return null;
    }

    @PostMapping("/operation")
    public ReceiveSystemManagerDTO operation(@RequestBody DeviceBO deviceBO) throws Exception {
        System.out.println(deviceBO.toString());
        ReceiveSystemManagerDTO operation = deviceService.operation(deviceBO);
        return operation;
    }
}
