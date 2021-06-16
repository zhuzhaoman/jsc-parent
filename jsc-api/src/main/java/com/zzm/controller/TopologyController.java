package com.zzm.controller;

import com.zzm.exception.GraceException;
import com.zzm.pojo.bo.TopologyAddBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.pojo.vo.TopologyVO;
import com.zzm.service.TopologyService;
import com.zzm.snmp.SnmpService;
import com.zzm.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 组网
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/topology")
public class TopologyController {

    public static final Logger log = LoggerFactory.getLogger(TopologyController.class);

    @Resource
    private TopologyService topologyService;
    @Resource
    private SnmpService snmpService;

    @GetMapping("/getChassisInfo")
    public JSONResult getChassisInfo(@RequestParam("ips") List<String> ips) throws Exception {
        if (ips.size() <= 0) {
            JSONResult.error("组网ip不能为空");
        }

        System.out.println(ips.toString());
        List<List> chassisInfo = topologyService.getChassisInfo(ips);
        return JSONResult.ok(chassisInfo);
    }

    @PostMapping("/saveTopology")
    public JSONResult saveTopology(@RequestBody TopologyAddBO topologyAddBO) throws Exception {

        topologyService.saveTopology(topologyAddBO);

        return JSONResult.ok();
    }

    @GetMapping("/getTopology")
    public JSONResult getTopology() {
        TopologyVO topology = topologyService.getTopology();
        return JSONResult.ok(topology);
    }

    @PostMapping("/removeTopology")
    public JSONResult removeTopology() {
        topologyService.removeTopology();
        return JSONResult.ok();
    }
}
