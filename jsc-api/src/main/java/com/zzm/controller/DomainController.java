package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.exception.GraceException;
import com.zzm.pojo.bo.DeviceDomainBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.DomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-09-22
 * @description:
 **/
@SuppressWarnings("all")
@RestController
@RequestMapping("/domain")
public class DomainController {

    public static final Logger log = LoggerFactory.getLogger(DomainController.class);

    @Resource
    private DomainService domainService;

    /**
     * 获取全部域信息
     *
     * @return
     */
    @GetMapping("/all")
    @SystemLog(description = "获取域信息")
    public ReceiveSystemManagerDTO getDomainAll(String username) {
        try {
            return domainService.getDomainAll(username);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("获取域信息失败");
            return null;
        }
    }


    /**
     * 查询域信息
     *
     * @param deviceDomainBO 传递的参数
     * @throws InterruptedException
     */
    @PostMapping("/get")
    public ReceiveSystemManagerDTO getDomainById(@RequestBody DeviceDomainBO deviceDomainBO) {
        System.out.println("获取到数据：" + deviceDomainBO.toString());
        try {
            return domainService.getDomainById(deviceDomainBO);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("获取域信息失败");
            return null;
        }
    }

}
