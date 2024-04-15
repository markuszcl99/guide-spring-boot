package com.markus.accumulation.service.user.converter;

import com.markus.accumulation.api.vo.user.UserInfoSaveReq;
import com.markus.accumulation.service.user.repository.entity.UserInfoDO;
import com.sun.istack.internal.NotNull;

/**
 * @author: markus
 * @date: 2024/4/14 3:20 PM
 * @Description: 用户转换类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class UserConverter {
    /**
     * 该类转换是手动编码实现 Req to DO 的转换
     */

    public static UserInfoDO toDO(@NotNull UserInfoSaveReq userInfoSaveReq) {
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setId(userInfoSaveReq.getUserId());
        userInfoDO.setUsername(userInfoSaveReq.getUsername());
        userInfoDO.setAge(userInfoSaveReq.getAge());
        return userInfoDO;
    }
}
