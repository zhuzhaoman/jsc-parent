package com.zzm.service;


import com.zzm.pojo.bo.DeviceThresholdConfigBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import java.util.concurrent.ExecutionException;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 15:14
 * @description 设备信息接口
 */
public interface DeviceService {

    /**
     * 查询设备信息
     *
     * @param username 当前操作用户
     * @throws InterruptedException
     */
    ReceiveSystemManagerDTO info(String username) throws Exception;


    /**
     * 获取设备的槽位数
     *
     * @param username
     * @return
     */
    int getDeviceSlotTotal(String username) throws Exception;

    /**
     * 根据不同的槽位获取端口数
     *
     * @param username
     * @param slotNumber
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    int getSlotPortTotal(String username, int slotNumber) throws Exception;

    /**
     * 根据不同的槽位获取当前槽位类型
     *
     * @param username
     * @param slotNumber
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    int getSlotType(String username, int slotNumber) throws Exception;

    /**
     * 获取设备阈值信息
     */
    ReceiveSystemManagerDTO getThreshold(String username) throws Exception;

    /**
     * 配置设备的阈值
     *
     * @param deviceThresholdConfigBO
     */
    ReceiveSystemManagerDTO configThreshold(DeviceThresholdConfigBO deviceThresholdConfigBO) throws Exception;
}
