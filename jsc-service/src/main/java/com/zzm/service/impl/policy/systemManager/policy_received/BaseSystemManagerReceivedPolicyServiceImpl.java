package com.zzm.service.impl.policy.systemManager.policy_received;

import com.zzm.pojo.vo.RealTimeFlowVO;
import com.zzm.utils.BaseConversionUtils;

import java.util.List;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 17:15
 * @description 描述
 */
public class BaseSystemManagerReceivedPolicyServiceImpl {

    /**
     * 端口实时流量数据处理方法
     *
     * @param realTimeFlowDTOList
     */
    public void dataAnalysis(List<RealTimeFlowVO> realTimeFlowDTOList) {

        RealTimeFlowVO sumRealTimeFlow = new RealTimeFlowVO();

        realTimeFlowDTOList.stream().forEach(realTimeFlowDTO -> {

            // 将int数据转换为指定长度二进制
            String indexStr = BaseConversionUtils.decimal2binary(realTimeFlowDTO.getM_u32PortId(), 32);

            /**
             * 机箱号
             */
            int crateStart = indexStr.length() - 29;
            int crateEnd = crateStart + 5;
            int crateNumber = Integer.parseInt(indexStr.substring(crateStart, crateEnd));

            /**
             * 所属槽位
             */
            int slotStart = indexStr.length() - 21;
            int slotEnd = slotStart + 5;
            int slotNumber = Integer.parseInt(indexStr.substring(slotStart, slotEnd), 2);

            /**
             * 获取丝印号，并获取到其端口号，并将端口号从新赋值给本体
             * 获取端口号，并将其转换为整型
             */
            int portNumber = Integer.parseInt(indexStr.substring(indexStr.length() - 8), 2);

            /**
             * 扩展端口号
             */
            int extendStart = indexStr.length() - 13;
            int extendEnd = extendStart + 5;
            int extendNumber = Integer.parseInt(indexStr.substring(extendStart, extendEnd));

            /**
             * 是否甩纤
             */
            int isEXTENDStart = indexStr.length() - 14;
            int isEXTENDEnd = isEXTENDStart + 1;
            int isEXTEND = Integer.parseInt(indexStr.substring(isEXTENDStart, isEXTENDEnd));

            /**
             * 端口速率
             */
            int portRateStart = indexStr.length() - 32;
            int portRateEnd = portRateStart + 3;
            int portRate = Integer.parseInt(indexStr.substring(portRateStart, portRateEnd), 2);

            /**
             * 设置端口id
             */
            realTimeFlowDTO.setM_u32PortId(portNumber);

            /**
             * 判断端口的类型
             * 端口速率类型，正交1.0(0)、10G端口(1)、100G端口(2)
             */
            String flag = (portRate == 1 ? "S " : portRate == 2 ? "Q " : "");

            if(flag.equals("")) {
                if (isEXTEND == 1) {
                    realTimeFlowDTO.setPortId(portNumber + "_" + BaseConversionUtils.binary2decimal(extendNumber));
                } else {
                    realTimeFlowDTO.setPortId(portNumber + "");
                }
            } else {
                if (isEXTEND == 1) {
                    realTimeFlowDTO.setPortId(flag + crateNumber + "/" + slotNumber + "/" + portNumber + "_" + BaseConversionUtils.binary2decimal(extendNumber));
                } else {
                    realTimeFlowDTO.setPortId(flag + crateNumber + "/" + slotNumber + "/" + portNumber);
                }
            }


            /**
             * 输入
             */
            sumRealTimeFlow.setM_u64InBytes(sumRealTimeFlow.getM_u64InBytes() + realTimeFlowDTO.getM_u64InBytes());
            sumRealTimeFlow.setM_u64InPckts(sumRealTimeFlow.getM_u64InPckts() + realTimeFlowDTO.getM_u64InPckts());
            sumRealTimeFlow.setM_u64InBitRate(sumRealTimeFlow.getM_u64InBitRate() + realTimeFlowDTO.getM_u64InBitRate());
            sumRealTimeFlow.setM_u64InPktRate(sumRealTimeFlow.getM_u64InPktRate() + realTimeFlowDTO.getM_u64InPktRate());
            sumRealTimeFlow.setM_u64InDiscards(sumRealTimeFlow.getM_u64InDiscards() + realTimeFlowDTO.getM_u64InDiscards());
            sumRealTimeFlow.setM_u64InErrors(sumRealTimeFlow.getM_u64InErrors() + realTimeFlowDTO.getM_u64InErrors());

            /**
             * 输出
             */
            sumRealTimeFlow.setM_u64OutBytes(sumRealTimeFlow.getM_u64OutBytes() + realTimeFlowDTO.getM_u64OutBytes());
            sumRealTimeFlow.setM_u64OutPckts(sumRealTimeFlow.getM_u64OutPckts() + realTimeFlowDTO.getM_u64OutPckts());
            sumRealTimeFlow.setM_u64OutBitRate(sumRealTimeFlow.getM_u64OutBitRate() + realTimeFlowDTO.getM_u64OutBitRate());
            sumRealTimeFlow.setM_u64OutPktRate(sumRealTimeFlow.getM_u64OutPktRate() + realTimeFlowDTO.getM_u64OutPktRate());
            sumRealTimeFlow.setM_u64OutDiscards(sumRealTimeFlow.getM_u64OutDiscards() + realTimeFlowDTO.getM_u64OutDiscards());
            sumRealTimeFlow.setM_u64OutErrors(sumRealTimeFlow.getM_u64OutErrors() + realTimeFlowDTO.getM_u64OutErrors());
        });

        sumRealTimeFlow.setPortId("all");
        realTimeFlowDTOList.add(0, sumRealTimeFlow);
    }
}
