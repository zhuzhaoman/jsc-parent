package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.EtherMacRuleMsg;
import com.zzm.sqlite.pojo.FixedloccharcodeRuleMsg;
import com.zzm.sqlite.pojo.Ipv4RuleMsg;
import com.zzm.sqlite.pojo.Ipv6RuleMsg;
import com.zzm.sqlite.pojo.vo.FixCharVO;
import com.zzm.sqlite.pojo.vo.Ipv4VO;
import com.zzm.sqlite.pojo.vo.Ipv6VO;
import com.zzm.sqlite.repository.FixCharRepository;
import com.zzm.sqlite.repository.Ipv4Repository;
import com.zzm.sqlite.service.FixCharService;
import com.zzm.sqlite.service.Ipv4Service;
import com.zzm.sqlite.utils.FixCharUtils;
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
public class FixCharServiceImpl extends BaseService implements FixCharService {

    private final FixCharRepository fixCharRepository;
    private final ValueObjectTransfer valueObjectTransfer;


    @Override
    public PagedGridResult getRuleList(String username, Integer page, Integer pageSize) {

        Sort sort = Sort.by(Sort.Direction.DESC, "setTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Long userId = getUserId(username);

        Specification<FixedloccharcodeRuleMsg> specification = new Specification<FixedloccharcodeRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<FixedloccharcodeRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                Predicate predicate = criteriaBuilder.equal(root.get("userId"), userId);
                list.add(predicate);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };

        Page<FixedloccharcodeRuleMsg> ruleMsgPage = fixCharRepository.findAll(specification, pageable);

        List<FixCharVO> fixCharVOList = ruleMsgPage.getContent().stream().map(fixChar -> {
            return (FixCharVO) valueObjectTransfer.cast(fixChar, FixCharVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, fixCharVOList, page, pageSize);
    }


    @Override
    public PagedGridResult getRuleListByCriteria(String username, Integer page, Integer pageSize, String criteria) {

        JSONObject jsonObject = FixCharUtils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Long userId = getUserId(username);

        Specification<FixedloccharcodeRuleMsg> specification = new Specification<FixedloccharcodeRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<FixedloccharcodeRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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

        Page<FixedloccharcodeRuleMsg> ruleMsgPage = fixCharRepository.findAll(specification, pageable);

        List<FixCharVO> fixCharVOList = ruleMsgPage.getContent().stream().map(fixChar -> {
            return (FixCharVO) valueObjectTransfer.cast(fixChar, FixCharVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, fixCharVOList, page, pageSize);
    }
}
