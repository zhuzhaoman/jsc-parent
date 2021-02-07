package com.zzm.enums;

/**
 * @author zhuzhaoman
 * @date 2020/8/19 0019 16:05
 * @description 描述
 */
public enum MessageCodeEnum {

    CONNECT_REQUEST(257,258, "m_tZCollectionMsg,m_tSystemStatusMsg","登录"),
    DEVICE_INFO(36611, 36612,"m_tZCollectionMsg,m_tSystemStatusMsg", "设备基本信息"),
    DEVICE_DOMAIN(36097, 36098,"", "设备域信息"),
    FIND_FLOW_BY_SLOT(35075, 35076,"", "根据槽位查询端口流量"),
    FIND_FLOW_BY_INPUT(34069, 34070,"", "根据输入端口组查询端口流量"),
    FIND_FLOW_BY_OUTPUT(34057, 34058,"", "根据输出端口组查询流量信息"),
    DEVICE_THRESHOLD_CONFIG(36381, 36382,"", "设备阈值的配置"),
    DEVICE_THRESHOLD_GET(36629, 36630, "m_tZCollectionMsg,m_tReadSnmpThresholdMsg,m_tSnmpThresholdMsg", "设备阈值的获取"),
    DOMAIN_ALL_GET(36103, 36104, "", "获取设备全部域信息"),

    /**
     * 规则下发
     */
    IPV4_RULE_ADD(513, 514,"","IPV4规则下发"),
    IPV6_RULE_ADD(515, 516,"","IPV6规则下发"),
    TCPFLAG_RULE_ADD(517, 518,"", "TCPFLAG规则下发"),
    FIXCHAR_RULE_ADD(519,520,"", "FIXCHAR规则下发"),
    WINDOW_RULE_ADD(521, 522, "", "WINDOW规则下发"),
    FULLCHAR_RULE_ADD(523, 524, "", "FullCHAR规则下发"),
    PROTOCOL_RULE_ADD(544, 545, "", "PROTOCOL规则下发"),
    URL_RULE_ADD(546, 547, "", "URL规则下发"),
    VLAN_RULE_ADD(548, 549, "", "VLAN规则下发"),
    ETHMAC_RULE_ADD(550, 551, "", "ETHMAC规则下发"),
    IMSI_RULE_ADD(572, 573, "", "IMSI规则下发"),
    TEMPLATE_RULE_ADD(578, 579, "", "配置规则模板"),
    PRIORITY_RULE_ADD(580, 581, "", "配置规则优先级"),
    COMMON_RULE_ADD(0,1110, "", "通用规则下发接收消息枚举"),

    /**
     * 单条规则删除
     */
    IPV4_RULE_SOLO_DEL(1281, 1282,"","IPV4规则单条删除"),
    IPV6_RULE_SOLO_DEL(1283, 1284,"","IPV6规则单条删除"),
    TCPFLAG_RULE_SOLO_DEL(1285, 1286,"", "TCPFLAG规则单条删除"),
    FIXCHAR_RULE_SOLO_DEL(1287,1288,"", "FIXCHAR规则单条删除"),
    WINDOW_RULE_SOLO_DEL(1289, 1290, "", "WINDOW规则单条删除"),
    FULLCHAR_RULE_SOLO_DEL(1291, 1292, "", "FullCHAR规则单条删除"),
    IMSI_RULE_SOLO_DEL(1307, 1308, "", "IMSI规则单条删除"),
    PROTOCOL_RULE_SOLO_DEL(1315, 1316, "", "PROTOCOL规则单条删除"),
    URL_RULE_SOLO_DEL(1321, 1322, "", "URL规则单条删除"),
    VLAN_RULE_SOLO_DEL(1323, 1324, "", "VLAN规则单条删除"),
    ETHMAC_RULE_SOLO_DEL(1325, 1326, "", "ETHMAC规则单条删除"),

    /**
     * 批量规则删除
     */
    IPV4_RULE_MORE_DEL(1344, 1345,"","IPV4规则多条删除"),
    IPV6_RULE_MORE_DEL(1346, 1347,"","IPV6规则多条删除"),
    TCPFLAG_RULE_MORE_DEL(1350, 1351,"", "TCPFLAG规则多条删除"),
    FIXCHAR_RULE_MORE_DEL(1352,1353,"", "FIXCHAR规则多条删除"),
    WINDOW_RULE_MORE_DEL(1380, 1381, "", "WINDOW规则多条删除"),
    FULLCHAR_RULE_MORE_DEL(1376, 1377, "", "FullCHAR规则多条删除"),
    IMSI_RULE_MORE_DEL(1364, 1365, "", "IMSI规则多条删除"),
    PROTOCOL_RULE_MORE_DEL(1372, 1373, "", "PROTOCOL规则多条删除"),
    URL_RULE_MORE_DEL(1374, 1375, "", "URL规则多条删除"),
    VLAN_RULE_MORE_DEL(1378, 1379, "", "VLAN规则多条删除"),
    ETHMAC_RULE_MORE_DEL(1382, 1383, "", "ETHMAC规则多条删除"),
    RULE_ALL_DEL(1293,1294, "", "全部规则删除"),
    COMMON_RULE_DEL(0,1111, "", "通用规则删除接收消息枚举"),

    /**
     * 查询单条规则
     */

    IPV4_GET_ONE(769, 770, "m_tAclResponseMsg", "ipv4单条规则查询"),
    IPV6_GET_ONE(771, 772, "m_tAclResponseMsg", "ipv6单条规则查询"),
    WINDOW_GET_ONE(777, 778, "m_tAclResponseMsg", "window单条规则查询"),
    FULL_CHAR_GET_ONE(779, 780, "m_tAclResponseMsg", "full-char单条规则查询"),
    IMSI_GET_ONE(793, 794, "m_tAclResponseMsg", "imsi单条规则查询"),
    URL_GET_ONE(823, 824, "m_tAclResponseMsg", "url单条规则查询"),
    VLAN_GET_ONE(825, 826, "m_tAclResponseMsg", "vlan单条规则查询"),
    ETH_GET_ONE(827, 828, "m_tAclResponseMsg", "eth-mac单条规则查询"),
    PROTOCOL_GET_ONE(817, 818, "m_tAclResponseMsg", "protocol单条规则查询"),
    FIX_CHAR_GET_ONE(775, 776, "m_tAclResponseMsg", "fix-char单条规则查询"),
    TCP_FLAG_GET_ONE(773, 774, "m_tAclResponseMsg", "tcp-flag单条规则查询"),
    COMMON_RULE_GET(0,1113, "", "通用规获取则接收消息枚举"),


    /**
     * 资源配置
     */
    SERVICE_PROFILE_RESOURCES_CONFIG(33088, 33089, "", "配置业务策略资源"),
    SERVICE_PROFILE_RESOURCES_GET(33291, 33292, "", "业务策略资源查询"),
    PORT_GROUP_RESOURCES_CONFIG(33072, 33073, "", "配置端口组资源"),
    PORT_GROUP_RESOURCES_RELEASE(33074, 33075, "", "释放端口组资源"),
    PORT_GROUP_RESOURCES_GET(33283, 33284, "", "查询端口组资源"),
    SUBPORT_GROUP_RESOURCES_CONFIG(33076, 33077, "", "配置子端口组资源"),
    SUBPORT_GROUP_RESOURCES_RELEASE(33078, 33079, "", "释放子端口组资源"),
    SUBPORT_GROUP_RESOURCES_GET(33285, 33286, "", "获取子端口组资源"),
    PORT_RESOURCES_CONFIG(33080, 33081, "", "配置端口资源"),
    PORT_RESOURCES_RELEASE(33082, 33083, "", "释放端口资源"),
    PORT_RESOURCES_GET(33287, 33288, "", "查询端口资源"),
    RULE_CAPACITY_RESOURCES_CONFIG(0, 33069, "", "配置规则容量"),
    RULE_CAPACITY_RESOURCES_SAVE(33070, 33071, "", "保存规则容量"),
    RULE_CAPACITY_RESOURCES_GET(33281, 33282, "", "查询规则容量资源"),
    RULE_ID_RESOURCES_CONFIG(33084, 33085, "", "配置规则ID资源"),
    RULE_ID_RESOURCES_GET(33289, 33290, "", "查询规则ID资源"),
    DYNAMIC_PORT_RESOURCES_CONFIG(33100, 33101, "", "配置动态端口容量"),
    COMMON_RESOURCES_CONFIG_SAVE_RELEASE(0,2220, "", "通用资源配置保存释放枚举"),
    COMMON_RESOURCES_GET(0,2221, "", "通用资源配置获取枚举"),


    /**
     * 业务策略
     */
    SERVICE_PROFILE_ADD(37121, 37122, "", "添加业务策略"),
    SERVICE_PROFILE_DEL(37377, 37378, "", "删除业务策略"),
    SERVICE_PROFILE_DEL_ALL(37379, 37380, "", "删除全部业务策略"),
    SERVICE_PROFILE_GET(37633, 37634, "", "查询业务策略"),
    SERVICE_PROFILE_CONFIG_RELAY(37123, 37124, "", "配置业务策略转发方式"),
    SERVICE_PROFILE_RECOVERY(37129, 37130, "", "恢复业务策略默认配置"),
    COMMON_SERVICE_PROFILE_CONFIG(0,5550, "", "通用业务策略枚举"),
    COMMON_SERVICE_PROFILE_GET(0,5551, "", "通用系统配置获取枚举"),


    /**
     * 端口组
     */
    PORT_GROUP_ADD(33537, 33538, "", "建立一个或多个端口组"),
    PORT_GROUP_ADD_INPUT(33573, 33574, "", "建立一个或多个输入端口组"),
    PORT_GROUP_ADD_CHILD(33541, 33542, "", "建立一个或多个子端口组"),
    PORT_GROUP_DEL(33793, 33794, "", "删除一个或多个端口组"),
    PORT_GROUP_DEL_INPUT(33807, 33808, "", "删除一个或多个输入端口组"),
    PORT_GROUP_DEL_CHILD(33795, 33796, "", "删除一个或多个子端口组"),
    PORT_GROUP_ADD_PORT_TO_GROUP(33547, 33548, "", "添加端口到端口组"),
    PORT_GROUP_ADD_PORT_TO_CHILD_GROUP(33549, 33550, "", "添加端口到子端口组中"),
    PORT_GROUP_ADD_PORT_TO_INPUT_GROUP(33575, 33576, "", "添加端口到输入端口组中"),
    PORT_GROUP_ADD_CHILD_GROUP_TO_INPUT_GROUP(33545, 33546, "", "添加子端口组到输出端口组中"),
    PORT_GROUP_DEL_PORT_TO_GROUP(33799, 33800, "", "从端口组中删除一个或多个端口"),
    PORT_GROUP_DEL_PORT_TO_CHILD_GROUP(33801, 33802, "", "从子端口组中删除一个或多个端口"),
    PORT_GROUP_DEL_PORT_TO_INPUT_GROUP(33809, 33810, "", "从输入端口组中删除一个或多个端口"),
    PORT_GROUP_DEL_CHILD_GROUP_TO_GROUP(33797, 33798, "", "从端口组中删除子端口组"),
    PORT_GROUP_GET(34051, 34052, "", "查询端口组"),
    PORT_GROUP_GET_INPUT(34065, 34066, "", "查询输入端口组"),
    PORT_GROUP_GET_CHILD(34055, 34056, "", "查询子端口组"),
    PORT_GROUP_GET_ALL_INPUT(34067, 34068, "", "查询全部输入端口组"),
    PORT_GROUP_CONFIG_OUTPUT_GROUP_WEIGHT(33555, 33556, "", "配置输出端口组中端口权重"),
    PORT_GROUP_CONFIG_GROUP_CHILD_GROUP_WEIGHT(33557, 33558, "", "配置输出端口组中子端口组权重"),
    PORT_GROUP_CONFIG_OUTPUT_GROUP_NAME(33539, 33540, "", "配置输出端口组名称"),
    PORT_GROUP_CONFIG_CHILD_GROUP_NAME(33543, 33544, "", "配置子端口组名称"),
    PORT_GROUP_CONFIG_FLOW_DISTRIBUTE_MODE(33577, 33578, "", "配置端口组流量分发模式"),
    COMMON_PORT_GROUP(0,6660, "", "通用端口组枚举"),
    COMMON_PORT_GROUP_GET(0,6661, "", "通用端口组获取枚举"),


    /**
     * 系统配置
     */
    SYSTEM_CONFIG_DEFAULT_ACT(538, 539, "", "系统配置默认动作"),
    SYSTEM_CONFIG_UNKNOWN_PKT(2058, 2059, "", "系统配置未识别报文处理"),
    SYSTEM_CONFIG_PKT_HEADER_OUT(2168, 2169, "", "系统配置报文头部输出"),
    SYSTEM_CONFIG_PKT_HEADER_STRIP(2096, 2097, "", "系统配置包问首部剥离"),
    SYSTEM_CONFIG_PKT_TRUNCATION(2150, 2151, "", "系统配置报文截断"),
    SYSTEM_CONFIG_IP_MATCH_LEVEL(2092, 2093, "", "系统配置ip内外层匹配"),
    SYSTEM_CONFIG_PKT_INFO_CARRY(2050, 2051, "", "系统配置报文信息携带"),
    SYSTEM_CONFIG_HIT_STAT(2068, 2069, "", "系统配置中标统计"),
    SYSTEM_CONFIG_FLOW_SYN(2104, 2105, "", "系统配置老化开关"),
    SYSTEM_CONFIG_GRE_TERMINATE(2128, 2129, "", "系统配置gre隧道终结"),
    SYSTEM_CONFIG_TUNNEL_PKT_MATCH(2122, 2123, "", "系统配置报文匹配"),
    SYSTEM_CONFIG_FIX_CHAR_GLOBAL_BEGIN_POS(2136, 2137, "", "系统配置固定位置特征码偏移"),
    SYSTEM_CONFIG_DEL_DUPLICATE(2134, 2135, "", "报文去重配置"),
    SYSTEM_CONFIG_GLOBAL_DEL_DUPLICATE(2162, 2163, "", "整机报文去重开关"),
    SYSTEM_CONFIG_TCP_FLOW_MODEL(2056, 2057, "", "配置tcp建流方式"),
    SYSTEM_CONFIG_FLOW_MANAGE(525, 526, "", "配置流管理"),
    SYSTEM_CONFIG_FLOW_MANAGE_AGING(2052, 2053, "", "配置流管理老化"),
    SYSTEM_CONFIG_STREAM_LOG(2066, 2067, "", "配置流日志"),
    SYSTEM_CONFIG_VLAN_INFO_ADD(2130, 2131, "", "配置vlan信息添加"),
    SYSTEM_CONFIG_SIP_CORRELATION(2132, 2133, "", "配置sip关联"),
    SYSTEM_CONFIG_FUZZY_MATCH(2138, 2139, "", "配置模糊匹配"),
    SYSTEM_CONFIG_DPI_BUF_N(2154, 2155, "", "配置dpi查找buf数"),
    SYSTEM_CONFIG_DPI_MATCH_N(2156, 2157, "", "配置dpi匹配数"),
    SYSTEM_CONFIG_DPI_OFFSET(2152, 2153, "", "配置dpi规则偏移位置"),
    SYSTEM_CONFIG_DPI_SWITCH(2158, 2159, "", "配置性能测试开关"),
    SYSTEM_CONFIG_RESUME_DEFAULT(2290, 2291, "", "数据处理配置恢复出厂默认设置"),
    SYSTEM_GET_DATA_PROCESSING_INFO_QUERY(2288, 2289, "", "数据处理配置信息查询"),
    COMMON_SYSTEM_CONFIG(0,7770, "", "通用系统配置枚举"),
    COMMON_SYSTEM_CONFIG_GET(0,7771, "", "通用系统配置获取枚举"),


    /**
     * 端口
     */
    INTERFACE_UP_AND_DOWN(34319, 34320, "", "端口上下行配置"),
    INTERFACE_EXTEND(34328, 34329, "", "端口甩纤配置"),
    INTERFACE_FIBER(34315, 34316, "", "端口单双纤配置"),
    INTERFACE_NAME(34305, 34306, "", "端口描述配置"),
    INTERFACE_ROUTE(34326, 34327, "", "端口路由信息配置"),
    INTERFACE_GRE_PACKAGE(34339, 34340, "", "端口gre隧道封装配置"),
    INTERFACE_RATE(34353, 34354, "", "端口速率配置"),
    INTERFACE_FEC(34351, 34352, "", "端口fec使能配置"),
    INTERFACE_ENABLE(34324, 34325, "", "端口使能配置"),
    INTERFACE_SHOW(34561, 34562, "", "端口信息查询"),
    INTERFACE_ADD_GRE(34343, 34344, "", "端口添加gre隧道终结"),
    INTERFACE_DEL_GRE(34345, 34346, "", "端口删除gre隧道终结"),
    INTERFACE_SHOW_GRE(34347, 34348, "", "端口查询gre隧道终结"),
    INTERFACE_CLEAR_FLOW(34528, 34529, "", "端口清除端口流量"),
    INTERFACE_GROUP_CLEAR_FLOW(33561, 33562, "", "端口清除端口组流量"),
    INTERFACE_SUB_GROUP_CLEAR_FLOW(33563, 33564, "", "端口清除子端口组流量"),
    INTERFACE_SLOT_CLEAR_FLOW(34819, 34820, "", "端口清除槽位流量"),
    COMMON_INTERFACE_CONFIG(0, 8880, "","通用端口配置枚举"),
    COMMON_INTERFACE_GET(0, 8881, "", "通用端口展示枚举"),

    DEVICE_SHOW_NET_CONFIG(36609, 36610, "", "show net-config"),
    DEVICE_SHOW_SNMP_CONFIG(36613, 36614, "", "show snmp server config"),
    DEVICE_ADD_SNMP_COMMUNITY(36384, 36385, "", "添加团体名"),
    DEVICE_DEL_SNMP_COMMUNITY(36386, 36387, "", "删除团体名"),
    DEVICE_ADD_SNMP_USER(36390, 36391, "", "添加用户"),
    DEVICE_DEL_SNMP_USER(36392, 36393, "", "删除用户"),
    DEVICE_SAVE_SNMP_CONFIG(36388, 36389, "", "保存团体名设置"),
    DEVICE_SAVE_NET_CONFIG(36361, 36362, "", "保存网络设置"),
    DEVICE_CLOCK_CONFIG(36359, 36360, "", "系统时间设置"),
    DEVICE_ROUTE_CONFIG(36365, 36366, "", "系统路由设置"),
    DEVICE_NTP_CONFIG(36363, 36364, "", "系统NTP设置"),
    DEVICE_GATEWAY_CONFIG(36355, 36356, "", "系统网关设置"),
    DEVICE_IP_CONFIG(36353, 36354, "", "系统IP设置"),
    DEVICE_NAME_CONFIG(36367, 36368, "", "设备描述配置"),
    DEVICE_ID_CONFIG(36434, 36435, "", "设备id配置"),
    DEVICE_SNMP_SYS_NAME(36400, 36401, "", "snmp系统名称"),
    DEVICE_SNMP_SYS_LOCATION(36402, 36403, "", "snmp系统位置"),
    DEVICE_SNMP_TRAP_IP(36406, 36407, "", "snmp告警ip设置"),
    DEVICE_SNMP_RESUME_DEFAULT(36408, 36409, "", "snmp恢复默认状态"),
    DEVICE_SYSTEM_UPDATE_CONFIG(36422, 36423, "", "更新系统配置"),
    DEVICE_DISK_USAGE_THRESHOLD_CONFIG(36381, 36382, "", "配置磁盘阈值"),
    DEVICE_PORT_TRAFFIC_THRESHOLD_CONFIG(36381, 36382, "", "端口流量阈值"),
    DEVICE_REBOOT_SLOT(36633, 36634, "", "reboot"),
    COMMON_DEVICE_GET(0, 9991, "", "通用设备展示枚举"),
    COMMON_DEVICE_CONFIG(0, 9992, "", "通用设备配置枚举");


    private int reqCode;
    private int resCode;
    private String params;
    private String msg;

    MessageCodeEnum(int reqCode, int resCode, String params, String msg) {
        this.reqCode = reqCode;
        this.resCode = resCode;
        this.params = params;
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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static MessageCodeEnum fromValue(int resCode)
    {
        for (MessageCodeEnum m : MessageCodeEnum.values())
        {
            if (m.getResCode() == resCode)
            {
                return m;
            }
        }
        return null;
    }
}
