package com.zzm.job;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.dao.FlowCycleRepository;
import com.zzm.dao.TotalHistoryFlowRepository;
import com.zzm.enums.ChassisTypeEnum;
import com.zzm.enums.DeviceDomainEnum;
import com.zzm.pojo.FlowCycle;
import com.zzm.pojo.TotalHistoryFlow;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.vo.DomainInfoVO;
import com.zzm.pojo.vo.SlotInfoVO;
import com.zzm.policy.snmp.port_flow.SnmpPortFlowCardTypeComponent;
import com.zzm.policy.snmp.port_flow.SnmpPortFlowCardTypePolicyService;
import com.zzm.policy.snmp.port_optical_power.SnmpPortOpticalPowerCardTypeComponent;
import com.zzm.policy.snmp.port_optical_power.SnmpPortOpticalPowerCardTypePolicyService;
import com.zzm.service.DeviceService;
import com.zzm.service.DomainService;
import com.zzm.snmp.SnmpService;
import com.zzm.utils.BaseConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 历史流量定时任务
 */
//@Component
@SuppressWarnings("all")
public class HistoryFlowJob {

    public static final Logger log = LoggerFactory.getLogger(HistoryFlowJob.class);

    private Object deviceInfo = null;

    @Resource
    private FlowCycleRepository flowCycleRepository;

    @Resource
    private TotalHistoryFlowRepository totalHistoryFlowRepository;

    @Resource
    private DomainService domainService;

    @Resource
    private DeviceService deviceService;

    @Resource
    private SnmpService snmpService;

    ExecutorService executorService1 = Executors.newFixedThreadPool(3);
    ExecutorService executorService2 = Executors.newFixedThreadPool(3);

    @Scheduled(cron = "0 */1 * * * ?")
    @Transactional
    public void producePortHistoryFlow() throws Exception {
        executorService1.execute(() -> {
            // 当前时间
            Date currentTime = new Date();
            System.out.println("当前时间：" + currentTime);
            System.out.println("保存历史光功率");

            /**
             * [31~24]：8位 -> 端口号
             * [23~19]：5位 -> 扩展端口号
             *    [18]：1位 -> 端口是否甩纤：甩纤1 、未甩纤0
             * [17~16]：2位 -> 端口类型，前面板端口（00），后面板端口（1）、100G端口（2）
             * [15~11]：5位 -> 端口所属前面板槽位
             *  [10~8]：3位 -> 端口所在后面板槽位号（rtm板卡）
             *   [7~3]：5位 -> 端口所在机箱号
             *   [2~0]：3位 -> 端口速率类型，正交1.0(0)、10G端口(1)、100G端口(2)
             */
            // 1、获取当前域下有板卡的域信息
            List<DomainInfoVO> domainInfoVOList = null;
            try {
                domainInfoVOList = domainService.getDomainAllBySlot("admin");
            } catch (Exception e) {
                e.printStackTrace();
            }

            ReceiveSystemManagerDTO receiveSystemManagerDTO = null;
            try {
                receiveSystemManagerDTO = deviceService.info("admin");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object deviceInfo = receiveSystemManagerDTO.getData();
            this.deviceInfo = deviceInfo;

            // 2、设备槽位数
            int slotTotal = getDeviceSlotTotal();

            // 3、获取当前板卡所有端口、输入字节、输出字节速率、输入光功率、输出光功率
            List<String> portAll = snmpService.getPortIndexAll();
            List<String> portInRate = snmpService.getInputRate();
            List<String> portOutRate = snmpService.getOutputRate();
            List<String> portInOpticalPower = snmpService.getInOpticalPower();
            List<String> portOutOpticalPower = snmpService.getOutOpticalPower();
            System.out.println("portAll:" + portAll.size());
            System.out.println("portInRate:" + portInRate.size());
            System.out.println("portOutRate:" + portOutRate.size());
            System.out.println("portInOpticalPower:" + portInOpticalPower.size());
            System.out.println("portOutOpticalPower:" + portOutOpticalPower.size());


            if ((portAll.size() == portInRate.size()) && (portAll.size() == portOutRate.size()) &&
                    (portAll.size() == portInOpticalPower.size()) && (portAll.size() == portOutOpticalPower.size())) {

                for (int i = 0; i < portAll.size(); i++) {

                    int indexNumber = Integer.parseInt(portAll.get(i));
                    String indexStr = BaseConversionUtils.decimal2binary(indexNumber, 32);

                    /**
                     * 所属槽位
                     */
                    int slotStart = 11;
                    int slotEnd = slotStart + 5;
                    int slotNumber = Integer.parseInt(indexStr.substring(slotStart, slotEnd), 2);

                    /**
                     * 防止将交换口也算在内
                     */
                    if (slotNumber > slotTotal) {
                        continue;
                    }

                    /**
                     * 机箱号
                     */
                    int chassisStart = 3;
                    int chassisEnd = chassisStart + 5;
                    int chassisNumber = Integer.parseInt(indexStr.substring(chassisStart, chassisEnd));


                    /**
                     * 端口号
                     */
                    int portStart = 24;
                    int portEnd = portStart + 8;
                    int portNumber = Integer.parseInt(indexStr.substring(portStart, portEnd), 2);

                    // 根据槽位获取槽位所属的域信息
                    DomainInfoVO domainInfo = getDomainInfoBySlotId(domainInfoVOList, slotNumber);

                    // 输入字节速率
                    float inputRate = BaseConversionUtils.bit2Gbps(portInRate.get(i));
                    // 输出字节速率
                    float outputRate = BaseConversionUtils.bit2Gbps(portOutRate.get(i));

                    // 输入光功率
                    float inputOpticalPower = Float.parseFloat(portInOpticalPower.get(i));
                    // 输出光功率
                    float outputOpticalPower = Float.parseFloat(portOutOpticalPower.get(i));


                    // 域名称
                    String domainName = DeviceDomainEnum.fromCode(domainInfo.getM_u32Property()).getName();

                    /**
                     * 端口速率
                     */
                    int portRateStart = 0;
                    int portRateEnd = portRateStart + 3;
                    int portRate = Integer.parseInt(indexStr.substring(portRateStart, portRateEnd), 2);

                    /**
                     * 扩展端口号
                     */
                    int extendStart = 19;
                    int extendEnd = extendStart + 5;
                    int extendNumber = Integer.parseInt(indexStr.substring(extendStart, extendEnd), 2);

                    /**
                     * 是否甩纤
                     */
                    int isEXTENDStart = 18;
                    int isEXTENDEnd = isEXTENDStart + 1;
                    int isEXTEND = Integer.parseInt(indexStr.substring(isEXTENDStart, isEXTENDEnd), 2);

                    int cardType = getCardType(slotNumber);

                    /**
                     * 保存端口历史流量
                     */
                    SnmpPortFlowCardTypePolicyService snmpPortFlowCardTypePolicyService =
                            SnmpPortFlowCardTypeComponent.snmpCardTypePolicyServiceMap.get(cardType);
                    try {
                        snmpPortFlowCardTypePolicyService.dataEncapsulation(chassisNumber, slotNumber, portNumber,
                                extendNumber, isEXTEND, portRate, inputRate, outputRate, domainName, domainInfo, currentTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    /**
                     * 保存光功率
                     */
                    SnmpPortOpticalPowerCardTypePolicyService snmpPortOpticalPowerCardTypePolicyService =
                            SnmpPortOpticalPowerCardTypeComponent.snmpCardTypePolicyServiceMap.get(cardType);
                    try {
                        snmpPortOpticalPowerCardTypePolicyService.dataEncapsulation(chassisNumber, slotNumber, portNumber,
                                extendNumber, isEXTEND, portRate, inputOpticalPower, outputOpticalPower, domainName, domainInfo, currentTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Scheduled(cron = "0 */1 * * * ?")
    @Transactional
    public void produceHistoryFlow() throws Exception {
        executorService2.execute(() -> {
            Date currentTime = new Date();
            System.out.println("当前时间：" + currentTime);
            System.out.println("保存历史流量");

            /**
             * [31~24]：8位 -> 端口号
             * [23~19]：5位 -> 扩展端口号
             *    [18]：1位 -> 端口是否甩纤：甩纤1 、未甩纤0
             * [17~16]：2位 -> 端口类型，前面板端口（00），后面板端口（1）、100G端口（2）
             * [15~11]：5位 -> 端口所属前面板槽位
             *  [10~8]：3位 -> 端口所在后面板槽位号（rtm板卡）
             *   [7~3]：5位 -> 端口所在机箱号
             *   [2~0]：3位 -> 端口速率类型，正交1.0(0)、10G端口(1)、100G端口(2)
             */
            // 1、获取当前域信息
            List<DomainInfoVO> domainInfoVOList = null;
            try {
                domainInfoVOList = domainService.getDomainAllBySlot("admin");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map<String, Map<String, Float>> existDomain = new HashMap<>();

            ReceiveSystemManagerDTO receiveSystemManagerDTO = null;
            try {
                receiveSystemManagerDTO = deviceService.info("admin");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object deviceInfo = receiveSystemManagerDTO.getData();
            this.deviceInfo = deviceInfo;

            // 2、获取当前设备类型判断是几槽机箱
            int slotTotal = getDeviceSlotTotal();

            // 3、获取当前板卡所有端口、输入字节、输出字节速率
            List<String> portAll = snmpService.getPortIndexAll();
            List<String> portInRate = snmpService.getInputRate();
            List<String> portOutRate = snmpService.getOutputRate();
            System.out.println("portAll:" + portAll.size());
            System.out.println("portInRate:" + portInRate.size());
            System.out.println("portOutRate:" + portOutRate.size());

            if ((portAll.size() == portInRate.size()) && (portAll.size() == portOutRate.size())) {
                for (int i = 0; i < portAll.size(); i++) {

                    int indexNumber = Integer.parseInt(portAll.get(i));
                    String indexStr = BaseConversionUtils.decimal2binary(indexNumber, 32);

                    /**
                     * 所属槽位
                     */
                    int slotStart = 11;
                    int slotEnd = slotStart + 5;
                    int slotNumber = Integer.parseInt(indexStr.substring(slotStart, slotEnd), 2);

                    /**
                     * 防止将交换口也算在内
                     */
                    if (slotNumber > slotTotal) {
                        continue;
                    }

                    /**
                     * 机箱号
                     */
                    int chassisStart = 3;
                    int chassisEnd = chassisStart + 5;
                    int chassisNumber = Integer.parseInt(indexStr.substring(chassisStart, chassisEnd), 2);
                    /**
                     * 端口号
                     */
                    int portStart = 24;
                    int portEnd = portStart + 8;
                    int portNumber = Integer.parseInt(indexStr.substring(portStart, portEnd), 2);

                    // 当前端口所属的域信息
                    DomainInfoVO domainInfo = getDomainInfoBySlotId(domainInfoVOList, slotNumber);

                    // 输入字节速率
                    float inputRate = BaseConversionUtils.bit2Gbps(portInRate.get(i));
                    // 输出字节速率
                    float outputRate = BaseConversionUtils.bit2Gbps(portOutRate.get(i));

                    String domainSlot = domainInfo.getM_u32Property() + "/" + domainInfo.getM_u32DomainId();
                    if (existDomain.containsKey(domainSlot)) {
                        Float inSum = existDomain.get(domainSlot).get("in");
                        Float outSum = existDomain.get(domainSlot).get("out");

                        inSum += inputRate;
                        outSum += outputRate;

                        Map<String, Float> map = new HashMap<>();
                        map.put("in", inSum);
                        map.put("out", outSum);

                        existDomain.put(domainSlot, map);
                    } else {
                        Map<String, Float> map = new HashMap<>();

                        map.put("in", inputRate);
                        map.put("out", outputRate);
                        existDomain.put(domainSlot, map);
                    }
                }
            }

            Iterator<Map.Entry<String, Map<String, Float>>> iterator = existDomain.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Map<String, Float>> next = iterator.next();

                String domainSlot = next.getKey();
                String[] split = domainSlot.split("/");
                int domainType = Integer.parseInt(split[0]);
                int domainId = Integer.parseInt(split[1]);

                Map<String, Float> value = next.getValue();
                float inputRate = value.get("in");
                float outputRate = value.get("out");

                TotalHistoryFlow totalHistoryFlow = new TotalHistoryFlow();
                totalHistoryFlow.setId(UUID.randomUUID().toString().replace("-", ""));
                totalHistoryFlow.setRx(inputRate);
                totalHistoryFlow.setTx(outputRate);
                totalHistoryFlow.setDomainType(domainType);
                totalHistoryFlow.setDomainId(domainId);
                System.out.println("domainType:" + domainType);
                totalHistoryFlow.setDomainName(DeviceDomainEnum.fromCode(domainType).getName());
                totalHistoryFlow.setCreateTime(currentTime);

                totalHistoryFlowRepository.save(totalHistoryFlow);
            }
        });
    }

    /**
     * 每隔5分钟清除数据库中过期的数据
     */
    @Scheduled(cron = "0 */30 * * * ?")
    @Transactional
    public void deleteHistoryFlow() {
        List<FlowCycle> flowCycleList = flowCycleRepository.findAll();
        FlowCycle flowCycle = flowCycleList.get(0);
        Integer cycle = flowCycle.getCycle();

        log.info("-------------删除过期数据---------------------");
        log.info("-------------存储周期为：{}-------------------", cycle);

        if (cycle == null) {
            cycle = 365;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.DATE, -cycle);
        Date time = calendar.getTime();

        totalHistoryFlowRepository.deleteTotalHistoryFlowByCreateTimeRange(time);
    }

    /**
     * 根据槽位号判断操作所属的域信息
     *
     * @param domainInfoVOS
     * @param slotId
     * @return
     */
    private DomainInfoVO getDomainInfoBySlotId(List<DomainInfoVO> domainInfoVOS, int slotId) {
        for (DomainInfoVO domainInfoVO : domainInfoVOS) {
            List<SlotInfoVO> slotInfoVOS = domainInfoVO.getM_tSlotMsg();
            for (SlotInfoVO slotInfoVO : slotInfoVOS) {
                if (slotId == slotInfoVO.getM_u32SlotId()) {
                    return domainInfoVO;
                }
            }
        }
        return null;
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

}
