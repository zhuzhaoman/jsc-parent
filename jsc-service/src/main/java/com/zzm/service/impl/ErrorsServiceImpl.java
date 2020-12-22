package com.zzm.service.impl;

import com.zzm.dao.ErrorsRepository;
import com.zzm.enums.DeviceWarningCategoryEnum;
import com.zzm.enums.FlagEnum;
import com.zzm.pojo.Errors;
import com.zzm.pojo.bo.ErrorsPagedBO;
import com.zzm.service.ErrorsService;
import com.zzm.utils.PagedGridResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 异常信息业务层实现类
 */
@Service
@SuppressWarnings("all")
public class ErrorsServiceImpl implements ErrorsService {

    @Resource
    private ErrorsRepository errorsRepository;


    /**
     * 查询设备每个分类异常信息数量
     *
     * @return
     */
    @Override
    public List<Map<String, Integer>> getErrorsCategoryCount() {

        List<Map<String, Integer>> result = new ArrayList<>();

        // 获取异常分类枚举集合
        DeviceWarningCategoryEnum[] values = DeviceWarningCategoryEnum.values();
        for (int i = 0; i < values.length; i++) {
            Map<String, Integer> map = new HashMap<>();

            /**
             * 查询未读信息，并添加到map中
             */
            Integer notLookCount = errorsRepository.countErrorsByCategoryAndFlag(values[i].getCode(), FlagEnum.NOT_LOOK.getCode());
            map.put("notLook", notLookCount);

            /**
             * 查询异常信息，并添加到map中
             */
            Integer count = errorsRepository.countErrorsByCategory(values[i].getCode());
            map.put("count", count);

            result.add(map);
        }

        return result;
    }

    /**
     * 获得异常信息
     *
     * @param errorsPagedBO 分页工具类
     * @return
     */
    @Override
    public PagedGridResult<Errors> getErrorsList(ErrorsPagedBO errorsPagedBO) {

        Specification<Errors> specification = new Specification<Errors>() {

            /**
             *Predicate：封装了单个的查询条件
             * Root<User> root：封装了查询对象的属性
             * CriteriaQuery<?> criteriaQuery：封装了我们要执行的查询中的各个部分的信息，select from order by
             * CriteriaBuilder criteriaBuilder:查询条件的构造器，定义不同的条件查询
             */
            @Override
            public Predicate toPredicate(Root<Errors> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // where name = '...'
                /**
                 * 参数一：查询的条件属性
                 * 参数二：条件的值
                 */
                Predicate predicate = criteriaBuilder.equal(root.get("category"), errorsPagedBO.getCategory());
                return predicate;
            }
        };


        Sort sort = Sort.by(Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(errorsPagedBO.getPage(), errorsPagedBO.getSize(), sort);


        Page<Errors> errorsPage = errorsRepository.findAll(specification, pageable);


        // 获取全部数据条数
        List<Errors> errorsList = errorsPage.getContent();

        /**
         * 封装返回分页数据
         */
        PagedGridResult<Errors> errorsPagedGridResult = new PagedGridResult<>();
        errorsPagedGridResult.setPage(errorsPagedBO.getPage());
        errorsPagedGridResult.setSize(errorsPagedBO.getSize());
        errorsPagedGridResult.setTotal(errorsPage.getTotalElements());
        errorsPagedGridResult.setTotalPage(errorsPage.getTotalPages());
        errorsPagedGridResult.setList(errorsList);

        return errorsPagedGridResult;
    }

    /**
     * 根据时间范围获得异常信息
     *
     * @param errorsPagedBO 分页工具类
     * @return
     */
    @Override
    public PagedGridResult<Errors> getErrorsListByDateRange(ErrorsPagedBO errorsPagedBO) {

        Specification<Errors> specification = new Specification<Errors>() {

            /**
             *Predicate：封装了单个的查询条件
             * Root<User> root：封装了查询对象的属性
             * CriteriaQuery<?> criteriaQuery：封装了我们要执行的查询中的各个部分的信息，select from order by
             * CriteriaBuilder criteriaBuilder:查询条件的构造器，定义不同的条件查询
             */
            @Override
            public Predicate toPredicate(Root<Errors> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                /**
                 * 参数一：查询的条件属性
                 * 参数二：条件的值
                 */
                Predicate predicate1 = criteriaBuilder.equal(root.get("category"), errorsPagedBO.getCategory());
                Predicate predicate2 = criteriaBuilder.between(root.get("createTime"), errorsPagedBO.getStartTime(), errorsPagedBO.getEndTime());

                list.add(predicate1);
                list.add(predicate2);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };


        Sort sort = Sort.by(Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(errorsPagedBO.getPage(), errorsPagedBO.getSize(), sort);


        Page<Errors> errorsPage = errorsRepository.findAll(specification, pageable);

        // 获取全部数据条数
        List<Errors> errorsList = errorsPage.getContent();

        /**
         * 封装返回分页数据
         */
        PagedGridResult<Errors> errorsPagedGridResult = new PagedGridResult<>();
        errorsPagedGridResult.setPage(errorsPagedBO.getPage());
        errorsPagedGridResult.setSize(errorsPagedBO.getSize());
        errorsPagedGridResult.setTotal(errorsPage.getTotalElements());
        errorsPagedGridResult.setTotalPage(errorsPage.getTotalPages());
        errorsPagedGridResult.setList(errorsList);

        return errorsPagedGridResult;
    }

    /**
     * 将未读异常信息全部改为已读信息
     *
     * @param category 异常分类
     */
    @Transactional
    @Override
    public void readErrors(Integer category) {
        errorsRepository.updateErrorsFlag(category);
    }

    /**
     * 根据分类清除异常信息
     *
     * @param category 异常分类
     */
    @Override
    public void cleanErrorsByCategory(Integer category) {
        errorsRepository.deleteAllByCategory(category);
    }


    /**
     * 保存异常信息
     *
     * @param errors 实体类
     * @return
     */
    @Transactional
    @Override
    public Errors saveErrors(Errors errors) {
        Errors result = errorsRepository.save(errors);
        return result;
    }


}
