package com.zzm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.*;
import com.zzm.exception.GraceException;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.DeviceBO;
import com.zzm.pojo.bo.DeviceThresholdConfigBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.policy.system_manager.sending.device.SystemManagerSendingDeviceComponent;
import com.zzm.policy.system_manager.sending.device.SystemManagerSendingDevicePolicyService;
import com.zzm.service.DeviceService;
import com.zzm.utils.JSONResult;
import com.zzm.utils.LinuxUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 15:14
 * @description 设备信息实现
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    public static final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Resource
    private ClientServerSync clientServerSync;
    @Value("${jsc.config.save-path}")
    private String savePath;
    @Value("${jsc.config.linux-ip}")
    private String linuxIp;
    @Value("${jsc.config.linux-username}")
    private String linuxUsername;
    @Value("${jsc.config.linux-password}")
    private String linuxPassword;

    @Override
    public boolean importConfigFile(MultipartFile[] files, String user) {

        if (files == null || files.length <= 0) {
            GraceException.display("上传的配置文件不能为空");
        }

        boolean checkFlag = checkImportFileName(files, user);
        if (!checkFlag) {
            GraceException.display("文件名称校验不通过");
        }

        try {
            for (MultipartFile file : files) {
                // 获得文件上传的文件名称
                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {

                    String[] fileNameArr = fileName.split("\\.");
                    String suffix = fileNameArr[fileNameArr.length - 1];

                    if (!"txt".equalsIgnoreCase(suffix)) {
                        GraceException.display("配置文件格式有误");
                    }

                    try {
                        String filePath = savePath + fileName;
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

        return LinuxUtil.loadFileAdmin(linuxIp, linuxUsername, linuxPassword);
    }


    private boolean checkImportFileName(MultipartFile[] files, String user) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            fileNames.add(file.getOriginalFilename());
        }

        return LinuxUtil.checkUserFileName(fileNames, user);
    }

    @Override
    public void exportConfigFile(String user) {
        LinuxUtil.exportFileAdmin(linuxIp, linuxUsername, linuxPassword);
    }

    @Override
    @SneakyThrows
    public void downloadConfigFile(String fileName, HttpServletResponse response) {

        File scFile = new File("D:/" + fileName);
        String srcFileName = scFile.getName();

        if (scFile.exists()) {

            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(srcFileName, "UTF-8"));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(scFile);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                response.setStatus(HttpStatus.OK.value());
                os.flush();
            } catch (Exception e) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 查询设备信息
     *
     * @param username 当前操作用户
     * @throws InterruptedException
     */
    @Override
    public ReceiveSystemManagerDTO info(String username) throws Exception {

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DEVICE_INFO.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DEVICE_INFO.getCode(),
                MessageCodeEnum.DEVICE_INFO.getReqCode(),
                username, 0, 0, null);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) clientServerSync.sendMessage(content);

        return receiveSystemManagerDTO;
    }

    /**
     * 获取设备槽位数
     *
     * @param username
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public int getDeviceSlotTotal(String username) throws Exception {

        ReceiveSystemManagerDTO info = info(username);
        Object data = JSONObject.toJSON(info.getData());
        JSONObject jsonObject = JSON.parseObject(data.toString());
        int chassisType = (int) jsonObject.get("m_u32EquType");

        ChassisTypeEnum chassisTypeEnum = ChassisTypeEnum.fromValue(chassisType);

        return chassisTypeEnum.getSlotNumber();
    }

    /**
     * 根据槽位号获取槽位板卡端口数
     *
     * @param slotNumber
     * @return
     */
    @Override
    public int getSlotPortTotal(String username, int slotNumber) throws Exception {

        ReceiveSystemManagerDTO info = info(username);
        Object data = JSONObject.toJSON(info.getData());
        JSONObject jsonObject = JSON.parseObject(data.toString());
        JSONArray cardSlotList = jsonObject.getJSONArray("m_tCardStatusMsg");


        for (int i = 0; i < cardSlotList.size(); i++) {
            JSONObject slot = cardSlotList.getJSONObject(i);
            if ((int) slot.get("m_u32SlotId") == slotNumber) {
                int slotType = (int) slot.get("m_u32CardType");
                CardTypeEnum cardTypeEnum = CardTypeEnum.fromValue(slotType);
                return cardTypeEnum.getPortNumber();
            }
        }

        return 0;
    }


    /**
     * 根据槽位获取当前板卡的类型
     * @param username
     * @param slotNumber
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public int getSlotType(String username, int slotNumber) throws Exception {

        ReceiveSystemManagerDTO info = info(username);
        Object data = JSONObject.toJSON(info.getData());
        JSONObject jsonObject = JSON.parseObject(data.toString());
        JSONArray cardSlotList = jsonObject.getJSONArray("m_tCardStatusMsg");


        for (int i = 0; i < cardSlotList.size(); i++) {
            JSONObject slot = cardSlotList.getJSONObject(i);
            if ((int) slot.get("m_u32SlotId") == slotNumber) {
                int slotType = (int) slot.get("m_u32CardType");
                return slotType;
            }
        }

        return 0;
    }

    /**
     * 获取设备阈值信息
     */
    @Override
    public ReceiveSystemManagerDTO getThreshold(String username) throws Exception {
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DEVICE_THRESHOLD_CONFIG_OR_GET.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DEVICE_THRESHOLD_GET.getCode(),
                MessageCodeEnum.DEVICE_THRESHOLD_GET.getReqCode(),
                username, 0, 0, null);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) clientServerSync.sendMessage(content);

        return receiveSystemManagerDTO;
    }

    /**
     * 配置设备的阈值
     *
     * @param deviceThresholdConfigBO 传递的参数
     */
    @Override
    public ReceiveSystemManagerDTO configThreshold(DeviceThresholdConfigBO deviceThresholdConfigBO) throws Exception {

        Map<String, Object> data = new HashMap<>();
        data.put("thresholdName", deviceThresholdConfigBO.getThresholdName());
        data.put("thresholdValue", deviceThresholdConfigBO.getThresholdValue());
        data.put("thresholdPower", deviceThresholdConfigBO.getThresholdPower());

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.DEVICE_THRESHOLD_CONFIG_OR_GET.getCode(),
                MessageIdentifyEnum.Z.getCode(),
                MessageTypeEnum.DEVICE_THRESHOLD_CONFIG.getCode(),
                MessageCodeEnum.DEVICE_THRESHOLD_CONFIG.getReqCode(),
                deviceThresholdConfigBO.getUsername(), null, null, data);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) clientServerSync.sendMessage(content);
        return receiveSystemManagerDTO;
    }

    @SneakyThrows
    @Override
    public ReceiveSystemManagerDTO operation(DeviceBO deviceBO) {
        SystemManagerSendingDevicePolicyService systemManagerSendingDevicePolicyService
                = SystemManagerSendingDeviceComponent.systemManagerSendingPolicyServiceMap.get(deviceBO.getDeviceType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingDevicePolicyService.dataEncapsulation(deviceBO);

        return receiveSystemManagerDTO;
    }

}
