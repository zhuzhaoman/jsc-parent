package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.FullcharRuleMsg;
import com.zzm.sqlite.pojo.TcpflagRuleMsg;
import com.zzm.sqlite.pojo.UrlRuleMsg;
import com.zzm.sqlite.pojo.vo.FullCharVO;
import com.zzm.sqlite.pojo.vo.TcpFlagVO;
import com.zzm.sqlite.pojo.vo.UrlVO;
import com.zzm.sqlite.repository.FullCharRepository;
import com.zzm.sqlite.repository.TcpFlagRepository;
import com.zzm.sqlite.service.TcpFlagService;
import com.zzm.sqlite.utils.PagedGridResult;
import com.zzm.sqlite.utils.TcpFlagUtils;
import com.zzm.sqlite.utils.UrlUtils;
import com.zzm.sqlite.utils.ValueObjectTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
 * @date: 2021-01-04
 * @description:
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TcpFlagServiceImpl extends BaseService implements TcpFlagService {

    private final TcpFlagRepository tcpFlagRepository;
    private final ValueObjectTransfer valueObjectTransfer;

    @Override
    public PagedGridResult getRuleList(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<TcpflagRuleMsg> ruleMsgPage = tcpFlagRepository.findAll(pageable);

        List<TcpFlagVO> urlVOList = ruleMsgPage.getContent().stream().map(urlRuleMsg -> {
            return (TcpFlagVO) valueObjectTransfer.cast(urlRuleMsg, TcpFlagVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, urlVOList, page, pageSize);
    }

    @Override
    public PagedGridResult getRuleListByCriteria(Integer page, Integer pageSize, String criteria) {
        JSONObject jsonObject = TcpFlagUtils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Specification<TcpflagRuleMsg> specification = new Specification<TcpflagRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<TcpflagRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                keys.forEach(s -> {
                    Predicate predicate = criteriaBuilder.equal(root.get(s), jsonObject.get(s));
                    list.add(predicate);
                });

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<TcpflagRuleMsg> ruleMsgPage = tcpFlagRepository.findAll(specification, pageable);

        List<TcpFlagVO> tcpFlagVOList = ruleMsgPage.getContent().stream().map(urlRuleMsg -> {
            return (TcpFlagVO) valueObjectTransfer.cast(urlRuleMsg, TcpFlagVO.class);
        }).collect(Collectors.toList());


        return setPagedGrid(ruleMsgPage, tcpFlagVOList, page, pageSize);
    }
}
