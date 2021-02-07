package com.zzm.sqlite.service;

import com.zzm.sqlite.pojo.vo.Ipv4VO;
import com.zzm.sqlite.utils.PagedGridResult;

import java.util.List;

public interface Ipv4Service {

    PagedGridResult getRuleList(Integer page, Integer pageSize);

    PagedGridResult getRuleListByCriteria(Integer page, Integer pageSize, String criteria);
}
