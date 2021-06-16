package com.zzm.sqlite.controller;

import com.zzm.sqlite.pojo.vo.WindowVO;
import com.zzm.sqlite.service.WindowService;
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
@RequestMapping("/window")
public class WindowController {

    @Resource
    private WindowService windowService;

    @GetMapping("/getRuleList")
    public PagedGridResult getRuleList(@RequestParam Integer page,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String username) {

        return windowService.getRuleList(username, page, pageSize);
    }

    @GetMapping("/getRuleListByCriteria")
    public PagedGridResult getRuleListByCriteria(@RequestParam Integer page,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam String criteria,
                                                 @RequestParam String username) {

        return windowService.getRuleListByCriteria(username, page, pageSize, criteria);
    }

}
