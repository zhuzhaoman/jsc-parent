package com.zzm.service.impl.policy.systemManager.policy_received.real_time_flow;

import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.vo.RealTimeFlowVO;
import com.zzm.policy.system_manager.received.SystemManagerReceivedPolicyService;
import com.zzm.service.impl.policy.systemManager.policy_received.BaseSystemManagerReceivedPolicyServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuzhaoman
 * @date 2020/8/24 0024 14:38
 * @description 设备域信息，systemManager返回数据
 */
@Service
@SuppressWarnings("all")
public class SlotSystemManagerReceivedReceivedPolicyServiceImpl extends BaseSystemManagerReceivedPolicyServiceImpl implements SystemManagerReceivedPolicyService {


    @Override
    public int policyMessageCode() {
        return MessageCodeEnum.FIND_FLOW_BY_SLOT.getResCode();
    }

    @Override
    public Object dataProcessing(ReceiveSystemManagerDTO receiveSystemManagerDTO) {

        int messageCode = receiveSystemManagerDTO.getMessageCode();
        MessageCodeEnum messageCodeEnum = MessageCodeEnum.fromValue(messageCode);

        int code = receiveSystemManagerDTO.getCode();

        if (code == 200) {
            receiveSystemManagerDTO.setMsg("获取槽位流量成功！！！");

            String[] params = messageCodeEnum.getParams().split(",");

            List<RealTimeFlowVO> realTimeFlowVOList = JSONObject.parseArray(receiveSystemManagerDTO.getData().toString(), RealTimeFlowVO.class);

            // 处理输出
            dataAnalysis(realTimeFlowVOList);

            receiveSystemManagerDTO.setData(realTimeFlowVOList);
        }

        return receiveSystemManagerDTO;
    }
}
