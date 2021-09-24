package com.zzm.service;

import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.SysLogBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.utils.PagedGridResult;

public interface LogService {
    ReceiveSystemManagerDTO operation(SysLogBO sysLogBO);

    PagedGridResult<OperationLog> getUserLog(String username, Integer page, Integer pageSize);

    void saveUserLog(OperationLog operationLog);
}
