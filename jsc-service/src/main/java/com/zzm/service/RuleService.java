package com.zzm.service;

import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.bo.RuleQueryBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

public interface RuleService {

    ReceiveSystemManagerDTO addRule(RuleBO ruleBO);

    ReceiveSystemManagerDTO delRule(RuleBO ruleBO);

    ReceiveSystemManagerDTO delAllRule(RuleBO ruleBO);

    ReceiveSystemManagerDTO delRuleByRuleType(RuleBO ruleBO);

    ReceiveSystemManagerDTO getRuleList(RuleQueryBO ruleQueryBO);

    ReceiveSystemManagerDTO getRuleById(RuleBO ruleBO);
}
