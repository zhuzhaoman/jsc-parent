package com.zzm.sqlite.service;

import com.zzm.sqlite.pojo.vo.FixCharVO;
import com.zzm.sqlite.pojo.vo.Ipv4VO;
import com.zzm.sqlite.utils.PagedGridResult;

import java.util.List;

public interface FixCharService {

    PagedGridResult getRuleList(String username, Integer page, Integer pageSize);

    PagedGridResult getRuleListByCriteria(String username, Integer page, Integer pageSize, String criteria);
}
