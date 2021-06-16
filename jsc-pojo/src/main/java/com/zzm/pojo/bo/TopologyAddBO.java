package com.zzm.pojo.bo;

import lombok.Data;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-06-04
 * @description:
 **/
@Data
public class TopologyAddBO {

    private List<ChassisBO> chassisList;
    private List<ConnectionBO> connectionList;

}
