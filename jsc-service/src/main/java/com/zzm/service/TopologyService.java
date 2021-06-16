package com.zzm.service;

import com.zzm.pojo.bo.TopologyAddBO;
import com.zzm.pojo.vo.TopologyVO;

import java.util.List;

public interface TopologyService {
    void saveTopology(TopologyAddBO topologyAddBO);

    List<List> getChassisInfo(List<String> ips) throws Exception;

    TopologyVO getTopology();

    void removeTopology();

}
