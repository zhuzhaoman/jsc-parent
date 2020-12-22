package com.zzm.service;

import com.zzm.pojo.bo.SystemBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

public interface SystemService {

    ReceiveSystemManagerDTO configSystem(SystemBO systemBO);

    ReceiveSystemManagerDTO getSystemConfig(SystemBO systemBO);
}
