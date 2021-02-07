package com.zzm.service.impl.policy.systemManager.policy_sending.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzm.enums.MessageBlockTypeEnum;
import com.zzm.enums.MessageCodeEnum;
import com.zzm.enums.MessageIdentifyEnum;
import com.zzm.enums.MessageTypeEnum;
import com.zzm.netty.ClientServerSync;
import com.zzm.pojo.bo.CVlanTypeMsgBO;
import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.bo.RuleDelBO;
import com.zzm.pojo.bo.VlanRuleBO;
import com.zzm.pojo.dto.CVlanTypeMsgDTO;
import com.zzm.pojo.dto.IPV6RuleDTO;
import com.zzm.pojo.dto.SendSystemManagerDTO;
import com.zzm.pojo.dto.VlanRuleDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.impl.policy.systemManager.policy_sending.BaseSystemManagerSendingPolicyServiceImpl;
import com.zzm.utils.BaseConversionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class VlanRuleSystemManagerSendingRulePolicyServiceImpl extends BaseSystemManagerSendingPolicyServiceImpl implements SystemManagerSendingRulePolicyService {


    @Resource
    private ClientServerSync clientServerSync;

    @Override
    public String policyType() {
        return "vlan";
    }

    /**
     * 处理List中String -> Long的问题
     *
     * @param vlanRuleBO
     * @return
     */
    private static VlanRuleDTO ruleHandle(VlanRuleBO vlanRuleBO) {
        List<CVlanTypeMsgBO> vlanTypeMsgBOList = vlanRuleBO.getM_tVlanTypeMsg();
        System.out.println(vlanRuleBO.getM_tVlanTypeMsg().toString());
        System.out.println(vlanTypeMsgBOList.size());

        VlanRuleDTO vlanRuleDTO = new VlanRuleDTO();

        BeanUtils.copyProperties(vlanRuleBO, vlanRuleDTO);
        vlanRuleDTO.setM_tVlanTypeMsg(null);

        List<CVlanTypeMsgDTO> cVlanTypeMsgDTOList = new ArrayList<>();
        try {
            for (CVlanTypeMsgBO cVlanTypeMsgBO : vlanTypeMsgBOList) {
                if (StringUtils.isNotBlank(cVlanTypeMsgBO.getM_u32VlanId()) &&
                        StringUtils.isNotBlank(cVlanTypeMsgBO.getM_u32VlanIdMask())) {
                    CVlanTypeMsgDTO cVlanTypeMsgDTO = new CVlanTypeMsgDTO();
                    cVlanTypeMsgDTO.setM_u32VlanType(cVlanTypeMsgBO.getM_u32VlanType());
                    cVlanTypeMsgDTO.setM_u32VlanId(BaseConversionUtils.hex2Long(cVlanTypeMsgBO.getM_u32VlanId()));
                    cVlanTypeMsgDTO.setM_u32VlanIdMask(BaseConversionUtils.hex2Long(cVlanTypeMsgBO.getM_u32VlanIdMask()));
                    cVlanTypeMsgDTOList.add(cVlanTypeMsgDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        vlanRuleDTO.setM_tVlanTypeMsg(cVlanTypeMsgDTOList);

        return vlanRuleDTO;
    }

    @Override
    public Object addDataEncapsulation(RuleBO ruleBO) {
        VlanRuleBO vlanRuleBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), VlanRuleBO.class);
        System.out.println("进入vlan");

        System.out.println("前端发送数据：" + ruleBO.getParam());

        VlanRuleDTO vlanRuleDTO = ruleHandle(vlanRuleBO);

        System.out.println("vlanDTO:" + vlanRuleDTO.toString());

        JSONObject jsonObject = priorityHandle(vlanRuleDTO);

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_ADD.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_ADD.getCode(),
                MessageCodeEnum.VLAN_RULE_ADD.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                jsonObject);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

    @Override
    public Object delDataEncapsulation(RuleBO ruleBO) {
        System.out.println(JSON.toJSONString(ruleBO.getParam()));
        RuleDelBO ruleDelBO = JSON.parseObject(JSON.toJSONString(ruleBO.getParam()), RuleDelBO.class);

        int ruleNumber = ruleDelBO.getM_u32RuleNum();

        int ruleType = ruleNumber > 1 ?
                MessageCodeEnum.VLAN_RULE_MORE_DEL.getReqCode() :
                MessageCodeEnum.VLAN_RULE_SOLO_DEL.getReqCode();

        Map<String, Integer> sendMap = new HashMap<>();
        sendMap.put("m_u32AclIndex", ruleDelBO.getM_u32AclIndex());

        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_DEL.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_DEL.getCode(),
                ruleType,
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                sendMap);

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }

    @Override
    public Object findDataEncapsulation(RuleBO ruleBO) {
        SendSystemManagerDTO sendSystemManagerDTO = new SendSystemManagerDTO(
                MessageBlockTypeEnum.RULE_DEL.getCode(),
                MessageIdentifyEnum.Y1.getCode(),
                MessageTypeEnum.RULE_GET.getCode(),
                MessageCodeEnum.VLAN_GET_ONE.getReqCode(),
                ruleBO.getUsername(),
                ruleBO.getDomainId(),
                ruleBO.getDomainType(),
                ruleBO.getParam());

        String content = JSONObject.toJSONString(sendSystemManagerDTO);
        Object data = clientServerSync.sendMessage(content);

        return data;
    }
}
