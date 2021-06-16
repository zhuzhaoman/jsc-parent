package com.zzm.sqlite.controller;

//import com.zzm.sqlite.repository.UserRepository;

import com.zzm.sqlite.pojo.vo.TcpFlagVO;
import com.zzm.sqlite.pojo.vo.UrlVO;
import com.zzm.sqlite.service.TcpFlagService;
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
@RequestMapping("/tcp-flag")
public class TcpFlagController {

    @Resource
    private TcpFlagService tcpFlagService;

    @GetMapping("/getRuleList")
    public PagedGridResult getRuleList(@RequestParam Integer page,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String username) {

        return tcpFlagService.getRuleList(username, page, pageSize);
    }

    @GetMapping("/getRuleListByCriteria")
    public PagedGridResult getRuleListByCriteria(@RequestParam Integer page,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam String criteria,
                                                 @RequestParam String username) {

        return tcpFlagService.getRuleListByCriteria(username, page, pageSize, criteria);
    }


}
