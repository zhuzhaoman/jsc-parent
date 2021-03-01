package com.zzm.service.impl;

import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.bo.RuleQueryBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRuleComponent;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.RuleService;
import com.zzm.service.impl.policy.systemManager.policy_sending.rule.DelRuleByRulePolicyServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private DelRuleByRulePolicyServiceImpl delRuleByRulePolicyService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ReceiveSystemManagerDTO addRule(RuleBO ruleBO) {

        SystemManagerSendingRulePolicyService systemManagerSendingRulePolicyService =
                SystemManagerSendingRuleComponent.systemManagerSendingRulePolicyServiceMap.get(ruleBO.getRuleType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingRulePolicyService.addDataEncapsulation(ruleBO);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO getRuleById(RuleBO ruleBO) {
        SystemManagerSendingRulePolicyService systemManagerSendingRulePolicyService =
                SystemManagerSendingRuleComponent.systemManagerSendingRulePolicyServiceMap.get(ruleBO.getRuleType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingRulePolicyService.findDataEncapsulation(ruleBO);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO delRule(RuleBO ruleBO) {
        SystemManagerSendingRulePolicyService systemManagerSendingRulePolicyService =
                SystemManagerSendingRuleComponent.systemManagerSendingRulePolicyServiceMap.get(ruleBO.getRuleType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingRulePolicyService.delDataEncapsulation(ruleBO);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO delAllRule(RuleBO ruleBO) {

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) delRuleByRulePolicyService.delAllRule(ruleBO);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO delRuleByRuleType(RuleBO ruleBO) {
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) delRuleByRulePolicyService.delRuleByRuleType(ruleBO);

        return receiveSystemManagerDTO;
    }


    @Override
    public ReceiveSystemManagerDTO getRuleList(RuleQueryBO ruleQueryBO) {

        String url = "http://127.0.0.1:9000/" + ruleQueryBO.getRuleType();

        if (StringUtils.isBlank(ruleQueryBO.getCriteria())) {
            url = url + "/getRuleList?page=" + ruleQueryBO.getPage()
                    + "&pageSize=" + ruleQueryBO.getPageSize();
        } else {
            String criteria = ruleQueryBO.getCriteria();
            url = url + "/getRuleListByCriteria?page=" + ruleQueryBO.getPage()
                    + "&pageSize=" + ruleQueryBO.getPageSize()
                    + "&criteria=" + criteria.substring(1, criteria.length() - 1);
        }

        // 发送请求
        Object result = restTemplate.getForObject(url, Object.class);

        ReceiveSystemManagerDTO receiveSystemManagerDTO = new ReceiveSystemManagerDTO();
        receiveSystemManagerDTO.setCode(200);
        receiveSystemManagerDTO.setMsg("OK");
        receiveSystemManagerDTO.setData(result);

        return receiveSystemManagerDTO;
    }

}
