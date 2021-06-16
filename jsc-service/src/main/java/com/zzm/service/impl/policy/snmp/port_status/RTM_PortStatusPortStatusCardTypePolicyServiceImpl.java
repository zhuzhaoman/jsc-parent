package com.zzm.service.impl.policy.snmp.port_status;

import com.zzm.enums.CardTypeEnum;
import com.zzm.pojo.vo.SlotPortStatusVO;
import com.zzm.policy.snmp.port_status.SnmpPortStatusCardTypePolicyService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-25
 * @description: 白盒设备
 **/
@Service
public class RTM_PortStatusPortStatusCardTypePolicyServiceImpl implements SnmpPortStatusCardTypePolicyService {

    private int PORT_TOTAL = 90;
    private int CARD_TYPE = 13;

    @Override
    public int policyType() {
        return CARD_TYPE;
    }

    @Override
    public void dataEncapsulation(int indexNumber, int slotNumber, int portNumber, int portRate, int isEXTEND, int extendPortNumber, List<Map<String, Object>> result, int index, List<String> portStatusList) {
        try {
            List<SlotPortStatusVO> slotPortStatusVOList = (List<SlotPortStatusVO>) result.get(slotNumber - 1).get("list");
            if (slotPortStatusVOList.size() < PORT_TOTAL) {
                List<SlotPortStatusVO> newPortStatusVOS = Arrays.asList(new SlotPortStatusVO[PORT_TOTAL]);
                result.get(slotNumber - 1).put("list", newPortStatusVOS);
                result.get(slotNumber - 1).put("slot", "slot" + slotNumber);
                result.get(slotNumber - 1).put("cardType", CardTypeEnum.fromValue(CARD_TYPE).getMsg());
                slotPortStatusVOList = newPortStatusVOS;
            }

            // 获取端口状态
            String portStatus = portStatusList.get(index);

            SlotPortStatusVO slotPortStatusVO = new SlotPortStatusVO();
            slotPortStatusVO.setPortIndex(indexNumber);
            slotPortStatusVO.setPortNumber(portNumber);
            slotPortStatusVO.setPortStatus(portStatus);
            slotPortStatusVO.setExtend(isEXTEND == 1);
            slotPortStatusVO.setPortType(portRate == 1 ? "S" : "Q");

            if (isEXTEND == 1) {

                // 获取最父级端口
                SlotPortStatusVO portLinkStatusObject = slotPortStatusVOList.get(portNumber - 1);
                if (portLinkStatusObject == null) {
                    portLinkStatusObject = new SlotPortStatusVO();
                    portLinkStatusObject.setPortNumber(portNumber);
                    // 无状态，因为被甩纤
                    portLinkStatusObject.setPortStatus("2");
                    portLinkStatusObject.setExtend(isEXTEND == 1);
                }


                slotPortStatusVO.setPortNumber(extendPortNumber);
                slotPortStatusVO.setExtend(false);

                portLinkStatusObject.getSlotPortStatusVOList().add(slotPortStatusVO);

                slotPortStatusVOList.set(portNumber - 1, portLinkStatusObject);

            } else {
                slotPortStatusVOList.set(portNumber - 1, slotPortStatusVO);
            }

            result.get(slotNumber - 1).put("list", slotPortStatusVOList);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
