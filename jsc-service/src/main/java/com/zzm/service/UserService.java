package com.zzm.service;


import com.zzm.pojo.bo.UserBO;
import com.zzm.pojo.dto.ReceiveSystemManagerDTO;

public interface UserService {
    /**
     * 用户登录
     *
     * @param userBO
     */
    ReceiveSystemManagerDTO login(UserBO userBO) throws Exception;

    ReceiveSystemManagerDTO guestLogin(UserBO userBO);

    ReceiveSystemManagerDTO config(UserBO userBO);

    ReceiveSystemManagerDTO getInfo(UserBO userBO);
}
