package com.zzm.sqlite.controller;

//import com.zzm.sqlite.repository.UserRepository;

import com.zzm.sqlite.pojo.vo.FixCharVO;
import com.zzm.sqlite.pojo.vo.Ipv4VO;
import com.zzm.sqlite.service.FixCharService;
import com.zzm.sqlite.service.Ipv4Service;
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
@RequestMapping("/fix-char")
public class FixCharController {

    @Resource
    private FixCharService fixCharService;

    @GetMapping("/getRuleList")
    public PagedGridResult getRuleList(@RequestParam Integer page,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String username) {

        return fixCharService.getRuleList(username, page, pageSize);
    }

    @GetMapping("/getRuleListByCriteria")
    public PagedGridResult getRuleListByCriteria(@RequestParam Integer page,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam String criteria,
                                                 @RequestParam String username) {

        return fixCharService.getRuleListByCriteria(username, page, pageSize, criteria);
    }


}
