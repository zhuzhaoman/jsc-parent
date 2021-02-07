package com.zzm.service;

import com.zzm.pojo.bo.PortBO;
import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

public interface PortService {

    ReceiveSystemManagerDTO operation(PortBO portBO) throws Exception;
}
