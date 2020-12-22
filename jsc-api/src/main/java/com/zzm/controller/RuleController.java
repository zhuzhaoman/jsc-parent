package com.zzm.controller;

import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-22
 * @description:
 **/
@SuppressWarnings("all")
@RestController
@RequestMapping("/rule")
public class RuleController {
    public static final Logger log = LoggerFactory.getLogger(RuleController.class);

    @Autowired
    private RuleService ruleService;

    @PostMapping("/addRule")
    public ReceiveSystemManagerDTO addRule(@RequestBody RuleBO ruleBO) {
        return ruleService.addRule(ruleBO);
    }

    @PostMapping("/delRule")
    public ReceiveSystemManagerDTO delRule(@RequestBody RuleBO ruleBO) {
        return ruleService.delRule(ruleBO);
    }

    @PostMapping("/delAllRule")
    public ReceiveSystemManagerDTO delAllRule(@RequestBody RuleBO ruleBO) {
        return ruleService.delAllRule(ruleBO);
    }

    @PostMapping("/delRuleByRuleType")
    public ReceiveSystemManagerDTO delRuleByRuleType(@RequestBody RuleBO ruleBO) {
        return ruleService.delRuleByRuleType(ruleBO);
    }

    @GetMapping("/hello")
    public String hello() {
        return "ok";
    }
}
