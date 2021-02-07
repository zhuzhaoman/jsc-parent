package com.zzm.sqlite.service;

import com.zzm.sqlite.pojo.vo.Ipv6VO;
import com.zzm.sqlite.utils.PagedGridResult;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-30
 * @description:
 **/
public interface Ipv6Service {

    PagedGridResult getRuleList(Integer page, Integer pageSize);

    PagedGridResult getRuleListByCriteria(Integer page, Integer pageSize, String criteria);
}
