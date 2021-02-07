package com.zzm.sqlite.controller;

import com.zzm.sqlite.pojo.vo.EthMacVO;
import com.zzm.sqlite.pojo.vo.VlanVO;
import com.zzm.sqlite.service.EthMacService;
import com.zzm.sqlite.service.VlanService;
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
@RequestMapping("/eth-mac")
public class EthMacController {

    @Resource
    private EthMacService ethMacService;

    @GetMapping("/getRuleList")
    public PagedGridResult getRuleList(@RequestParam Integer page,
                                       @RequestParam Integer pageSize) {

        PagedGridResult pagedGridResult = ethMacService.getRuleList(page, pageSize);

        return pagedGridResult;
    }

    @GetMapping("/getRuleListByCriteria")
    public PagedGridResult getRuleListByCriteria(@RequestParam Integer page,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam String criteria) {

        PagedGridResult pagedGridResult = ethMacService.getRuleListByCriteria(page, pageSize, criteria);

        return pagedGridResult;
    }


}
