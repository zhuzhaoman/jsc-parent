package com.zzm.service.impl.policy.systemManager.policy_received.resources;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.DeviceDomainEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.vo.DomainInfoVO;
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
public class PortResourcesGetSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {

    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.PORT_RESOURCES_GET.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        int messageCode = receiveSystemManagerDTO.getMessageCode();
        MessageCodeEnum messageCodeEnum = MessageCodeEnum.fromValue(messageCode);

        if(receiveSystemManagerDTO.getCode() == 200) {
            receiveSystemManagerDTO.setMsg("端口获取成功！");

            JSONArray objects = JSONObject.parseArray(receiveSystemManagerDTO.getData().toString());

            List<Map> result = new ArrayList<>();

            objects.forEach(o -> {
                JSONObject jsonObject = JSONObject.parseObject(o.toString());
                Map<String, Object> map = new HashMap<>();
                map.put("m_strUserName", jsonObject.get("m_strUserName"));
                List<Integer> indexNames = (List<Integer>) jsonObject.get("m_u32PortId");
                List<String> portNameList = new ArrayList<>();
                indexNames.forEach(index -> {
                    String portName = portHandel(index);
                    portNameList.add(portName);
                });
                map.put("m_u32PortId", portNameList);
                result.add(map);
            });

            receiveSystemManagerDTO.setData(result);
        }

        return receiveSystemManagerDTO;
    }

    public static String portHandel(Integer indexNumber) {

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
