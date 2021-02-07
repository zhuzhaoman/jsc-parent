package com.zzm.service;

import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

public interface PortGroupService {

    ReceiveSystemManagerDTO operation(PortGroupBO portGroupBO);
}
