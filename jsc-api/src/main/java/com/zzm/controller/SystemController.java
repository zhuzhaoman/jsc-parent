package com.zzm.controller;

import com.zzm.pojo.bo.SystemBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@SuppressWarnings("all")
@RestController
@RequestMapping("/system")
public class SystemController {

    public static final Logger log = LoggerFactory.getLogger(SystemController.class);

    @Resource
    private SystemService systemService;

    @PostMapping("/configSystem")
    public ReceiveSystemManagerDTO configSystem(@RequestBody SystemBO systemBO) {
        return systemService.configSystem(systemBO);
    }

    @PostMapping("/getSystemConfig")
    public ReceiveSystemManagerDTO getSystemConfig(@RequestBody SystemBO systemBO) {
        return systemService.getSystemConfig(systemBO);
    }

}
