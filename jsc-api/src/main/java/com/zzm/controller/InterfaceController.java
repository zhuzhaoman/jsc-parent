package com.zzm.controller;

import com.zzm.pojo.bo.PortBO;
import com.zzm.pojo.bo.PortGroupBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.PortGroupService;
import com.zzm.service.PortService;
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
@RequestMapping("/port")
public class InterfaceController {

    @Resource
    private PortService portService;

    @PostMapping("/operation")
    public ReceiveSystemManagerDTO operation(@RequestBody PortBO portBO) throws Exception {
        ReceiveSystemManagerDTO operation = portService.operation(portBO);
        return operation;
    }


}
