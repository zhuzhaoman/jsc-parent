package com.zzm.sqlite.service;

import com.zzm.sqlite.pojo.vo.EthMacVO;
import com.zzm.sqlite.pojo.vo.VlanVO;
import com.zzm.sqlite.utils.PagedGridResult;

import java.util.List;

public interface EthMacService {

    PagedGridResult getRuleList(Integer page, Integer pageSize);

    PagedGridResult getRuleListByCriteria(Integer page, Integer pageSize, String criteria);
}
