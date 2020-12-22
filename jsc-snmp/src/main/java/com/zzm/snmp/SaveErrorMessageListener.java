package com.zzm.snmp;

import com.zzm.pojo.Errors;
import com.zzm.pojo.dto.ErrorMessageToEachBranchDTO;
import com.zzm.service.ErrorsService;
import com.zzm.snmp.ErrorMessagePushEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhuzhaoman
 * @date 2020/8/25 0025
 * @description 保存异常信息到数据库
 */
@SuppressWarnings("all")
@Component
public class SaveErrorMessageListener implements ApplicationListener<ErrorMessagePushEvent> {

    @Resource
    private ErrorsService errorsService;

    @Override
    public void onApplicationEvent(ErrorMessagePushEvent event) {

        ErrorMessageToEachBranchDTO eachBranchDTO = (ErrorMessageToEachBranchDTO) event.getSource();

        Errors error = new Errors();
        error.setId(UUID.randomUUID().toString().replace("-", ""));
        error.setTitle(eachBranchDTO.getTitle());
        error.setContent(eachBranchDTO.getContent());
        error.setCategory(eachBranchDTO.getCategory());
        error.setErrorName(eachBranchDTO.getErrorName());
        error.setFlag(0);
        error.setCreateTime(new Date());
        errorsService.saveErrors(error);
    }
}
