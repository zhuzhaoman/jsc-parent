package com.zzm.snmp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.ChassisTypeEnum;
import com.zzm.enums.PortOIDEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.vo.PortMessageVO;
import com.zzm.pojo.vo.SlotPortStatusVO;
import com.zzm.policy.snmp.port_status.SnmpPortStatusCardTypeComponent;
import com.zzm.policy.snmp.port_status.SnmpPortStatusCardTypePolicyService;
import com.zzm.resource.SnmpResource;
import com.zzm.service.DeviceService;
import com.zzm.utils.BaseConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

@SuppressWarnings("all")
@Component
public class SnmpService {

    public static final Logger log = LoggerFactory.getLogger(SnmpService.class);

    private Object deviceInfo = null;

    @Resource
    private SnmpDao snmpDao;

    @Resource
    private SnmpResource snmpResource;

    @Resource
    private DeviceService deviceService;

    /**
     * 测试网络是否连通
     *
     * @return
     * @throws IOException
     */
    public boolean isEthernetConnection() {
        try {
            InetAddress inetAddress = InetAddress.getByName(snmpResource.getHostIp());
            return inetAddress.isReachable(1000);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 获取全部端口状态
     */
    public List<Map<String, Object>> allSlotPortStatus() throws Exception {

        // 判断snmp是否在线
        boolean status = isEthernetConnection();
        System.out.println(status);


        if (!status) {
            return null;
        }

        // 获取所有端口状态信息
        List<String> portStatusList = snmpDao.walkByTableToList(PortOIDEnum.PORT_LINK_STATUS.getCode());
        // 获取所有端口丝印号
        List<String> portIndexList = snmpDao.walkByTableToList(PortOIDEnum.PORT_INDEX.getCode());

        if (portStatusList.size() != portIndexList.size()) {
            log.error("【异常：端口不匹配】");
        }

        ReceiveSystemManagerDTO receiveSystemManagerDTO = deviceService.info("admin");
        Object deviceInfo = receiveSystemManagerDTO.getData();
        this.deviceInfo = deviceInfo;

        int slotTotal = getDeviceSlotTotal();

        /**
         * 初始化数据
         */
        List<Map<String, Object>> result = Arrays.asList(new HashMap<>());
        for (int i = 0; i < slotTotal; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("list", new ArrayList<SlotPortStatusVO>());
            map.put("cardType", null);
            result.set(i, map);
        }

        for (int index = 0; index < portIndexList.size(); index++) {

            /**
             * [24-31]：8位 -> 端口号
             * [23-19]：5位 -> 扩展端口号
             *    [18]：1位 -> 端口是否甩纤：甩纤1 、未甩纤0
             * [17-16]：2位 -> 端口类型，前面板端口（00），后面板端口（1）、100G端口（2）
             * [15-11]：5位 -> 端口所属前面板槽位
             *  [10-8]：3位 -> 端口所在后面板槽位号（rtm板卡）
             *   [7-3]：5位 -> 端口所在机箱号
             *   [2-0]：3位 -> 端口速率类型，正交1.0(0)、10G端口(1)、100G端口(2)
             */

            /**
             * 获取丝印号,并将其转换为16进制：5221321
             */
            int indexNumber = Integer.parseInt(portIndexList.get(index));
            String indexStr = BaseConversionUtils.decimal2binary(indexNumber, 32);

            /**
             * 所属槽位：1，2，....
             */
            int slotStart = 11;
            int slotEnd = slotStart + 5;
            int slotNumber = Integer.parseInt(indexStr.substring(slotStart, slotEnd), 2);

            // 获取端口号，并将其转换为：1、2、3
            int portNumber = Integer.parseInt(indexStr.substring(indexStr.length() - 8), 2);

            /**
             * 端口速率
             */
            int portRateStart = indexStr.length() - 32;
            int portRateEnd = portRateStart + 3;
            int portRate = Integer.parseInt(indexStr.substring(portRateStart, portRateEnd), 2);


            /**
             * 是否甩纤,：0、1
             */
            int start = indexStr.length() - 14;
            int end = start + 1;
            int isEXTEND = Integer.parseInt(indexStr.substring(start, end), 2);

            /**
             * 获取扩展端口号
             */
            int extendStart = indexStr.length() - 13;
            int extendEnd = extendStart + 5;
            String extendStr = indexStr.substring(extendStart, extendEnd);
            int extendPortNumber = Integer.parseInt(extendStr, 2);

            if (slotNumber <= slotTotal) {
                int cardType = getCardType(slotNumber);

                SnmpPortStatusCardTypePolicyService snmpPortStatusCardTypePolicyService =
                        SnmpPortStatusCardTypeComponent.snmpCardTypePolicyServiceMap.get(cardType);

                snmpPortStatusCardTypePolicyService.dataEncapsulation(indexNumber, slotNumber, portNumber, portRate, isEXTEND, extendPortNumber, result, index, portStatusList);
            }

        }

        return result;
    }

    /**
     * 获取某个端口状态，包括甩纤
     *
     * @param portIndexList：端口丝印号
     * @return
     */
    public List<PortMessageVO> appointStatus(String portIndexList) {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();

        if (!status) {
            return null;
        }

        String[] portIndex = portIndexList.split(",");
        List<PortMessageVO> portMessageVOList = new ArrayList<>();

        for (int i = 0; i < portIndex.length; i++) {

            int indexNumber = Integer.parseInt(portIndex[i]);
            String indexStr = Integer.toBinaryString(indexNumber);

            // 获取端口号
            int portNumber = Integer.parseInt(indexStr.substring(indexStr.length() - 8), 2);

            String portInBytesRate = PortOIDEnum.PORT_IN_BYTES_RATE.getCode() + "." + portIndex[i];
            String portOutBytesRate = PortOIDEnum.PORT_OUT_BYTES_RATE.getCode() + "." + portIndex[i];
            String portInOpticalPower = PortOIDEnum.PORT_IN_OPTICAL_POWER.getCode() + "." + portIndex[i];
            String portOutOpticalPower = PortOIDEnum.PORT_OUT_OPTICAL_POWER.getCode() + "." + portIndex[i];
            String portSpeedOid = PortOIDEnum.PORT_SPEED.getCode() + "." + portIndex[i];
            String portStatusOid = PortOIDEnum.PORT_LINK_STATUS.getCode() + "." + portIndex[i];

            List<String> IBytesList = snmpDao.walkByTableToOne(portInBytesRate); // 输入流量
            List<String> OBytesList = snmpDao.walkByTableToOne(portOutBytesRate); // 输出流量
            List<String> IOpticalPowerList = snmpDao.walkByTableToOne(portInOpticalPower); // 输入流量
            List<String> OOpticalPowerList = snmpDao.walkByTableToOne(portOutOpticalPower); // 输出流量
            List<String> portSpeedList = snmpDao.walkByTableToOne(portSpeedOid); // 端口速率
            List<String> portStatusList = snmpDao.walkByTableToOne(portStatusOid); // 端口状态

            // 判断端口是否甩纤
            String portNumberStr = portIndex.length > 1 ? (portNumber + "_" + (i + 1)) : String.valueOf(portNumber);

            PortMessageVO portMessageVO = new PortMessageVO();
            portMessageVO.setPortNumber(portNumberStr); // 1_1、1_2或者1、2
            portMessageVO.setInputBytes(IBytesList.get(0));
            portMessageVO.setOutputBytes(OBytesList.get(0));
            portMessageVO.setInputOpticalPower(IOpticalPowerList.get(0));
            portMessageVO.setOutputOpticalPower(OOpticalPowerList.get(0));
            portMessageVO.setPortSpeed(Integer.parseInt(portSpeedList.get(0)) / 1000 + "G"); // 10G
            portMessageVO.setPortStatus(portStatusList.get(0));

            portMessageVOList.add(portMessageVO);
        }

        return portMessageVOList;
    }

    /**
     * 端口输入字节数
     *
     * @return
     */
    public List<String> getInBytes() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();

        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_IN_BYTES.getCode());
    }

    /**
     * 端口输出字节数
     *
     * @return
     */
    public List<String> getOutBytes() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();


        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_OUT_BYTES.getCode());
    }


    /**
     * 端口输入光功率
     *
     * @return
     */
    public List<String> getInOpticalPower() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();


        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_IN_OPTICAL_POWER.getCode());
    }

    /**
     * 端口输出光功率
     *
     * @return
     */
    public List<String> getOutOpticalPower() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();


        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_OUT_OPTICAL_POWER.getCode());
    }

    /**
     * 输入字节速率
     *
     * @return
     */
    public List<String> getInputRate() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();


        if (!status) {
            return null;
        }

        return snmpDao.walkByTableToList(PortOIDEnum.PORT_IN_BYTES_RATE.getCode());
    }

    /**
     * 输出字节速率
     *
     * @return
     */
    public List<String> getOutputRate() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();

        if (!status) {
            return null;
        }
        return snmpDao.walkByTableToList(PortOIDEnum.PORT_OUT_BYTES_RATE.getCode());
    }

    /**
     * 获取全部端口号
     *
     * @return
     */
    public List<String> getPortIndexAll() {
        // 判断snmp是否在线
        boolean status = isEthernetConnection();

        if (!status) {
            return null;
        }
       return snmpDao.walkByTableToList(PortOIDEnum.PORT_INDEX.getCode());
    }

    /**
     * 获取设备槽位数
     *
     * @return
     */
    private int getDeviceSlotTotal() {
        JSONObject jsonObject = JSON.parseObject(deviceInfo.toString());
        int chassisType = (int) jsonObject.get("m_u32EquType");

        ChassisTypeEnum chassisTypeEnum = ChassisTypeEnum.fromValue(chassisType);

        return chassisTypeEnum.getSlotNumber();
    }

    /**
     * 根据槽位获取当前板卡的类型
     *
     * @param slotNumber
     * @return
     */
    private int getCardType(int slotNumber) {
        JSONObject jsonObject = JSON.parseObject(deviceInfo.toString());
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

}
