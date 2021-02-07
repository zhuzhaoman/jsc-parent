package com.zzm.sqlite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzm.sqlite.pojo.WindowRuleMsg;
import com.zzm.sqlite.pojo.WindowRuleMsg;
import com.zzm.sqlite.pojo.vo.WindowVO;
import com.zzm.sqlite.pojo.vo.VlanVO;
import com.zzm.sqlite.pojo.vo.WindowVO;
import com.zzm.sqlite.repository.WindowRepository;
import com.zzm.sqlite.service.WindowService;
import com.zzm.sqlite.utils.Ipv4Utils;
import com.zzm.sqlite.utils.PagedGridResult;
import com.zzm.sqlite.utils.ValueObjectTransfer;
import com.zzm.sqlite.utils.WindowUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class WindowServiceImpl extends BaseService implements WindowService {

    private final WindowRepository windowRepository;
    private final ValueObjectTransfer valueObjectTransfer;

    @Override
    public PagedGridResult getRuleList(Integer page, Integer pageSize) {

        Sort sort = Sort.by(Sort.Direction.DESC, "setTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<WindowRuleMsg> ruleMsgPage = windowRepository.findAll(pageable);

        List<WindowVO> windowVOList = ruleMsgPage.getContent().stream().map(windowRuleMsg -> {
            return (WindowVO) valueObjectTransfer.cast(windowRuleMsg, WindowVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, windowVOList, page, pageSize);
    }


    @Override
    public PagedGridResult getRuleListByCriteria(Integer page, Integer pageSize, String criteria) {

        JSONObject jsonObject = WindowUtils.queryFieldCast(criteria);
        Set<String> keys = jsonObject.keySet();

        Specification<WindowRuleMsg> specification = new Specification<WindowRuleMsg>() {
            @Override
            public Predicate toPredicate(Root<WindowRuleMsg> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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

        Page<WindowRuleMsg> ruleMsgPage = windowRepository.findAll(specification, pageable);

        List<WindowVO> windowVOList = ruleMsgPage.getContent().stream().map(windowRuleMsg -> {
            return (WindowVO) valueObjectTransfer.cast(windowRuleMsg, WindowVO.class);
        }).collect(Collectors.toList());

        return setPagedGrid(ruleMsgPage, windowVOList, page, pageSize);
    }
}
