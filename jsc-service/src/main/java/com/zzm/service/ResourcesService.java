package com.zzm.service;

import com.zzm.pojo.bo.ResourcesBO;
import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

public interface ResourcesService {

    ReceiveSystemManagerDTO addResources(ResourcesBO resourcesBO);

    ReceiveSystemManagerDTO getResources(ResourcesBO resourcesBO);

    ReceiveSystemManagerDTO releaseResources(ResourcesBO resourcesBO);
}
