package com.zzm.service.impl;

import com.zzm.pojo.bo.ResourcesBO;
import com.zzm.pojo.bo.SystemBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.sending.resources.SystemManagerSendingResourcesComponent;
import com.zzm.policy.system_manager.sending.resources.SystemManagerSendingResourcesPolicyService;
import com.zzm.policy.system_manager.sending.system.SystemManagerSendingSystemComponent;
import com.zzm.policy.system_manager.sending.system.SystemManagerSendingSystemPolicyService;
import com.zzm.service.ResourcesService;
import com.zzm.service.SystemService;
import org.springframework.stereotype.Service;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class SystemServiceImpl implements SystemService {

    @Override
    public ReceiveSystemManagerDTO configSystem(SystemBO systemBO) {

        SystemManagerSendingSystemPolicyService systemManagerSendingSystemPolicyService =
                SystemManagerSendingSystemComponent.systemManagerSendingResourcesPolicyServiceMap.get(systemBO.getSystemType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingSystemPolicyService.configDataEncapsulation(systemBO);
        systemManagerSendingSystemPolicyService.recordUserLog(systemBO);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO getSystemConfig(SystemBO systemBO) {
        SystemManagerSendingSystemPolicyService systemManagerSendingSystemPolicyService =
                SystemManagerSendingSystemComponent.systemManagerSendingResourcesPolicyServiceMap.get(systemBO.getSystemType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingSystemPolicyService.getDataEncapsulation(systemBO);


        return receiveSystemManagerDTO;
    }
}
