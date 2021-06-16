package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.FullcharRuleMsg;
import com.zzm.sqlite.pojo.TcpflagRuleMsg;
import com.zzm.sqlite.pojo.UrlRuleMsg;
import com.zzm.sqlite.pojo.UrlRuleMsg;
import com.zzm.sqlite.pojo.vo.FullCharVO;
import com.zzm.sqlite.pojo.vo.UrlVO;
import com.zzm.sqlite.pojo.vo.UrlVO;
import com.zzm.sqlite.repository.FullCharRepository;
import com.zzm.sqlite.repository.UrlRepository;
import com.zzm.sqlite.service.UrlService;
import com.zzm.sqlite.utils.Ipv4Utils;
import com.zzm.sqlite.utils.PagedGridResult;
import com.zzm.sqlite.utils.UrlUtils;
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
public class UrlServiceImpl extends BaseService implements UrlService {

    private final UrlRepository urlRepository;
    private final ValueObjectTransfer valueObjectTransfer;

    @Override
    public PagedGridResult getRuleList(String username, Integer page, Integer pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);

        Long userId = getUserId(username);

        Specification<UrlRuleMsg> specification = new Specification<UrlRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<UrlRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                Predicate predicate = criteriaBuilder.equal(root.get("userId"), userId);
                list.add(predicate);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };

        Page<UrlRuleMsg> ruleMsgPage = urlRepository.findAll(pageable);

        List<UrlVO> urlVOList = ruleMsgPage.getContent().stream().map(urlRuleMsg -> {
            return (UrlVO) valueObjectTransfer.cast(urlRuleMsg, UrlVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, urlVOList, page, pageSize);
    }


    @Override
    public PagedGridResult getRuleListByCriteria(String username, Integer page, Integer pageSize, String criteria) {

        JSONObject jsonObject = UrlUtils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Long userId = getUserId(username);

        Specification<UrlRuleMsg> specification = new Specification<UrlRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<UrlRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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

        Page<UrlRuleMsg> ruleMsgPage = urlRepository.findAll(specification, pageable);

        List<UrlVO> urlVOList = ruleMsgPage.getContent().stream().map(urlRuleMsg -> {
            return (UrlVO) valueObjectTransfer.cast(urlRuleMsg, UrlVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, urlVOList, page, pageSize);
    }
    
}
