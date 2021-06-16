package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.FullcharRuleMsg;
import com.zzm.sqlite.pojo.ImsiRuleMsg;
import com.zzm.sqlite.pojo.ImsiRuleMsg;
import com.zzm.sqlite.pojo.VlanRuleMsg;
import com.zzm.sqlite.pojo.vo.ImsiVO;
import com.zzm.sqlite.pojo.vo.ImsiVO;
import com.zzm.sqlite.pojo.vo.VlanVO;
import com.zzm.sqlite.repository.ImsiRepository;
import com.zzm.sqlite.repository.VlanRepository;
import com.zzm.sqlite.service.ImsiService;
import com.zzm.sqlite.service.VlanService;
import com.zzm.sqlite.utils.ImsiUtils;
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
public class ImsiServiceImpl extends BaseService implements ImsiService {

    private final ImsiRepository imsiRepository;
    private final ValueObjectTransfer valueObjectTransfer;

    @Override
    public PagedGridResult getRuleList(String username, Integer page, Integer pageSize) {

        Sort sort = Sort.by(Sort.Direction.DESC, "setTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Long userId = getUserId(username);

        Specification<ImsiRuleMsg> specification = new Specification<ImsiRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<ImsiRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                Predicate predicate = criteriaBuilder.equal(root.get("userId"), userId);
                list.add(predicate);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };

        Page<ImsiRuleMsg> ruleMsgPage = imsiRepository.findAll(specification, pageable);

        List<ImsiVO> imsiVOList = ruleMsgPage.getContent().stream().map(imsiRuleMsg -> {
            return (ImsiVO) valueObjectTransfer.cast(imsiRuleMsg, ImsiVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, imsiVOList, page, pageSize);
    }


    @Override
    public PagedGridResult getRuleListByCriteria(String username, Integer page, Integer pageSize, String criteria) {

        JSONObject jsonObject = ImsiUtils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Long userId = getUserId(username);

        Specification<ImsiRuleMsg> specification = new Specification<ImsiRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<ImsiRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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

        Page<ImsiRuleMsg> ruleMsgPage = imsiRepository.findAll(specification, pageable);

        List<ImsiVO> imsiVOList = ruleMsgPage.getContent().stream().map(imsiRuleMsg -> {
            return (ImsiVO) valueObjectTransfer.cast(imsiRuleMsg, ImsiVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, imsiVOList, page, pageSize);
    }
    
}
