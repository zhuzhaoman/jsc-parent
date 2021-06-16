package com.zzm.sqlite.service;

import com.zzm.sqlite.pojo.vo.FullCharVO;
import com.zzm.sqlite.utils.PagedGridResult;

import java.util.List;

public interface FullCharService {

    PagedGridResult getRuleList(String username, Integer page, Integer pageSize);

    PagedGridResult getRuleListByCriteria(String username, Integer page, Integer pageSize, String criteria);
}
