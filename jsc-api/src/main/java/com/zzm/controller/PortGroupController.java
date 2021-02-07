package com.zzm.controller;

import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.bo.ServiceProfileBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.PortGroupService;
import com.zzm.service.ServiceProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-24
 * @description:
 **/
@SuppressWarnings("all")
@RestController
@RequestMapping("/portGroup")
public class PortGroupController {

    @Resource
    private PortGroupService portGroupService;

    @PostMapping("/operation")
    public ReceiveSystemManagerDTO operation(@RequestBody PortGroupBO portGroupBO) {
        System.out.println(portGroupBO.toString());
        return portGroupService.operation(portGroupBO);
    }


}
