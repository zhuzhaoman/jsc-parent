package com.zzm.sqlite.service;

import com.zzm.sqlite.pojo.vo.ImsiVO;
import com.zzm.sqlite.utils.PagedGridResult;

import java.util.List;

public interface ImsiService {

    PagedGridResult getRuleList(Integer page, Integer pageSize);

    PagedGridResult getRuleListByCriteria(Integer page, Integer pageSize, String criteria);
}
