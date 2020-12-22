package com.zzm.service;

import com.zzm.pojo.Errors;
import com.zzm.pojo.bo.ErrorsPagedBO;
import com.zzm.utils.PagedGridResult;

import java.util.List;
import java.util.Map;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 异异常信息业务层接口
 */
public interface ErrorsService {

    /**
     * 保存异常信息
     *
     * @param errors
     * @return 异常信息domain
     */
    Errors saveErrors(Errors errors);

    /**
     * 获得异常信息（分页）
     *
     * @param errorsPagedBO 分页工具类
     * @return
     */
    PagedGridResult<Errors> getErrorsList(ErrorsPagedBO errorsPagedBO);

    /**
     * 根据时间范围获得异常信息（分页）
     *
     * @param errorsPagedBO 分页工具类
     * @return
     */
    PagedGridResult<Errors> getErrorsListByDateRange(ErrorsPagedBO errorsPagedBO);

    /**
     * 查询设备每个分类异常信息数量
     *
     * @return
     */
    List<Map<String, Integer>> getErrorsCategoryCount();

    /**
     * 将未读异常信息信息全部改为已读信息
     *
     * @param category 异常分类
     */
    void readErrors(Integer category);

    /**
     * 根据分类清除异常信息
     *
     * @param category 异常分类
     */
    void cleanErrorsByCategory(Integer category);
}
