package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.EtherMacRuleMsg;
import com.zzm.sqlite.pojo.EtherMacRuleMsg;
import com.zzm.sqlite.pojo.vo.EthMacVO;
import com.zzm.sqlite.pojo.vo.EthMacVO;
import com.zzm.sqlite.repository.EthMacRepository;
import com.zzm.sqlite.service.EthMacService;
import com.zzm.sqlite.utils.EthMacUtils;
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
 * @date: 2020-12-31
 * @description:
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EthMacServiceImpl extends BaseService implements EthMacService {

    private final EthMacRepository ethMacRepository;
    private final ValueObjectTransfer valueObjectTransfer;

    @Override
    public PagedGridResult getRuleList(Integer page, Integer pageSize) {

        Sort sort = Sort.by(Sort.Direction.DESC, "setTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<EtherMacRuleMsg> ruleMsgPage = ethMacRepository.findAll(pageable);

        List<EthMacVO> ethMacVOList = ruleMsgPage.getContent().stream().map(ethMacVO -> {
            return (EthMacVO) valueObjectTransfer.cast(ethMacVO, EthMacVO.class);
        }).collect(Collectors.toList());


        return setPagedGrid(ruleMsgPage, ethMacVOList, page, pageSize);
    }


    @Override
    public PagedGridResult getRuleListByCriteria(Integer page, Integer pageSize, String criteria) {

        JSONObject jsonObject = EthMacUtils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Specification<EtherMacRuleMsg> specification = new Specification<EtherMacRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<EtherMacRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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

        Page<EtherMacRuleMsg> ruleMsgPage = ethMacRepository.findAll(specification, pageable);

        List<EthMacVO> ethMacVOList = ruleMsgPage.getContent().stream().map(ethMacVO -> {
            return (EthMacVO) valueObjectTransfer.cast(ethMacVO, EthMacVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, ethMacVOList, page, pageSize);
    }
}
