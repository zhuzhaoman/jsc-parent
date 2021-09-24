package com.zzm.service.impl;

import com.zzm.pojo.bo.ServiceProfileBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.sending.service_profile.SystemManagerSendingServiceProfileComponent;
import com.zzm.policy.system_manager.sending.service_profile.SystemManagerSendingServiceProfilePolicyService;
import com.zzm.service.ServiceProfileService;
import org.springframework.stereotype.Service;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-24
 * @description:
 **/
@Service
public class ServiceProfileServiceImpl implements ServiceProfileService {

    @Override
    public ReceiveSystemManagerDTO operation(ServiceProfileBO serviceProfileBO) {

        System.out.println("接收数据：" + serviceProfileBO.toString());
        SystemManagerSendingServiceProfilePolicyService systemManagerSendingServiceProfilePolicyService
                = SystemManagerSendingServiceProfileComponent.systemManagerSendingServiceProfilePolicyServiceMap.get(serviceProfileBO.getServiceProfileType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingServiceProfilePolicyService.dataEncapsulation(serviceProfileBO);
        systemManagerSendingServiceProfilePolicyService.recordUserLog(serviceProfileBO);

        return receiveSystemManagerDTO;
    }
}
