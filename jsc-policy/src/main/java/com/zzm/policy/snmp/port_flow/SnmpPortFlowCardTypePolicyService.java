package com.zzm.policy.snmp.port_flow;

import com.zzm.pojo.vo.DomainInfoVO;

import java.util.Date;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-28
 * @description: 根据板卡的类型不同来保存端口的历史流量
 **/
public interface SnmpPortFlowCardTypePolicyService {
    /**
     * 策略类型
     *
     * @return
     */
    int policyType();

    /**
     * 封装要发送的数据
     * @param chassisNumber 机箱号
     * @param slotNumber 槽位号
     * @param portNumber 端口号
     * @param extendNumber 甩纤端口号
     * @param isEXTEND 是否甩纤
     * @param portRate 端口速率
     * @param inputRate 输入速率
     * @param outputRate 输出速率
     * @param domainName 域名称
     * @param domainInfo 域信息
     * @param currentTime 当前时间
     */
    void dataEncapsulation(int chassisNumber, int slotNumber, int portNumber, int extendNumber, int isEXTEND, int portRate, float inputRate, float outputRate, String domainName, DomainInfoVO domainInfo, Date currentTime) throws Exception;

}
