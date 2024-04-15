package com.markus.accumulation.service.user.service;

import com.markus.accumulation.api.vo.user.UserInfoSaveReq;

/**
 * @author: markus
 * @date: 2024/4/14 3:14 PM
 * @Description: 用户服务类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface IUserService {

    void saveUserInfo(UserInfoSaveReq userInfoSaveReq);
}
