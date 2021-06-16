package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.ImsiRuleMsg;
import com.zzm.sqlite.pojo.Ipv4RuleMsg;
import com.zzm.sqlite.pojo.vo.Ipv4VO;
import com.zzm.sqlite.repository.Ipv4Repository;
import com.zzm.sqlite.service.Ipv4Service;
import com.zzm.sqlite.utils.Ipv4Utils;
import com.zzm.sqlite.utils.PagedGridResult;
import com.zzm.sqlite.utils.ValueObjectTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-28
 * @description:
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Ipv4ServiceImpl extends BaseService implements Ipv4Service {

    private final Ipv4Repository ipv4Repository;
    private final ValueObjectTransfer valueObjectTransfer;

    @Override
    public PagedGridResult getRuleList(String username, Integer page, Integer pageSize) {

        Sort sort = Sort.by(Sort.Direction.DESC, "setTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Long userId = getUserId(username);

        Specification<Ipv4RuleMsg> specification = new Specification<Ipv4RuleMsg>() {
            @Override
            public Predicate toPredicate(Root<Ipv4RuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                Predicate predicate = criteriaBuilder.equal(root.get("userId"), userId);
                list.add(predicate);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };

        Page<Ipv4RuleMsg> ruleMsgPage = ipv4Repository.findAll(specification, pageable);

        List<Ipv4VO> ipv4VOList = ruleMsgPage.getContent().stream().map(ipv4RuleMsg -> {
            return (Ipv4VO) valueObjectTransfer.cast(ipv4RuleMsg, Ipv4VO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, ipv4VOList, page, pageSize);
    }


    @Override
    public PagedGridResult getRuleListByCriteria(String username, Integer page, Integer pageSize, String criteria) {

        JSONObject jsonObject = Ipv4Utils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Long userId = getUserId(username);

        Specification<Ipv4RuleMsg> specification = new Specification<Ipv4RuleMsg>() {
            @Override
            public Predicate toPredicate(Root<Ipv4RuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                keys.forEach(s -> {
                    Predicate predicate = criteriaBuilder.equal(root.get(s), jsonObject.get(s));
                    list.add(predicate);
                });

                Predicate predicate = criteriaBuilder.equal(root.get("userId"), userId);
                list.add(predicate);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };

        Sort sort = Sort.by(Sort.Direction.DESC, "setTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<Ipv4RuleMsg> ruleMsgPage = ipv4Repository.findAll(specification, pageable);

        List<Ipv4VO> ipv4VOList = ruleMsgPage.getContent().stream().map(ipv4RuleMsg -> {
            return (Ipv4VO) valueObjectTransfer.cast(ipv4RuleMsg, Ipv4VO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, ipv4VOList, page, pageSize);
    }
}
