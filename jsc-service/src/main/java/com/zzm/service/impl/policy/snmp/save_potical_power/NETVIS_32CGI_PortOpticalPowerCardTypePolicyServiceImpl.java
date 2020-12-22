package com.zzm.service.impl.policy.snmp.save_potical_power;

import com.zzm.dao.PortHistoryOpticalPowerRepository;
import com.zzm.pojo.PortHistoryOpticalPower;
import com.zzm.pojo.vo.DomainInfoVO;
import com.zzm.policy.snmp.port_optical_power.SnmpPortOpticalPowerCardTypePolicyService;
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
public class NETVIS_32CGI_PortOpticalPowerCardTypePolicyServiceImpl implements SnmpPortOpticalPowerCardTypePolicyService {

    @Resource
    private PortHistoryOpticalPowerRepository portHistoryOpticalPowerRepository;


    @Override
    public int policyType() {
        return 14;
    }

    @Override
    public void dataEncapsulation(int chassisNumber, int slotNumber, int portNumber, int extendNumber, int isEXTEND, int portRate, float inputOpticalPower, float outputOpticalPower, String domainName, DomainInfoVO domainInfo, Date currentTime) {


        // 端口名称
        String portName = "";

        if (isEXTEND == 1) {
            // portName = chassisNumber + "/" + slotNumber + "/" + portNumber + "_" + extendNumber;
            portName = portNumber + "_" + extendNumber;
        } else {
            // portName = chassisNumber + "/" + slotNumber + "/" + portNumber;
            portName = portNumber + "";
        }

        PortHistoryOpticalPower portHistoryOpticalPower = new PortHistoryOpticalPower();
        portHistoryOpticalPower.setId(UUID.randomUUID().toString().replace("-", ""));
        portHistoryOpticalPower.setRx(inputOpticalPower);
        portHistoryOpticalPower.setTx(outputOpticalPower);
        portHistoryOpticalPower.setDomainName(domainName);
        portHistoryOpticalPower.setDomainType(domainInfo.getM_u32Property());
        portHistoryOpticalPower.setDomainId(domainInfo.getM_u32DomainId());
        portHistoryOpticalPower.setSlotId(slotNumber);
        portHistoryOpticalPower.setPortName(portName);
        portHistoryOpticalPower.setCreateTime(currentTime);

        portHistoryOpticalPowerRepository.save(portHistoryOpticalPower);
    }
}
