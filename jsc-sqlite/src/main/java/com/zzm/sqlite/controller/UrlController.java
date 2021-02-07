package com.zzm.sqlite.controller;


import com.zzm.sqlite.pojo.vo.UrlVO;
import com.zzm.sqlite.service.UrlService;
import com.zzm.sqlite.utils.PagedGridResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2020-11-19
 * @description:
 **/
@RestController
@RequestMapping("/url")
public class UrlController {

    @Resource
    private UrlService urlService;

    @GetMapping("/getRuleList")
    public PagedGridResult getRuleList(@RequestParam Integer page,
                                       @RequestParam Integer pageSize) {

        PagedGridResult pagedGridResult = urlService.getRuleList(page, pageSize);

        return pagedGridResult;
    }

    @GetMapping("/getRuleListByCriteria")
    public PagedGridResult getRuleListByCriteria(@RequestParam Integer page,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam String criteria) {

        PagedGridResult pagedGridResult = urlService.getRuleListByCriteria(page, pageSize, criteria);

        return pagedGridResult;
    }


}
