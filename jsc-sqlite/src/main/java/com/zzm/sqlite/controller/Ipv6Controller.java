package com.zzm.sqlite.controller;

//import com.zzm.sqlite.repository.UserRepository;

import com.zzm.sqlite.pojo.vo.Ipv4VO;
import com.zzm.sqlite.pojo.vo.Ipv6VO;
import com.zzm.sqlite.service.Ipv4Service;
import com.zzm.sqlite.service.Ipv6Service;
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
@RequestMapping("/ipv6")
public class Ipv6Controller {

    @Resource
    private Ipv6Service ipv6Service;


    @GetMapping("/getRuleList")
    public PagedGridResult getRuleList(@RequestParam Integer page,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String username) {

        return ipv6Service.getRuleList(username, page, pageSize);
    }

    @GetMapping("/getRuleListByCriteria")
    public PagedGridResult getRuleListByCriteria(@RequestParam Integer page,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam String criteria,
                                                 @RequestParam String username) {

        return ipv6Service.getRuleListByCriteria(username, page, pageSize, criteria);
    }

}
