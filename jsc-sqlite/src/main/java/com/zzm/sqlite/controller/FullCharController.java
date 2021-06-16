package com.zzm.sqlite.controller;

//import com.zzm.sqlite.repository.UserRepository;

import com.zzm.sqlite.pojo.vo.FullCharVO;
import com.zzm.sqlite.service.FullCharService;
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
@RequestMapping("/full-char")
public class FullCharController {

    @Resource
    private FullCharService fullCharService;

    @GetMapping("/getRuleList")
    public PagedGridResult getRuleList(@RequestParam Integer page,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String username) {

        return fullCharService.getRuleList(username, page, pageSize);
    }

    @GetMapping("/getRuleListByCriteria")
    public PagedGridResult getRuleListByCriteria(@RequestParam Integer page,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam String criteria,
                                                 @RequestParam String username) {

        return fullCharService.getRuleListByCriteria(username, page, pageSize, criteria);
    }


}
