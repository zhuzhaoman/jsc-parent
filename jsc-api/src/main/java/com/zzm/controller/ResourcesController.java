package com.zzm.controller;

import com.zzm.pojo.bo.ResourcesBO;
import com.zzm.pojo.bo.RuleBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.ResourcesService;
import com.zzm.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-14
 * @description:
 **/
@SuppressWarnings("all")
@RestController
@RequestMapping("/resource")
public class ResourcesController {

    public static final Logger log = LoggerFactory.getLogger(ResourcesController.class);

    @Resource
    private ResourcesService resourcesService;

    @PostMapping("/addResource")
    public ReceiveSystemManagerDTO addResources(@RequestBody ResourcesBO resourcesBO) {
        return resourcesService.addResources(resourcesBO);
    }

    @PostMapping("/getResource")
    public ReceiveSystemManagerDTO getResources(@RequestBody ResourcesBO resourcesBO) {
        return resourcesService.getResources(resourcesBO);
    }

    @PostMapping("/releaseResource")
    public ReceiveSystemManagerDTO releaseRule(@RequestBody ResourcesBO resourcesBO) {
        return resourcesService.releaseResources(resourcesBO);
    }


}
