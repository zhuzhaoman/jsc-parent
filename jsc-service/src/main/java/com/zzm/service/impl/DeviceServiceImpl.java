package com.zzm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.*;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.DeviceThresholdConfigBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
}
