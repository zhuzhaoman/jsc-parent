package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.exception.GraceException;
import com.zzm.pojo.bo.RealTimeFlowBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.RealTimeFlowService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhuzhaoman
 * @date 2020/8/21 0021 15:32
 * @description 实时流量
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/realTimeFlow")
public class RealTimeFlowController {

    @Resource
    private RealTimeFlowService realTimeFlowService;

    /**
     * 获取端口实时流量
     * @param realTimeFlowBO 参数
     * @throws InterruptedException
     */
    @PostMapping("/get")
    @SystemLog(description = "查看实时流量")
    public ReceiveSystemManagerDTO realTimeFlow(@RequestBody RealTimeFlowBO realTimeFlowBO) throws InterruptedException {

        if ("".equals(realTimeFlowBO.getParam().trim())) {
            GraceException.display("请输入查询的端口或端口组");
            return null;
        }

        try {
            return realTimeFlowService.realTimeFlow(realTimeFlowBO);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("查询端口实时流量失败！！！");
            return null;
        }
    }

}
