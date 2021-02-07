package com.zzm.service;

import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.bo.ServiceProfileBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

public interface ServiceProfileService {

    ReceiveSystemManagerDTO operation(ServiceProfileBO serviceProfileBO);
}
