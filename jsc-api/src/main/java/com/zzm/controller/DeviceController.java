package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.exception.GraceException;
import com.zzm.pojo.bo.DeviceBO;
import com.zzm.pojo.bo.DeviceThresholdConfigBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.DeviceService;
import com.zzm.service.impl.policy.module.upgrade.Upgrade;
import com.zzm.utils.CustomFileUtils;
import com.zzm.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    private static List<Future<String>> upgradeFutureList = new ArrayList<>();
    private static List<Future<String>> importFutureList = new ArrayList<>();

    @Resource
    private DeviceService deviceService;

    @PostMapping("/upgrade")
    @SystemLog(description = "软件升级/安装")
    public JSONResult SystemManagerUpgrade(@RequestParam("files") MultipartFile[] files,
                                           @RequestParam("sort") String sort,
                                           @RequestParam("mode") Integer mode,
                                           @RequestParam("type") Integer type) throws IOException {

        for (int i = 0; i < upgradeFutureList.size(); i++) {
            if (upgradeFutureList.get(i) != null) {
                if (upgradeFutureList.get(i).isDone()) {
                    //判断线程结束，输出回调信息，并将该回调清除
                    upgradeFutureList.remove(i);
                } else {
                    GraceException.display("正在安装/升级，请稍后...");
                }
            }
        }

        if (files.length <= 0) {
            GraceException.display("上传的文件不能为空");
        }

        if ("".equals(sort)) {
            GraceException.display("排序不能为空");
        }

        Upgrade upgrade = deviceService.SystemManagerUpgradeUpload(files, type, mode);

        if (upgrade != null) {
            Future<String> stringFuture = deviceService.SystemManagerUpgradeAsync(upgrade, sort);
            upgradeFutureList.add(stringFuture);
        } else {
            GraceException.display("文件上传失败");
        }

        return JSONResult.ok("升级/安装执行成功，5~10分钟左右升级成功~");
    }

    @PostMapping("/importConfigFile")
    @SystemLog(description = "配置导入")
    public JSONResult importConfigFile(MultipartFile[] files, HttpServletRequest request) {

        for (int i = 0; i < importFutureList.size(); i++) {
            if (importFutureList.get(i) != null) {
                if (importFutureList.get(i).isDone()) {
                    //判断线程结束，输出回调信息，并将该回调清除
                    importFutureList.remove(i);
                } else {
                    GraceException.display("正在配置导入，请稍后...");
                }
            }
        }

        String user = request.getHeader("x-token");
        Future<String> stringFuture = deviceService.importConfigFile(files, user);
        importFutureList.add(stringFuture);

        return JSONResult.ok("配置导入中...");
    }


    @GetMapping("/exportConfigFile")
    @SystemLog(description = "配置导出")
    public JSONResult exportConfigFile(@RequestParam("user") String user,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {

        Map<String, Object> result = deviceService.exportConfigFile(user);
        if (result.get("status").equals("error") ||
                result.get("status").equals("")) {
            return JSONResult.error("导出失败");
        }

        return JSONResult.ok(result.get("fileList"));
    }

    @GetMapping("/download")
    @SystemLog(description = "配置文件下载")
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
     *
     * @throws InterruptedException
     */
    @GetMapping("/threshold")
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
     *
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
