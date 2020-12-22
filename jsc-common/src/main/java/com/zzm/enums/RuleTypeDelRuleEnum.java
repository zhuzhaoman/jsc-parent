package com.zzm.enums;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-21
 * @description:
 **/
public enum RuleTypeDelRuleEnum {

    IPV4("ipv4", 1408),
    IPV6("ipv6", 1409),
    WINDOW("window", 1412),
    FULL_CHAR("full-char", 1413),
    IMSI("imsi", 1426),
    URL("url", 1431),
    VLAN("vlan", 1432),
    ETH_MAC("eth-mac", 1433),
    PROTOCOL("protocol", 1430),
    FIX_CHAR("fix-char", 1411),
    TCP_FLAG("tcp-flag", 1410);

    private String ruleType;
    private Integer messageCode;

    RuleTypeDelRuleEnum(String ruleType, Integer messageCode) {
        this.ruleType = ruleType;
        this.messageCode = messageCode;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public Integer getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(Integer messageCode) {
        this.messageCode = messageCode;
    }

    public static RuleTypeDelRuleEnum fromCode(String ruleType) {
        for (RuleTypeDelRuleEnum r : RuleTypeDelRuleEnum.values()) {
            if (r.getRuleType().equals(ruleType)) {
                return r;
            }
        }
        return null;
    }
}
