package com.zzm.service.impl.policy.systemManager.policy_received.port_group;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import com.zzm.utils.BaseConversionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:38
 * @description 用户登录，systemManager返回数据
 */
@Service
@SuppressWarnings("all")
public class CommonPortGroupGetSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.COMMON_PORT_GROUP_GET.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        if(receiveSystemManagerDTO.getCode() == 200) {
            receiveSystemManagerDTO.setMsg("端口组配置成功！");

            JSONArray jsonArray = JSONArray.parseArray(receiveSystemManagerDTO.getData().toString());
            JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(0).toString());
            JSONArray jsonArray1 = JSONArray.parseArray(jsonObject.get("m_tPortWeightMsg").toString());

            List<Map> result = new ArrayList<>();

            jsonArray1.forEach(item -> {
                JSONObject jsonObject1 = JSONObject.parseObject(item.toString());
                Map<String, Object> map = new HashMap<>();
                map.put("m_u32PortWeight", jsonObject1.get("m_u32PortWeight"));

                String portName = portHandel((Integer) jsonObject1.get("m_u32PortId"));
                map.put("m_u32PortId", portName);
                result.add(map);
            });

            jsonObject.put("m_tPortWeightMsg", result);
            jsonArray.set(0, jsonObject);

            receiveSystemManagerDTO.setData(jsonArray);
        }

        return receiveSystemManagerDTO;
    }

    private static String portHandel(Integer indexNumber) {

        String indexStr = BaseConversionUtils.decimal2binary(indexNumber, 32);

        /**
         * 机箱号
         */
        int chassisStart = 3;
        int chassisEnd = chassisStart + 5;
        int chassisNumber = Integer.parseInt(indexStr.substring(chassisStart, chassisEnd));
        /**
         * 所属槽位
         */
        int slotStart = 11;
        int slotEnd = slotStart + 5;
        int slotNumber = Integer.parseInt(indexStr.substring(slotStart, slotEnd), 2);
        /**
         * 端口号
         */
        int portStart = 24;
        int portEnd = portStart + 8;
        int portNumber = Integer.parseInt(indexStr.substring(portStart, portEnd), 2);
        /**
         * 扩展端口号
         */
        int extendStart = 19;
        int extendEnd = extendStart + 5;
        int extendNumber = Integer.parseInt(indexStr.substring(extendStart, extendEnd), 2);
        /**
         * 端口速率
         */
        int portRateStart = 0;
        int portRateEnd = portRateStart + 3;
        int portRate = Integer.parseInt(indexStr.substring(portRateStart, portRateEnd), 2);
        /**
         * 是否甩纤
         */
        int isEXTENDStart = 18;
        int isEXTENDEnd = isEXTENDStart + 1;
        int isEXTEND = Integer.parseInt(indexStr.substring(isEXTENDStart, isEXTENDEnd), 2);

        /**
         * 判断端口的类型
         * 端口速率类型，正交1.0(0)、10G端口(1)、100G端口(2)
         */
        String flag = (portRate == 1 ? "S " : portRate == 2 ? "Q " : "");


        // 端口名称
        String portName = "";

        if (isEXTEND == 1) {
            portName = flag + " front " + chassisNumber + "/" + slotNumber + "/" + portNumber + "_" + extendNumber;
        } else {
            portName = flag + " front " + chassisNumber + "/" + slotNumber + "/" + portNumber;
        }

        return portName;
    }
}