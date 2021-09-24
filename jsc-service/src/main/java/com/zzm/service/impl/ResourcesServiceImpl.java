package com.zzm.service.impl;

import com.zzm.pojo.bo.ResourcesBO;
import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.sending.resources.SystemManagerSendingResourcesComponent;
import com.zzm.policy.system_manager.sending.resources.SystemManagerSendingResourcesPolicyService;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRuleComponent;
import com.zzm.policy.system_manager.sending.rule.SystemManagerSendingRulePolicyService;
import com.zzm.service.ResourcesService;
import com.zzm.service.RuleService;
import org.springframework.stereotype.Service;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@Service
public class ResourcesServiceImpl implements ResourcesService {

    @Override
    public ReceiveSystemManagerDTO addResources(ResourcesBO resourcesBO) {
        System.out.println("参数：" + resourcesBO.toString());
        SystemManagerSendingResourcesPolicyService systemManagerSendingResourcesPolicyService =
                SystemManagerSendingResourcesComponent.systemManagerSendingResourcesPolicyServiceMap.get(resourcesBO.getResourceType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingResourcesPolicyService.configDataEncapsulation(resourcesBO);
        systemManagerSendingResourcesPolicyService.recordConfigOrReleaseUserLog(resourcesBO, true);

        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO getResources(ResourcesBO resourcesBO) {
        SystemManagerSendingResourcesPolicyService systemManagerSendingResourcesPolicyService =
                SystemManagerSendingResourcesComponent.systemManagerSendingResourcesPolicyServiceMap.get(resourcesBO.getResourceType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingResourcesPolicyService.getDataEncapsulation(resourcesBO);
        systemManagerSendingResourcesPolicyService.recordGetUserLog(resourcesBO);


        return receiveSystemManagerDTO;
    }

    @Override
    public ReceiveSystemManagerDTO releaseResources(ResourcesBO resourcesBO) {
        SystemManagerSendingResourcesPolicyService systemManagerSendingResourcesPolicyService =
                SystemManagerSendingResourcesComponent.systemManagerSendingResourcesPolicyServiceMap.get(resourcesBO.getResourceType());

        ReceiveSystemManagerDTO receiveSystemManagerDTO =
                (ReceiveSystemManagerDTO) systemManagerSendingResourcesPolicyService.releaseDataEncapsulation(resourcesBO);
        systemManagerSendingResourcesPolicyService.recordConfigOrReleaseUserLog(resourcesBO, false);

        return receiveSystemManagerDTO;
    }
}
