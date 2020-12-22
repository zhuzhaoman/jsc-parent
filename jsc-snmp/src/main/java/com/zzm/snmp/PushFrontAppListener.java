package com.zzm.snmp;

import com.zzm.pojo.dto.ErrorMessageToEachBranchDTO;
import com.zzm.pojo.vo.MessageVO;
import com.zzm.utils.WebSocketSendMessage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zhuzhaoman
 * @date 2020/8/25 0025
 * @description 推送异常信息到前端App页下，用于全局消息提示
 */
@Component
public class PushFrontAppListener implements ApplicationListener<ErrorMessagePushEvent> {

    @Override
    public void onApplicationEvent(ErrorMessagePushEvent event) {

        ErrorMessageToEachBranchDTO eachBranchDTO =  (ErrorMessageToEachBranchDTO) event.getSource();
        MessageVO message = new MessageVO(200, eachBranchDTO.getTitle(), eachBranchDTO.getContent(), "warning");
        WebSocketSendMessage.sendTopicMessage(message);
    }
}
