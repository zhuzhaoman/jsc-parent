package com.zzm.service.impl;

import com.zzm.dao.PortHistoryFlowRepository;
import com.zzm.pojo.PortHistoryFlow;
import com.zzm.pojo.bo.PortHistoryFlowBO;
import com.zzm.service.PortHistoryFlowService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 异常信息业务层实现类
 */
@Service
@SuppressWarnings("all")
public class PortHistoryFlowServiceImpl implements PortHistoryFlowService {

    @Resource
    private PortHistoryFlowRepository portHistoryFlowRepository;


    /**
     * 根据天查询流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @Override
    public List<PortHistoryFlow> getPortHistoryFlowByDay(PortHistoryFlowBO portHistoryFlowBO) {

        Specification<PortHistoryFlow> specification = new Specification<PortHistoryFlow>() {

            /**
             *Predicate：封装了单个的查询条件
             * Root<User> root：封装了查询对象的属性
             * CriteriaQuery<?> criteriaQuery：封装了我们要执行的查询中的各个部分的信息，select from order by
             * CriteriaBuilder criteriaBuilder:查询条件的构造器，定义不同的条件查询
             */
            @Override
            public Predicate toPredicate(Root<PortHistoryFlow> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {


                List<Predicate> list = new ArrayList<>();

                /**
                 * 参数一：查询的条件属性
                 * 参数二：条件的值
                 */
                Predicate predicate1 = criteriaBuilder.between(root.get("createTime"), portHistoryFlowBO.getStartTime(), portHistoryFlowBO.getEndTime());
                Predicate predicate2 = criteriaBuilder.equal(root.get("domainType"), portHistoryFlowBO.getDomainType());
                Predicate predicate3 = criteriaBuilder.equal(root.get("domainId"), portHistoryFlowBO.getDomainId());
                Predicate predicate4 = criteriaBuilder.equal(root.get("portName"), portHistoryFlowBO.getPortName());

                list.add(predicate1);
                list.add(predicate2);
                list.add(predicate3);
                list.add(predicate4);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };


        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");

        List<PortHistoryFlow> portHistoryFlowRepositoryAll = portHistoryFlowRepository.findAll(specification, sort);

        return portHistoryFlowRepositoryAll;
    }

    /**
     * 根据日期范围查询流量，精度5分钟
     * @param portHistoryFlowBO
     * @return
     */
    @Override
    public List<PortHistoryFlow> getPortHistoryFlowByDayBetweenMonth(PortHistoryFlowBO portHistoryFlowBO) {
        return portHistoryFlowRepository.getPortHistoryFlowByDayBetweenMonth(portHistoryFlowBO);
    }

    /**
     * 根据一个月内时间查询流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @Override
    public List<PortHistoryFlow> getPortHistoryFlowByMonth(PortHistoryFlowBO portHistoryFlowBO) {
        List<PortHistoryFlow> portHistoryFlowList = portHistoryFlowRepository.getPortHistoryFlowByMonthRange(portHistoryFlowBO);
        return portHistoryFlowList;
    }

    /**
     * 根据一个季度内时间查询流量信息
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @Override
    public List<PortHistoryFlow> getPortHistoryFlowByQuarterRange(PortHistoryFlowBO portHistoryFlowBO) {
        List<PortHistoryFlow> portHistoryFlowList = portHistoryFlowRepository.getPortHistoryFlowByQuarterRange(portHistoryFlowBO);
        return portHistoryFlowList;
    }

    @Override
    public void deletePortHistoryFlowByCreateTimeRange(Date minTime) {

    }
}
