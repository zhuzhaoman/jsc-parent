package com.zzm.controller;

import com.zzm.annotation.SystemLog;
import com.zzm.exception.GraceException;
import com.zzm.pojo.bo.UserBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;
import com.zzm.service.UserService;
import com.zzm.utils.JSONResult;
import com.zzm.utils.WebSocketSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuzhaoman
 * @date 2020/8/1 0001 13:38
 * @description 用户基本操作
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 第一次登录，初始化WbeSocket链接
     *
     * @param simpMessagingTemplate
     */
    @Autowired
    public UserController(SimpMessagingTemplate simpMessagingTemplate) {
        WebSocketSendMessage.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * 用户登录
     *
     * @param userBO 参数
     * @throws InterruptedException
     */
    @PostMapping("/login")
    @SystemLog(description = "用户登录")
    public ReceiveSystemManagerDTO login(@RequestBody UserBO userBO) throws InterruptedException {
        try {
//            ReceiveSystemManagerDTO receiveSystemManagerDTO = new ReceiveSystemManagerDTO();
//            receiveSystemManagerDTO.setCode(200);
//            receiveSystemManagerDTO.setDomainType(8);
//            receiveSystemManagerDTO.setUsername("admin");
//            receiveSystemManagerDTO.setDomainId(1);
//            receiveSystemManagerDTO.setMessageCode(0);
//            receiveSystemManagerDTO.setMsg("登录成功");
//            return receiveSystemManagerDTO;

            if (userBO.getUsername().equals("guest")) {
                return userService.guestLogin(userBO);
            } else {
                return userService.login(userBO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GraceException.display("登录失败");
        }

        return null;
    }

    /**
     * 用户退出
     *
     * @return
     */
    @PostMapping("/logout")
    public JSONResult logout() {
        return JSONResult.ok();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @SystemLog(description = "获取当前用户信息")
    @GetMapping("/info")
    public JSONResult info(@RequestParam String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", Arrays.asList(token));
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super " + token);
        return JSONResult.ok(map);
    }

}
