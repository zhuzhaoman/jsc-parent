package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.exception.GraceException;
import com.zzm.pojo.Errors;
import com.zzm.pojo.bo.ErrorsPagedBO;
import com.zzm.service.ErrorsService;
import com.zzm.utils.JSONResult;
import com.zzm.utils.PagedGridResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 异常信息查询
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/errors")
public class ErrorMessageController {

    public static final Logger log = LoggerFactory.getLogger(ErrorMessageController.class);

    @Resource
    private ErrorsService errorsService;

    /**
     * 获取每个异常分类异常数
     *
     * @return
     */
    @GetMapping("/getErrorCategoryCount")
    @SystemLog(description = "获取异常信息记录数")
    public JSONResult getErrorCategoryCount() {

        List<Map<String, Integer>> errors = null;

        try {
            errors = errorsService.getErrorsCategoryCount();
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("获取异常信息数失败");
        }
        return JSONResult.ok(errors);
    }

    /**
     * 获取指定异常分类集合（分页）
     *
     * @param errorsPagedBO
     * @return
     */
    @PostMapping("/list")
    @SystemLog(description = "分页查询异常信息")
    public JSONResult getErrorsList(@RequestBody ErrorsPagedBO errorsPagedBO) {

        PagedGridResult<Errors> pagedGridResult = null;

        /**
         * 判断用户根据日期查询还是直接查询
         */
        try {
            if (errorsPagedBO.getStartTime() == null
                    || errorsPagedBO.getEndTime() == null) {
                pagedGridResult = errorsService.getErrorsList(errorsPagedBO);
                List<Errors> list = pagedGridResult.getList();
            } else {
                pagedGridResult = errorsService.getErrorsListByDateRange(errorsPagedBO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("查询数据失败");
        }

        return JSONResult.ok(pagedGridResult);
    }

    /**
     * 清除全部异常信息
     *
     * @param category 异常分类
     * @return
     */
    @GetMapping("/clean")
    @SystemLog(description = "清除异常信息")
    public JSONResult cleanErrorsByCategory(Integer category) {

        try {
            errorsService.cleanErrorsByCategory(category);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            GraceException.display("清除异常信息失败");
        }

        return JSONResult.ok();
    }


    /**
     * 查看未读异常信息
     *
     * @param category 异常分类
     * @return
     */
    @GetMapping("/readError")
    @SystemLog(description = "查看异常信息")
    public JSONResult readError(Integer category) {

        try {
            errorsService.readErrors(category);
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("改变异常信息状态失败");
        }
        return JSONResult.ok();
    }

}
