package com.zzm.sqlite.service.impl;

import com.zzm.sqlite.pojo.Ipv4RuleMsg;
import com.zzm.sqlite.pojo.vo.Ipv4VO;
import com.zzm.sqlite.utils.PagedGridResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-01-11
 * @description:
 **/
public class BaseService {

    public PagedGridResult setPagedGrid(Page<?> ruleMsgPage, List<?> list, Integer page, Integer pageSize) {
        /**
         * 封装返回分页数据
         */
        PagedGridResult<?> pagedGridResult = new PagedGridResult<>();
        pagedGridResult.setPage(page);
        pagedGridResult.setSize(pageSize);
        pagedGridResult.setTotal(ruleMsgPage.getTotalElements());
        pagedGridResult.setTotalPage(ruleMsgPage.getTotalPages());
        pagedGridResult.setList(list);

        return pagedGridResult;
    }
}
