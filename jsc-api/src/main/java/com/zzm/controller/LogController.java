package com.zzm.controller;

import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.SysLogBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.LogService;
import com.zzm.utils.JSONResult;
import com.zzm.utils.PagedGridResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-24
 * @description:
 **/
@SuppressWarnings("all")
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;

    @PostMapping("/syslog/operation")
    public ReceiveSystemManagerDTO operation(@RequestBody SysLogBO sysLogBO) {
        return logService.operation(sysLogBO);
    }

    @GetMapping("/userlog/list")
    public JSONResult getUserLog(@RequestParam String username,
                                 @RequestParam Integer page,
                                 @RequestParam Integer pageSize) {
        PagedGridResult<OperationLog> pagedGridResult = logService.getUserLog(username, page, pageSize);
        return JSONResult.ok(pagedGridResult);
    }

}
