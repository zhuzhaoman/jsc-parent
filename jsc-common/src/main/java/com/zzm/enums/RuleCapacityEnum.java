package com.zzm.enums;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-15
 * @description:
 **/
public enum  RuleCapacityEnum {

    IPV4("ipv4", 33026),
    IPV4_M("ipv4-m", 33025),
    IPV6("ipv6", 33028),
    IPV6_M("ipv6-m", 33027),
    FIX_CHAR("fix-char", 33030),
    IPV4_COMP_FIX("ipv4-comp-fix", 33040),
    IPV6_COMP_FIX("ipv6-comp-fix", 33041),
    IPV4_COMP_IMSI("ipv4-comp-imsi", 33055),
    IPV6_COMP_IMSI("ipv6-comp-imsi", 33056),
    IPV4_COMP_FULL("ipv4-comp-full", 33050),
    IPV6_COMP_FULL("ipv6-comp-full", 33051),
    IMSI("imsi", 33045),
    TCP_FLAT("tcp-flag", 33035),
    ETHERTYPE("ethertype", 33047),
    PROTOCOL_RULE("protocol-rule", 33049),
    URL("url", 33052),
    VLAN("vlan", 33053),
    FULL_CHAR("full-char", 33032),
    WINDOW("window", 33031),
    ETH_MAC("eth-mac", 33054);



    private String ruleType;
    private Integer messageCode;

    RuleCapacityEnum(String ruleType, Integer messageCode) {
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

    public static RuleCapacityEnum fromCode(String ruleType) {
        for (RuleCapacityEnum r : RuleCapacityEnum.values()) {
            if (r.getRuleType().equals(ruleType)) {
                return r;
            }
        }
        return null;
    }
}
