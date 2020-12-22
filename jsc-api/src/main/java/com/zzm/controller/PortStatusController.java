package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.exception.GraceException;
import com.zzm.pojo.vo.PortMessageVO;
import com.zzm.snmp.SnmpService;
import com.zzm.utils.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhuzhaoman
 * @date 2020/8/10 0010 16:26
 * @description 端口状态查询
 */
@RestController
@RequestMapping("/portStatus")
public class PortStatusController {

    @Resource
    private SnmpService snmpService;

    /**
     * 查询所有端口状态
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/all")
    @SystemLog(description = "获取端口状态")
    public JSONResult allSlotPortStatus() {

        try {
            List<Map<String, Object>> allSlotPortStatus = snmpService.allSlotPortStatus();
            return JSONResult.ok(allSlotPortStatus);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("");
        }

        return JSONResult.error("获取端口状态失败");

    }

    /**
     * 获取指定单个或多个端口信息
     * @param portIndexList 端口号，多个用 ","隔开
     * @return
     */
    @GetMapping("/appointStatus")
    @SystemLog(description = "获取单个端口信息")
    public JSONResult appointStatus(@RequestParam("portIndexList") String portIndexList) {
        System.out.println("端口丝印号：" + portIndexList);

        List<PortMessageVO> portMessageVOList = snmpService.appointStatus(portIndexList);

        return JSONResult.ok(portMessageVOList);
    }
}
