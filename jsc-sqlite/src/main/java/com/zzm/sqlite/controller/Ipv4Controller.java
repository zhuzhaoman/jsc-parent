package com.zzm.sqlite.controller;

//import com.zzm.sqlite.repository.UserRepository;

import com.zzm.sqlite.service.Ipv4Service;
import com.zzm.sqlite.utils.PagedGridResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@RestController
@RequestMapping("/ipv4")
public class Ipv4Controller {

    @Resource
    private Ipv4Service ipv4Service;

    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/getRuleList")
    public PagedGridResult getRuleList(@RequestParam Integer page,
                                       @RequestParam Integer pageSize) {

        PagedGridResult pagedGridResult = ipv4Service.getRuleList(page, pageSize);

        return pagedGridResult;
    }

    @GetMapping("/getRuleListByCriteria")
    public PagedGridResult getRuleListByCriteria(@RequestParam Integer page,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam String criteria) {

        PagedGridResult pagedGridResult = ipv4Service.getRuleListByCriteria(page, pageSize, criteria);

        return pagedGridResult;
    }


}
