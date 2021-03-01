package com.zzm.enums;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-02
 * @description:
 **/
public enum CommonSystemManagerReceivedEnum {

    /* 规则下发 */
    IPV4_RULE_ADD(514, 1110, "IPV4规则下发"),
    IPV6_RULE_ADD(516, 1110, "IPV6规则下发"),
    TCPFLAG_RULE_ADD(518, 1110, "TCPFLAG规则下发"),
    FIXCHAR_RULE_ADD(520, 1110, "FIXCHAR规则下发"),
    WINDOW_RULE_ADD(522, 1110, "WINDOW规则下发"),
    FULLCHAR_RULE_ADD(524, 1110, "FullCHAR规则下发"),
    PROTOCOL_RULE_ADD(545, 1110, "PROTOCOL规则下发"),
    URL_RULE_ADD(547, 1110, "URL规则下发"),
    VLAN_RULE_ADD(549, 1110, "VLAN规则下发"),
    ETHMAC_RULE_ADD(551, 1110, "ETHMAC规则下发"),
    IMSI_RULE_ADD(573, 1110, "IMSI规则下发"),
    TEMPLATE_RULE_ADD(579, 1110, "配置规则模板"),
    PRIORITY_RULE_ADD(581, 1110, "配置规则优先级"),
    /* 单条规则删除 */
    IPV4_RULE_SOLO_DEL(1282, 1111, "IPV4规则单条删除"),
    IPV6_RULE_SOLO_DEL(1284, 1111, "IPV6规则单条删除"),
    TCPFLAG_RULE_SOLO_DEL(1286, 1111, "TCPFLAG规则单条删除"),
    FIXCHAR_RULE_SOLO_DEL(1288, 1111, "FIXCHAR规则单条删除"),
    WINDOW_RULE_SOLO_DEL(1290, 1111, "WINDOW规则单条删除"),
    FULLCHAR_RULE_SOLO_DEL(1292, 1111, "FullCHAR规则单条删除"),
    IMSI_RULE_SOLO_DEL(1308, 1111, "IMSI规则单条删除"),
    PROTOCOL_RULE_SOLO_DEL(1316, 1111, "PROTOCOL规则单条删除"),
    URL_RULE_SOLO_DEL(1322, 1111, "URL规则单条删除"),
    VLAN_RULE_SOLO_DEL(1324, 1111, "VLAN规则单条删除"),
    ETHMAC_RULE_SOLO_DEL(1326, 1111, "ETHMAC规则单条删除"),

    /* 批量规则删除 */
    IPV4_RULE_MORE_DEL(1345, 1111, "IPV4规则多条删除"),
    IPV6_RULE_MORE_DEL(1347, 1111, "IPV6规则多条删除"),
    TCPFLAG_RULE_MORE_DEL(1351, 1111, "TCPFLAG规则多条删除"),
    FIXCHAR_RULE_MORE_DEL(1353, 1111, "FIXCHAR规则多条删除"),
    WINDOW_RULE_MORE_DEL(1381, 1111, "WINDOW规则多条删除"),
    FULLCHAR_RULE_MORE_DEL(1377, 1111, "FullCHAR规则多条删除"),
    IMSI_RULE_MORE_DEL(1365, 1111, "IMSI规则多条删除"),
    PROTOCOL_RULE_MORE_DEL(1373, 1111, "PROTOCOL规则多条删除"),
    URL_RULE_MORE_DEL(1375, 1111, "URL规则多条删除"),
    VLAN_RULE_MORE_DEL(1379, 1111, "VLAN规则多条删除"),
    ETHMAC_RULE_MORE_DEL(1383, 1111, "ETHMAC规则多条删除"),
    RULE_ALL_DEL(1294, 1111, "全部规则删除"),

    IPV4_GET_ONE(770, 1113, "ipv4单条规则查询"),
    IPV6_GET_ONE(772, 1113, "ipv6单条规则查询"),
    WINDOW_GET_ONE(778, 1113, "window单条规则查询"),
    FULL_CHAR_GET_ONE(780, 1113, "full-char单条规则查询"),
    IMSI_GET_ONE(794, 1113, "imsi单条规则查询"),
    URL_GET_ONE(824, 1113, "url单条规则查询"),
    VLAN_GET_ONE(826, 1113, "vlan单条规则查询"),
    ETH_GET_ONE(828, 1113, "eth-mac单条规则查询"),
    PROTOCOL_GET_ONE(818, 1113, "protocol单条规则查询"),
    FIX_CHAR_GET_ONE(776, 1113, "fix-char单条规则查询"),
    TCP_FLAG_GET_ONE(774, 1113, "tcp-flag单条规则查询"),

    /* 资源配置 */
    SERVICE_PROFILE_RESOURCES_CONFIG(33089, 2220, "配置业务策略资源"),
    PORT_GROUP_RESOURCES_CONFIG(33073, 2220, "配置端口组资源"),
    SUBPORT_GROUP_RESOURCES_CONFIG(33077, 2220, "配置子端口组资源"),
    PORT_RESOURCES_CONFIG(33081, 2220, "配置端口资源"),
    RULE_CAPACITY_RESOURCES_CONFIG(33069, 2220, "配置规则容量"),
    RULE_CAPACITY_RESOURCES_SAVE(33071, 2220, "保存规则容量"),
    RULE_ID_RESOURCES_CONFIG(33085, 2220, "配置规则ID资源"),
    DYNAMIC_PORT_RESOURCES_CONFIG(33101, 2220, "配置动态端口容量"),
    /* 资源配置释放 */
    PORT_GROUP_RESOURCES_RELEASE(33075, 2220, "释放端口组资源"),
    SUBPORT_GROUP_RESOURCES_RELEASE(33079, 2220, "释放子端口组资源"),
    PORT_RESOURCES_RELEASE(33083, 2220, "释放端口资源"),
    /* 资源配置获取 */
    SERVICE_PROFILE_RESOURCES_GET(33292, 2221, "业务策略资源查询"),
    PORT_GROUP_RESOURCES_GET(33284, 2221, "查询端口组资源"),
    SUBPORT_GROUP_RESOURCES_GET(33286, 2221, "获取子端口组资源"),
    RULE_CAPACITY_RESOURCES_GET(33282, 2221, "查询规则容量资源"),
    RULE_ID_RESOURCES_GET(33290, 2221, "查询规则ID资源"),

    /* 业务策略 */
    SERVICE_PROFILE_ADD(37122, 5550, "添加业务策略"),
    SERVICE_PROFILE_DEL(37378, 5550, "删除业务策略"),
    SERVICE_PROFILE_DEL_ALL(37380, 5550, "删除全部业务策略"),
    SERVICE_PROFILE_CONFIG_RELAY(37124, 5550, "配置业务策略转发方式"),
    SERVICE_PROFILE_RECOVERY(37130, 5550, "恢复业务策略默认配置"),
    SERVICE_PROFILE_GET(37634, 5551, "查询业务策略"),


    /* 端口组 */
    PORT_GROUP_ADD(33538, 6660, "建立一个或多个端口组"),
    PORT_GROUP_ADD_INPUT(33574, 6660, "建立一个或多个输入端口组"),
    PORT_GROUP_ADD_CHILD(33542, 6660, "建立一个或多个子端口组"),
    PORT_GROUP_DEL(33794, 6660, "删除一个或多个端口组"),
    PORT_GROUP_DEL_INPUT(33808, 6660, "删除一个或多个输入端口组"),
    PORT_GROUP_DEL_CHILD(33796, 6660, "删除一个或多个子端口组"),
    PORT_GROUP_ADD_PORT_TO_GROUP(33548, 6660, "添加端口到端口组"),
    PORT_GROUP_ADD_PORT_TO_CHILD_GROUP(33550, 6660, "添加端口到子端口组中"),
    PORT_GROUP_ADD_PORT_TO_INPUT_GROUP(33576, 6660, "添加端口到输入端口组中"),
    PORT_GROUP_ADD_CHILD_GROUP_TO_INPUT_GROUP(33546, 6660, "添加子端口组到输出端口组中"),
    PORT_GROUP_DEL_PORT_TO_GROUP(33800, 6660, "从端口组中删除一个或多个端口"),
    PORT_GROUP_DEL_PORT_TO_CHILD_GROUP(33802, 6660, "从子端口组中删除一个或多个端口"),
    PORT_GROUP_DEL_PORT_TO_INPUT_GROUP(33810, 6660, "从输入端口组中删除一个或多个端口"),
    PORT_GROUP_DEL_CHILD_GROUP_TO_GROUP(33798, 6660, "从端口组中删除子端口组"),
    PORT_GROUP_CONFIG_OUTPUT_GROUP_WEIGHT(33556, 6660, "配置输出端口组中端口权重"),
    PORT_GROUP_CONFIG_GROUP_CHILD_GROUP_WEIGHT(33558, 6660, "配置输出端口组中子端口组权重"),
    PORT_GROUP_CONFIG_OUTPUT_GROUP_NAME(33540, 6660, "配置输出端口组名称"),
    PORT_GROUP_CONFIG_CHILD_GROUP_NAME(33544, 6660, "配置子端口组名称"),
    PORT_GROUP_CONFIG_FLOW_DISTRIBUTE_MODE(33578, 6660, "配置端口组流量分发模式"),
    PORT_GROUP_GET(34052, 6661, "查询端口组"),
    PORT_GROUP_GET_INPUT(34066, 6661, "查询输入端口组"),
    PORT_GROUP_GET_CHILD(34056, 6661, "查询子端口组"),
    PORT_GROUP_GET_ALL_INPUT(34068, 6661, "查询全部输入端口组"),

    /* 系统配置 */
    SYSTEM_CONFIG_DEFAULT_ACTION(539, 7770, "系统配置默认动作"),
    SYSTEM_CONFIG_UNIDENTIFIED_MESSAGE(2059, 7770, "系统配置未识别报文处理"),
    SYSTEM_CONFIG_MESSAGE_HEAD_OUTPUT(2169, 7770, "系统配置报文头部输出"),
    SYSTEM_CONFIG_MESSAGE_HEAD_DISSECTION(2097, 7770, "系统配置包问首部剥离"),
    SYSTEM_CONFIG_MESSAGE_TRUNCATION(2151, 7770, "系统配置报文截断"),
    SYSTEM_CONFIG_IP_INTERNAL_EXTERNAL_MATCHING(2093, 7770, "系统配置ip内外层匹配"),
    SYSTEM_CONFIG_MESSAGE_INFORMATION_CARRYING(2051, 7770, "系统配置报文信息携带"),
    SYSTEM_CONFIG_WINNING_STATISTICS(2069, 7770, "系统配置中标统计"),
    SYSTEM_CONFIG_AGING_SWITCH(2105, 7770, "系统配置老化开关"),
    SYSTEM_CONFIG_END_OF_GRE_TUNNEL(2129, 7770, "系统配置gre隧道终结"),
    SYSTEM_CONFIG_TUNNEL_MESSAGE_MATCHING(2123, 7770, "系统配置报文匹配"),
    SYSTEM_CONFIG_FIXED_POSITION_SIGNATURE_MATCHING(2137, 7770, "系统配置固定位置特征码偏移"),
    SYSTEM_CONFIG_MESSAGE_DE_RECONFIGURATION(2135, 7770, "报文去重配置"),
    SYSTEM_CONFIG_COMPLETE_MESSAGE_DE_RECONFIGURATION(2163, 7770, "整机报文去重开关"),
    SYSTEM_CONFIG_TCP_FLOW_MODE(2057, 7770, "配置tcp建流方式"),
    SYSTEM_CONFIG_FLOW_MANAGEMENT(526, 7770, "配置流管理"),
    SYSTEM_CONFIG_FLOW_MANAGEMENT_AGING(2053, 7770, "配置流管理老化"),
    SYSTEM_CONFIG_STREAM_LOG(2067, 7770, "配置流日志"),
    SYSTEM_CONFIG_VLAN_INFORMATION_ADDITION(2131, 7770, "配置vlan信息添加"),
    SYSTEM_CONFIG_SIP_ASSOCIATION(2133, 7770, "配置sip关联"),
    SYSTEM_CONFIG_FUZZY_MATCHING(2139, 7770, "配置模糊匹配"),
    SYSTEM_CONFIG_DPI_SEARCH_BUF_NUMBER(2155, 7770, "配置dpi查找buf数"),
    SYSTEM_CONFIG_NUMBER_OF_DPI_MATCHES(2157, 7770, "配置dpi匹配数"),
    SYSTEM_CONFIG_DPI_RULE_OFFSET_POSITION(2153, 7770, "配置dpi规则偏移位置"),
    SYSTEM_CONFIG_PERFORMANCE_TESTING(2159, 7770, "配置性能测试开关"),
    SYSTEM_CONFIG_DATA_PROCESSING_FACTORY_SETTINGS(2291, 7770, "数据处理配置恢复出厂默认设置"),
    SYSTEM_NO_ROLE(0, 7770, "用户没有权限"),
    SYSTEM_GET_DATA_PROCESSING_INFO_QUERY(2289, 7771, "数据处理配置信息查询"),

    /* 端口配置 */
    INTERFACE_UP_AND_DOWN(34320, 8880, "端口上下行配置"),
    INTERFACE_EXTEND(34329, 8880, "端口甩纤配置"),
    INTERFACE_FIBER(34316, 8880, "端口单双纤配置"),
    INTERFACE_NAME(34306, 8880, "端口描述配置"),
    INTERFACE_ROUTE(34327, 8880, "端口路由信息配置"),
    INTERFACE_GRE_PACKAGE(34340, 8880, "端口gre隧道封装配置"),
    INTERFACE_RATE(34354, 8880, "端口速率配置"),
    INTERFACE_FEC(34352, 8880, "端口fec使能配置"),
    INTERFACE_ENABLE(34325, 8880, "端口使能配置"),
    INTERFACE_ADD_GRE(34344, 8880, "端口添加gre隧道终结"),
    INTERFACE_DEL_GRE(34346, 8880, "端口删除gre隧道终结"),
    INTERFACE_CLEAR_FLOW(34529, 8880, "端口清除端口流量"),
    INTERFACE_GROUP_CLEAR_FLOW(33562, 8880, "端口清除端口组流量"),
    INTERFACE_SUB_GROUP_CLEAR_FLOW(33564, 8880, "端口清除子端口组流量"),
    INTERFACE_SLOT_CLEAR_FLOW(34820, 8880, "端口清除槽位流量"),
    INTERFACE_SHOW(34562, 8881, "端口信息查询"),
    INTERFACE_SHOW_GRE(34348, 8881, "端口查询gre隧道终结"),
    COMMON_INTERFACE_CONFIG(0, 8880, "通用端口配置枚举"),
    COMMON_INTERFACE_GET(0, 8881, "通用端口展示枚举"),

    /* 设备管理 */
    DEVICE_SHOW_NET_CONFIG( 36610, 9991, "show net-config"),
    DEVICE_SHOW_SNMP_CONFIG(36614, 9991, "show snmp server config"),
    DEVICE_ADD_SNMP_COMMUNITY( 36385, 9992, "添加团体名"),
    DEVICE_DEL_SNMP_COMMUNITY( 36387, 9992, "删除团体名"),
    DEVICE_ADD_SNMP_USER(36391, 9992, "添加用户"),
    DEVICE_DEL_SNMP_USER( 36393, 9992, "删除用户"),
    DEVICE_SAVE_SNMP_CONFIG( 36389, 9992, "保存团体名设置"),
    DEVICE_SAVE_NET_CONFIG( 36362, 9992, "保存网络设置"),
    DEVICE_ROUTE_CONFIG( 36360, 9992, "系统路由设置"),
    DEVICE_NTP_CONFIG(36364, 9992, "系统NTP设置"),
    DEVICE_GATEWAY_CONFIG( 36356, 9992, "系统网关设置"),
    DEVICE_IP_CONFIG( 36354, 9992, "系统IP设置"),
    DEVICE_NAME_CONFIG(36368, 9992, "设备描述配置"),
    DEVICE_ID_CONFIG(36435, 9992, "设备id配置"),
    DEVICE_SNMP_SYS_NAME( 36401, 9992, "snmp系统名称"),
    DEVICE_SNMP_SYS_LOCATION( 36403, 9992, "snmp系统位置"),
    DEVICE_SNMP_TRAP_IP( 36407, 9992, "snmp告警ip设置"),
    DEVICE_SNMP_RESUME_DEFAULT( 36409, 9992, "snmp恢复默认状态"),
    DEVICE_SYSTEM_UPDATE_CONFIG( 36423, 9992, "更新系统配置"),
    DEVICE_DISK_USAGE_THRESHOLD_CONFIG( 36382, 9992, "配置磁盘阈值"),
    DEVICE_PORT_TRAFFIC_THRESHOLD_CONFIG(36382, 9992, "端口流量阈值"),
    DEVICE_REBOOT_SLOT(36634, 9992, "reboot");


    private int reqCode;
    private int resCode;
    private String msg;

    CommonSystemManagerReceivedEnum(int reqCode, int resCode, String msg) {
        this.reqCode = reqCode;
        this.resCode = resCode;
        this.msg = msg;
    }

    public int getReqCode() {
        return reqCode;
    }

    public void setReqCode(int reqCode) {
        this.reqCode = reqCode;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static CommonSystemManagerReceivedEnum fromCode(int code) {
        for (CommonSystemManagerReceivedEnum c : CommonSystemManagerReceivedEnum.values()) {
            if (c.getReqCode() == code) {
                return c;
            }
        }
        return null;
    }
}
