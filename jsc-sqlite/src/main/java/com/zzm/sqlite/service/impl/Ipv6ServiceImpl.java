package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.Ipv4RuleMsg;
import com.zzm.sqlite.pojo.Ipv6RuleMsg;
import com.zzm.sqlite.pojo.vo.Ipv4VO;
import com.zzm.sqlite.pojo.vo.Ipv6VO;
import com.zzm.sqlite.repository.Ipv6Repository;
import com.zzm.sqlite.service.Ipv6Service;
import com.zzm.sqlite.utils.Ipv4Utils;
import com.zzm.sqlite.utils.Ipv6Utils;
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
public class Ipv6ServiceImpl extends BaseService implements Ipv6Service {

    private final Ipv6Repository ipv6Repository;
    private final ValueObjectTransfer valueObjectTransfer;


    @Override
    public PagedGridResult getRuleList(Integer page, Integer pageSize) {

        Sort sort = Sort.by(Sort.Direction.DESC, "setTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<Ipv6RuleMsg> ruleMsgPage = ipv6Repository.findAll(pageable);

        List<Ipv6VO> ipv6VOList = ruleMsgPage.getContent().stream().map(ipv6RuleMsg -> {
            return (Ipv6VO) valueObjectTransfer.cast(ipv6RuleMsg, Ipv6VO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, ipv6VOList, page, pageSize);
    }


    @Override
    public PagedGridResult getRuleListByCriteria(Integer page, Integer pageSize, String criteria) {

        JSONObject jsonObject = Ipv6Utils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Specification<Ipv6RuleMsg> specification = new Specification<Ipv6RuleMsg>() {
            @Override
            public Predicate toPredicate(Root<Ipv6RuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                keys.forEach(s -> {
                    Predicate predicate = criteriaBuilder.equal(root.get(s), jsonObject.get(s));
                    list.add(predicate);
                });

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };

        Sort sort = Sort.by(Sort.Direction.DESC, "setTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<Ipv6RuleMsg> ruleMsgPage = ipv6Repository.findAll(specification, pageable);

        List<Ipv6VO> ipv6VOList = ruleMsgPage.getContent().stream().map(ipv6RuleMsg -> {
            return (Ipv6VO) valueObjectTransfer.cast(ipv6RuleMsg, Ipv6VO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, ipv6VOList, page, pageSize);
    }

}
