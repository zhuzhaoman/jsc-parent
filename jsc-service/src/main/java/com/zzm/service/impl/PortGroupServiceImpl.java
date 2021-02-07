package com.zzm.service.impl;

import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.bo.ServiceProfileBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.sending.port_group.SystemManagerSendingPortGroupComponent;
import com.zzm.policy.system_manager.sending.port_group.SystemManagerSendingPortGroupPolicyService;
import com.zzm.policy.system_manager.sending.service_profile.SystemManagerSendingServiceProfileComponent;
import com.zzm.policy.system_manager.sending.service_profile.SystemManagerSendingServiceProfilePolicyService;
import com.zzm.service.PortGroupService;
import com.zzm.service.ServiceProfileService;
import org.springframework.stereotype.Service;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-24
 * @description:
 **/
@Service
public class PortGroupServiceImpl implements PortGroupService {

    @Override
    public ReceiveSystemManagerDTO operation(PortGroupBO portGroupBO) {
        SystemManagerSendingPortGroupPolicyService systemManagerSendingPortGroupPolicyService
                = SystemManagerSendingPortGroupComponent.systemManagerSendingPortGroupPolicyServiceMap.get(portGroupBO.getPortGroupType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingPortGroupPolicyService.dataEncapsulation(portGroupBO);

        return receiveSystemManagerDTO;
    }
}
