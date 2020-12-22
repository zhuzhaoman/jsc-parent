package com.zzm.snmp;

import com.zzm.pojo.dto.ErrorMessageToEachBranchDTO;
import com.zzm.snmp.ErrorMessagePushEvent;
import com.zzm.utils.WebSocketSendMessage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuzhaoman
 * @date 2020/8/25 0025
 * @description 推送异常分类到前端，用于动态显示异常数值
 */
@Component
public class PushFrontHomeListener implements ApplicationListener<ErrorMessagePushEvent> {

    @Override
    public void onApplicationEvent(ErrorMessagePushEvent event) {

        ErrorMessageToEachBranchDTO eachBranchDTO =  (ErrorMessageToEachBranchDTO) event.getSource();

        Map<String, Object> result = new HashMap<>();
        result.put("msg", eachBranchDTO.getTitle());
        result.put("category", eachBranchDTO.getCategory());

        WebSocketSendMessage.sendMessageToUserSubscribe("all", "error", result);
    }
}
