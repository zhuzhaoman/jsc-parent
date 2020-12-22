package com.zzm.service.impl.policy.snmp.save_port_flow;

import com.zzm.dao.PortHistoryFlowRepository;
import com.zzm.pojo.PortHistoryFlow;
import com.zzm.pojo.vo.DomainInfoVO;
import com.zzm.policy.snmp.port_flow.SnmpPortFlowCardTypePolicyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-25
 * @description:
 **/
@SuppressWarnings("all")
@Service
public class NETVIS_32CGI_PortFlowCardTypePolicyServiceImpl implements SnmpPortFlowCardTypePolicyService {

    @Resource
    private PortHistoryFlowRepository portHistoryFlowRepository;


    @Override
    public int policyType() {
        return 14;
    }

    @Override
    public void dataEncapsulation(int chassisNumber, int slotNumber, int portNumber, int extendNumber, int isEXTEND, int portRate, float inputRate, float outputRate, String domainName, DomainInfoVO domainInfo, Date currentTime) {


        // 端口名称
        String portName = "";

        if (isEXTEND == 1) {
            // portName = chassisNumber + "/" + slotNumber + "/" + portNumber + "_" + extendNumber;
            portName = portNumber + "_" + extendNumber;
        } else {
            // portName = chassisNumber + "/" + slotNumber + "/" + portNumber;
            portName = portNumber + "";
        }

        PortHistoryFlow portHistoryFlow = new PortHistoryFlow();
        portHistoryFlow.setId(UUID.randomUUID().toString().replace("-", ""));
        portHistoryFlow.setRx(inputRate);
        portHistoryFlow.setTx(outputRate);
        portHistoryFlow.setDomainName(domainName);
        portHistoryFlow.setDomainType(domainInfo.getM_u32Property());
        portHistoryFlow.setDomainId(domainInfo.getM_u32DomainId());
        portHistoryFlow.setSlotId(slotNumber);
        portHistoryFlow.setPortName(portName);
        portHistoryFlow.setCreateTime(currentTime);

        portHistoryFlowRepository.save(portHistoryFlow);
    }
}
