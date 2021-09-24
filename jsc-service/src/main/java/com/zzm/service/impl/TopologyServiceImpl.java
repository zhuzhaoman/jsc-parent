package com.zzm.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzm.dao.ChassisInfoRepository;
import com.zzm.dao.PortConnectionRepository;
import com.zzm.exception.GraceException;
import com.zzm.pojo.ChassisInfo;
import com.zzm.pojo.bo.ChassisBO;
import com.zzm.pojo.bo.ConnectionBO;
import com.zzm.pojo.bo.TopologyAddBO;
import com.zzm.pojo.vo.PortConnection;
import com.zzm.pojo.vo.TopologyVO;
import com.zzm.service.TopologyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-05
 * @description:
 **/
@Service
public class TopologyServiceImpl implements TopologyService {

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private ChassisInfoRepository chassisInfoRepository;
    @Autowired
    private PortConnectionRepository portConnectionRepository;

    @Override
    @Transactional
    public void saveTopology(TopologyAddBO topologyAddBO) {

        chassisInfoRepository.deleteAll();
        portConnectionRepository.deleteAll();

        List<ChassisBO> chassisList = topologyAddBO.getChassisList();
        chassisList.forEach(chassisBO -> {
            ChassisInfo chassisInfo = new ChassisInfo();
            BeanUtils.copyProperties(chassisBO, chassisInfo);
            chassisInfoRepository.save(chassisInfo);
        });

        List<ConnectionBO> connectionList = topologyAddBO.getConnectionList();
        connectionList.forEach(conn -> {
            PortConnection portConnection = new PortConnection();
            BeanUtils.copyProperties(conn, portConnection);

            portConnectionRepository.save(portConnection);
        });
    }

    @Override
    public List<List> getChassisInfo(List<String> ips) {
        List<List> result = new ArrayList<>();

        ips.forEach(ip -> {
            try {
                String url = String.join("", "https://", ip, ":8080/portStatus/all");
                // 发送请求
                Object response = restTemplate.getForObject(url, Object.class);
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(response));
                JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("data"));
                result.add(jsonArray);
            } catch (Exception e) {
                e.printStackTrace();
                GraceException.display("组网失败，请检查ip地址");
            }
        });

        return result;
    }

    @Override
    public TopologyVO getTopology() {
        List<ChassisInfo> chassisInfoList = chassisInfoRepository.findAll();
        List<PortConnection> portConnectionList = portConnectionRepository.findAll();

        TopologyVO topologyVO = new TopologyVO();
        topologyVO.setChassisInfoList(chassisInfoList);
        topologyVO.setPortConnectionList(portConnectionList);
        topologyVO.setStatus(chassisInfoList.size() > 0);

        return topologyVO;
    }

    @Override
    @Transactional
    public void removeTopology() {
        chassisInfoRepository.deleteAll();
        portConnectionRepository.deleteAll();
    }
}
