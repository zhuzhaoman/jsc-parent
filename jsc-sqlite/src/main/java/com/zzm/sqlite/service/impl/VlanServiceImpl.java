package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.VlanRuleMsg;
import com.zzm.sqlite.pojo.VlanRuleMsg;
import com.zzm.sqlite.pojo.vo.VlanVO;
import com.zzm.sqlite.pojo.vo.Ipv6VO;
import com.zzm.sqlite.pojo.vo.VlanVO;
import com.zzm.sqlite.repository.VlanRepository;
import com.zzm.sqlite.service.VlanService;
import com.zzm.sqlite.utils.Ipv4Utils;
import com.zzm.sqlite.utils.PagedGridResult;
import com.zzm.sqlite.utils.ValueObjectTransfer;
import com.zzm.sqlite.utils.VlanUtils;
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
public class VlanServiceImpl extends BaseService implements VlanService {

    private final VlanRepository vlanRepository;
    private final ValueObjectTransfer valueObjectTransfer;


    @Override
    public PagedGridResult getRuleList(Integer page, Integer pageSize) {

        Sort sort = Sort.by(Sort.Direction.DESC, "setTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<VlanRuleMsg> ruleMsgPage = vlanRepository.findAll(pageable);

        List<VlanVO> vlanVOList = ruleMsgPage.getContent().stream().map(vlanRuleMsg -> {
            return (VlanVO) valueObjectTransfer.cast(vlanRuleMsg, VlanVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, vlanVOList, page, pageSize);
    }


    @Override
    public PagedGridResult getRuleListByCriteria(Integer page, Integer pageSize, String criteria) {

        JSONObject jsonObject = VlanUtils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Specification<VlanRuleMsg> specification = new Specification<VlanRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<VlanRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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

        Page<VlanRuleMsg> ruleMsgPage = vlanRepository.findAll(specification, pageable);

        List<VlanVO> vlanVOList = ruleMsgPage.getContent().stream().map(vlanRuleMsg -> {
            return (VlanVO) valueObjectTransfer.cast(vlanRuleMsg, VlanVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, vlanVOList, page, pageSize);
    }
}
