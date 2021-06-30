package com.zzm.service.impl;

import com.zzm.dao.TotalHistoryFlowRepository;
import com.zzm.pojo.TotalHistoryFlow;
import com.zzm.pojo.bo.TotalHistoryFlowBO;
import com.zzm.service.TotalHistoryFlowService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author：zhuzhaoman
 * @date：2020/8/25
 * @description：历史流量查询业务层实现
 */
@SuppressWarnings("all")
@Service
public class TotalHistoryFlowServiceImpl implements TotalHistoryFlowService {

    @Resource
    private TotalHistoryFlowRepository totalHistoryFlowRepository;


    /**
     * 根据天查询流量信息，范围0<x<=1，精度1分钟
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @Override
    public List<TotalHistoryFlow> getTotalHistoryFlowByDay(TotalHistoryFlowBO totalHistoryFlowBO) {

        Specification<TotalHistoryFlow> specification = new Specification<TotalHistoryFlow>() {

            /**
             *Predicate：封装了单个的查询条件
             * Root<User> root：封装了查询对象的属性
             * CriteriaQuery<?> criteriaQuery：封装了我们要执行的查询中的各个部分的信息，select from order by
             * CriteriaBuilder criteriaBuilder:查询条件的构造器，定义不同的条件查询
             */
            @Override
            public Predicate toPredicate(Root<TotalHistoryFlow> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {


                List<Predicate> list = new ArrayList<>();

                /**
                 * 参数一：查询的条件属性
                 * 参数二：条件的值
                 */
                Predicate predicate1 = criteriaBuilder.between(root.get("createTime"), totalHistoryFlowBO.getStartTime(), totalHistoryFlowBO.getEndTime());
                Predicate predicate2 = criteriaBuilder.equal(root.get("domainType"), totalHistoryFlowBO.getDomainType());
                Predicate predicate3 = criteriaBuilder.equal(root.get("domainId"), totalHistoryFlowBO.getDomainId());

                list.add(predicate1);
                list.add(predicate2);
                list.add(predicate3);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };


        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<TotalHistoryFlow> totalHistoryFlowList = totalHistoryFlowRepository.findAll(specification, sort);

        return totalHistoryFlowList;
    }

    /**
     * 根据日期范围查询流量，范围1<x<30，精度5分钟
     * @param totalHistoryFlowBO
     * @return
     */
    @Override
    public List<TotalHistoryFlow> getTotalHistoryFlowByDayBetweenMonth(TotalHistoryFlowBO totalHistoryFlowBO) {
        List<TotalHistoryFlow> totalHistoryFlowList = totalHistoryFlowRepository.getTotalHistoryFlowByDayBetweenMonth(totalHistoryFlowBO);
        return totalHistoryFlowList;
    }

    /**
     * 根据一个月内时间查询流量信息，范围x=30，精度30分钟
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @Override
    public List<TotalHistoryFlow> getTotalHistoryFlowByMonth(TotalHistoryFlowBO totalHistoryFlowBO) {
        List<TotalHistoryFlow> totalHistoryFlowList = totalHistoryFlowRepository.getTotalHistoryFlowByMonthRange(totalHistoryFlowBO);
        return totalHistoryFlowList;
    }

    /**
     * 根据一个季度内时间查询流量信息，范围x>30，精度60分钟
     *
     * @param totalHistoryFlowBO 参数
     * @return
     */
    @Override
    public List<TotalHistoryFlow> getTotalHistoryFlowByQuarterRange(TotalHistoryFlowBO totalHistoryFlowBO) {
        List<TotalHistoryFlow> totalHistoryFlowList = totalHistoryFlowRepository.getTotalHistoryFlowByQuarterRange(totalHistoryFlowBO);

        return totalHistoryFlowList;
    }


    /**
     * 删除小于某个时间点的数据
     *
     * @param minTime 时间
     */
    @Override
    public void deleteTotalHistoryFlowByCreateTimeRange(Date minTime) {
        totalHistoryFlowRepository.deleteTotalHistoryFlowByCreateTimeRange(minTime);
    }

    private Map<String, Float> criticalFlow(List<TotalHistoryFlow> totalHistoryFlowList) {

        Map<String, Float> result = new HashMap<>();

        int total = totalHistoryFlowList.size();
        float rxAvg = new BigDecimal(totalHistoryFlowList.stream().mapToDouble(TotalHistoryFlow::getRx).sum() / total)
                .setScale(2, BigDecimal.ROUND_UP).floatValue();
        float txAvg = new BigDecimal(totalHistoryFlowList.stream().mapToDouble(TotalHistoryFlow::getTx).sum() / total)
                .setScale(2, BigDecimal.ROUND_UP).floatValue();

        float rxMax = totalHistoryFlowList.stream()
                .max((flow1, flow2) -> (flow1.getRx() > flow2.getRx()) ? 1 : -1).get().getRx();
        float txMax = totalHistoryFlowList.stream()
                .max((flow1, flow2) -> (flow1.getTx() > flow2.getTx()) ? 1 : -1).get().getTx();

        float rxMin = totalHistoryFlowList.stream()
                .max((flow1, flow2) -> (flow1.getRx() < flow2.getRx()) ? 1 : -1).get().getRx();
        float txMin = totalHistoryFlowList.stream()
                .max((flow1, flow2) -> (flow1.getTx() < flow2.getTx()) ? 1 : -1).get().getTx();

        System.out.println("rxAvg:" + rxAvg);
        System.out.println("txAvg:" + txAvg);
        result.put("rxAvg", rxAvg);
        result.put("txAvg", txAvg);

        System.out.println("rxMax:" + rxMax);
        System.out.println("txMax:" + txMax);
        result.put("rxMax", rxMax);
        result.put("txMax", txMax);

        System.out.println("rxMin:" + rxMin);
        System.out.println("txMin:" + rxMin);
        result.put("rxMin", rxMin);
        result.put("txMin", txMin);

        return result;
    }

}
