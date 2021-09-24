package com.zzm.service.impl;

import com.zzm.dao.OperationLogRepository;
import com.zzm.pojo.Errors;
import com.zzm.pojo.OperationLog;
import com.zzm.pojo.bo.SysLogBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.policy.system_manager.sending.sys_log.SystemManagerSendingSysLogComponent;
import com.zzm.policy.system_manager.sending.sys_log.SystemManagerSendingSysLogPolicyService;
import com.zzm.service.LogService;
import com.zzm.utils.PagedGridResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-07-13
 * @description:
 **/
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private OperationLogRepository operationLogRepository;

    @Override
    public ReceiveSystemManagerDTO operation(SysLogBO sysLogBO) {
        SystemManagerSendingSysLogPolicyService systemManagerSendingSysLogPolicyService =
                SystemManagerSendingSysLogComponent.systemManagerSendingSysLogPolicyServiceMap.get(sysLogBO.getSysLogType());

        return (ReceiveSystemManagerDTO) systemManagerSendingSysLogPolicyService.configDataEncapsulation(sysLogBO);
    }

    @Override
    public PagedGridResult<OperationLog> getUserLog(String username, Integer page, Integer pageSize) {
        Specification<OperationLog> specification = new Specification<OperationLog>() {

            /**
             *Predicate：封装了单个的查询条件
             * Root<User> root：封装了查询对象的属性
             * CriteriaQuery<?> criteriaQuery：封装了我们要执行的查询中的各个部分的信息，select from order by
             * CriteriaBuilder criteriaBuilder:查询条件的构造器，定义不同的条件查询
             */
            @Override
            public Predicate toPredicate(Root<OperationLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                /**
                 * 参数一：查询的条件属性
                 * 参数二：条件的值
                 */
                Predicate predicate1 = criteriaBuilder.equal(root.get("username"), username);

                list.add(predicate1);

                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };


        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);


        Page<OperationLog> errorsPage = operationLogRepository.findAll(specification, pageable);

        // 获取全部数据条数
        List<OperationLog> errorsList = errorsPage.getContent();

        /**
         * 封装返回分页数据
         */
        PagedGridResult<OperationLog> logPagedGridResult = new PagedGridResult<>();
        logPagedGridResult.setPage(page);
        logPagedGridResult.setSize(pageSize);
        logPagedGridResult.setTotal(errorsPage.getTotalElements());
        logPagedGridResult.setTotalPage(errorsPage.getTotalPages());
        logPagedGridResult.setList(errorsList);

        return logPagedGridResult;
    }

    @Override
    @Transactional
    public void saveUserLog(OperationLog operationLog) {
        operationLogRepository.save(operationLog);
    }
}
