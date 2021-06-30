package com.zzm.service.impl;

import com.zzm.pojo.bo.PortBO;
import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.sending.port.SystemManagerSendingPortComponent;
import com.zzm.policy.system_manager.sending.port.SystemManagerSendingPortPolicyService;
import com.zzm.policy.system_manager.sending.port_group.SystemManagerSendingPortGroupComponent;
import com.zzm.policy.system_manager.sending.port_group.SystemManagerSendingPortGroupPolicyService;
import com.zzm.service.PortGroupService;
import com.zzm.service.PortService;
import org.springframework.stereotype.Service;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-24
 * @description:
 **/
@Service
public class PortServiceImpl implements PortService {

    @Override
    public ReceiveSystemManagerDTO operation(PortBO portBO) throws Exception {
        SystemManagerSendingPortPolicyService systemManagerSendingPortPolicyService
                = SystemManagerSendingPortComponent.systemManagerSendingPolicyServiceMap.get(portBO.getPortType());

        System.out.println(portBO.getPortType());
        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingPortPolicyService.dataEncapsulation(portBO);

        return receiveSystemManagerDTO;
    }
}
