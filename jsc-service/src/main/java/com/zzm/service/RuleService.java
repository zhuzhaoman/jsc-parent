package com.zzm.service;

import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

public interface RuleService {

    ReceiveSystemManagerDTO addRule(RuleBO ruleBO);

    ReceiveSystemManagerDTO delRule(RuleBO ruleBO);

    ReceiveSystemManagerDTO delAllRule(RuleBO ruleBO);

    ReceiveSystemManagerDTO delRuleByRuleType(RuleBO ruleBO);
}
