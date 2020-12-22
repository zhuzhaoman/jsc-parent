package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.exception.GraceException;
import com.zzm.pojo.bo.DeviceThresholdConfigBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
