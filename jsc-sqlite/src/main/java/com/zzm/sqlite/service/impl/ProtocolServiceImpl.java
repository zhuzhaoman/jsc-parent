package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.Ipv6RuleMsg;
import com.zzm.sqlite.pojo.ProtocolRuleMsg;
import com.zzm.sqlite.pojo.ProtocolRuleMsg;
import com.zzm.sqlite.pojo.vo.ProtocolVO;
import com.zzm.sqlite.pojo.vo.ProtocolVO;
import com.zzm.sqlite.repository.ProtocolRepository;
import com.zzm.sqlite.service.ProtocolService;
import com.zzm.sqlite.utils.Ipv4Utils;
import com.zzm.sqlite.utils.PagedGridResult;
import com.zzm.sqlite.utils.ProtocolUtils;
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
 * @date: 2021-01-04
 * @description:
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProtocolServiceImpl extends BaseService implements ProtocolService {

    private final ProtocolRepository protocolRepository;
    private final ValueObjectTransfer valueObjectTransfer;

    @Override
    public PagedGridResult getRuleList(String username, Integer page, Integer pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);

        Long userId = getUserId(username);

        Specification<ProtocolRuleMsg> specification = new Specification<ProtocolRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<ProtocolRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                Predicate predicate = criteriaBuilder.equal(root.get("userId"), userId);
                list.add(predicate);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };

        Page<ProtocolRuleMsg> ruleMsgPage = protocolRepository.findAll(specification, pageable);

        List<ProtocolVO> protocolVOList = ruleMsgPage.getContent().stream().map(protocolRuleMsg -> {
            return (ProtocolVO) valueObjectTransfer.cast(protocolRuleMsg, ProtocolVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, protocolVOList, page, pageSize);
    }


    @Override
    public PagedGridResult getRuleListByCriteria(String username, Integer page, Integer pageSize, String criteria) {

        JSONObject jsonObject = ProtocolUtils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Long userId = getUserId(username);

        Specification<ProtocolRuleMsg> specification = new Specification<ProtocolRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<ProtocolRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<ProtocolRuleMsg> ruleMsgPage = protocolRepository.findAll(specification, pageable);

        List<ProtocolVO> protocolVOList = ruleMsgPage.getContent().stream().map(protocolRuleMsg -> {
            return (ProtocolVO) valueObjectTransfer.cast(protocolRuleMsg, ProtocolVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, protocolVOList, page, pageSize);
    }
}
