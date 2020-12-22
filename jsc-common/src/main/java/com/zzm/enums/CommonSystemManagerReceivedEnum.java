package com.zzm.enums;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-02
 * @description:
 **/
public enum CommonSystemManagerReceivedEnum {

    /* 规则下发 */
    IPV4_RULE_ADD(514, 1111 ,"IPV4规则下发"),
    IPV6_RULE_ADD(516, 1111 ,"IPV6规则下发"),
    TCPFLAG_RULE_ADD(518, 1111 , "TCPFLAG规则下发"),
    FIXCHAR_RULE_ADD(520, 1111 , "FIXCHAR规则下发"),
    WINDOW_RULE_ADD(522, 1111 , "WINDOW规则下发"),
    FULLCHAR_RULE_ADD(524, 1111 , "FullCHAR规则下发"),
    PROTOCOL_RULE_ADD(545, 1111 , "PROTOCOL规则下发"),
    URL_RULE_ADD(547, 1111 , "URL规则下发"),
    VLAN_RULE_ADD(549, 1111 , "VLAN规则下发"),
    ETHMAC_RULE_ADD(551, 1111 , "ETHMAC规则下发"),
    IMSI_RULE_ADD(573, 1111 ,  "IMSI规则下发"),
    TEMPLATE_RULE_ADD(579, 1111 ,  "配置规则模板"),
    PRIORITY_RULE_ADD(581, 1111 ,  "配置规则优先级"),
    /* 单条规则删除 */
    IPV4_RULE_SOLO_DEL(1282,2222,"IPV4规则单条删除"),
    IPV6_RULE_SOLO_DEL(1284,2222,"IPV6规则单条删除"),
    TCPFLAG_RULE_SOLO_DEL( 1286,2222, "TCPFLAG规则单条删除"),
    FIXCHAR_RULE_SOLO_DEL(1288,2222, "FIXCHAR规则单条删除"),
    WINDOW_RULE_SOLO_DEL(1290, 2222, "WINDOW规则单条删除"),
    FULLCHAR_RULE_SOLO_DEL(1292, 2222, "FullCHAR规则单条删除"),
    IMSI_RULE_SOLO_DEL(1308, 2222, "IMSI规则单条删除"),
    PROTOCOL_RULE_SOLO_DEL(1316, 2222, "PROTOCOL规则单条删除"),
    URL_RULE_SOLO_DEL(1322, 2222, "URL规则单条删除"),
    VLAN_RULE_SOLO_DEL(1324, 2222, "VLAN规则单条删除"),
    ETHMAC_RULE_SOLO_DEL(1326, 2222, "ETHMAC规则单条删除"),
    /* 批量规则删除 */
    IPV4_RULE_MORE_DEL(1345,2222,"IPV4规则多条删除"),
    IPV6_RULE_MORE_DEL(1347,2222,"IPV6规则多条删除"),
    TCPFLAG_RULE_MORE_DEL( 1351,2222, "TCPFLAG规则多条删除"),
    FIXCHAR_RULE_MORE_DEL(1353,2222, "FIXCHAR规则多条删除"),
    WINDOW_RULE_MORE_DEL(1381, 2222, "WINDOW规则多条删除"),
    FULLCHAR_RULE_MORE_DEL(1377, 2222, "FullCHAR规则多条删除"),
    IMSI_RULE_MORE_DEL(1365, 2222, "IMSI规则多条删除"),
    PROTOCOL_RULE_MORE_DEL( 1373, 2222, "PROTOCOL规则多条删除"),
    URL_RULE_MORE_DEL(1375, 2222, "URL规则多条删除"),
    VLAN_RULE_MORE_DEL(1379, 2222, "VLAN规则多条删除"),
    ETHMAC_RULE_MORE_DEL(1383, 2222, "ETHMAC规则多条删除"),
    RULE_ALL_DEL(1294, 2222, "全部规则删除"),

    /* 资源配置 */
    SERVICE_PROFILE_RESOURCES_CONFIG(33089, 3333,  "配置业务策略资源"),
    PORT_GROUP_RESOURCES_CONFIG(33073, 3333,  "配置端口组资源"),
    SUBPORT_GROUP_RESOURCES_CONFIG(33077, 3333,  "配置子端口组资源"),
    PORT_RESOURCES_CONFIG(33081, 3333,  "配置端口资源"),
    RULE_CAPACITY_RESOURCES_CONFIG(33069, 3333,  "配置规则容量"),
    RULE_CAPACITY_RESOURCES_SAVE(33071, 3333,  "保存规则容量"),
    RULE_ID_RESOURCES_CONFIG(33085, 3333,  "配置规则ID资源"),
    DYNAMIC_PORT_RESOURCES_CONFIG(33101, 3333,  "配置动态端口容量"),
    /* 资源配置释放 */
    PORT_GROUP_RESOURCES_RELEASE(33075, 3333,  "释放端口组资源"),
    SUBPORT_GROUP_RESOURCES_RELEASE(33079, 3333,  "释放子端口组资源"),
    PORT_RESOURCES_RELEASE(33083, 3333,  "释放端口资源"),
    /* 资源配置获取 */
    SERVICE_PROFILE_RESOURCES_GET(33292, 4444,  "业务策略资源查询"),
    PORT_GROUP_RESOURCES_GET(33284, 4444,  "查询端口组资源"),
    SUBPORT_GROUP_RESOURCES_GET(33286, 4444,  "获取子端口组资源"),
    RULE_CAPACITY_RESOURCES_GET(33282, 4444,  "查询规则容量资源"),
    RULE_ID_RESOURCES_GET(33290, 4444,  "查询规则ID资源"),

    /* 系统配置 */
    SYSTEM_CONFIG_DEFAULT_ACTION(539, 7777, "系统配置默认动作"),
    SYSTEM_CONFIG_UNIDENTIFIED_MESSAGE(2059, 7777, "系统配置未识别报文处理"),
    SYSTEM_CONFIG_MESSAGE_HEAD_OUTPUT(2169, 7777, "系统配置报文头部输出"),
    SYSTEM_CONFIG_MESSAGE_HEAD_DISSECTION(2097, 7777, "系统配置包问首部剥离"),
    SYSTEM_CONFIG_MESSAGE_TRUNCATION(2151, 7777, "系统配置报文截断"),
    SYSTEM_CONFIG_IP_INTERNAL_EXTERNAL_MATCHING(2093, 7777, "系统配置ip内外层匹配"),
    SYSTEM_CONFIG_MESSAGE_INFORMATION_CARRYING(2051, 7777, "系统配置报文信息携带"),
    SYSTEM_CONFIG_WINNING_STATISTICS( 2069, 7777, "系统配置中标统计"),
    SYSTEM_CONFIG_AGING_SWITCH(2105, 7777, "系统配置老化开关"),
    SYSTEM_CONFIG_END_OF_GRE_TUNNEL(2129, 7777, "系统配置gre隧道终结"),
    SYSTEM_CONFIG_TUNNEL_MESSAGE_MATCHING(2123, 7777, "系统配置报文匹配"),
    SYSTEM_CONFIG_FIXED_POSITION_SIGNATURE_MATCHING(2137, 7777, "系统配置固定位置特征码偏移"),
    SYSTEM_CONFIG_MESSAGE_DE_RECONFIGURATION(2135, 7777, "报文去重配置"),
    SYSTEM_CONFIG_COMPLETE_MESSAGE_DE_RECONFIGURATION( 2163, 7777, "整机报文去重开关"),
    SYSTEM_CONFIG_TCP_FLOW_MODE(2057, 7777, "配置tcp建流方式"),
    SYSTEM_CONFIG_FLOW_MANAGEMENT(526, 7777, "配置流管理"),
    SYSTEM_CONFIG_FLOW_MANAGEMENT_AGING(2053, 7777, "配置流管理老化"),
    SYSTEM_CONFIG_STREAM_LOG(2067, 7777, "配置流日志"),
    SYSTEM_CONFIG_VLAN_INFORMATION_ADDITION(2131, 7777, "配置vlan信息添加"),
    SYSTEM_CONFIG_SIP_ASSOCIATION(2133, 7777, "配置sip关联"),
    SYSTEM_CONFIG_FUZZY_MATCHING(2139, 7777, "配置模糊匹配"),
    SYSTEM_CONFIG_DPI_SEARCH_BUF_NUMBER(2155, 7777, "配置dpi查找buf数"),
    SYSTEM_CONFIG_NUMBER_OF_DPI_MATCHES(2157, 7777, "配置dpi匹配数"),
    SYSTEM_CONFIG_DPI_RULE_OFFSET_POSITION(2153, 7777, "配置dpi规则偏移位置"),
    SYSTEM_CONFIG_PERFORMANCE_TESTING(2159, 7777, "配置性能测试开关"),
    SYSTEM_CONFIG_DATA_PROCESSING_FACTORY_SETTINGS(2291, 7777, "数据处理配置恢复出厂默认设置"),

    SYSTEM_GET_DATA_PROCESSING_INFO_QUERY(2289, 8888, "数据处理配置信息查询");


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
