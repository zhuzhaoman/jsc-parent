package com.zzm.sqlite.service.impl;

import com.zzm.sqlite.enums.UserIdEnum;
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

    public Long getUserId(String username) {

        UserIdEnum userIdEnum = UserIdEnum.formValue(username);

        switch (userIdEnum) {
            case ADMIN: return UserIdEnum.ADMIN.getCode();
            case USER1: return UserIdEnum.USER1.getCode();
            case USER2: return UserIdEnum.USER2.getCode();
            case USER3: return UserIdEnum.USER3.getCode();
            case USER4: return UserIdEnum.USER4.getCode();
        }

        return 0L;
    }
}
