package com.markus.accumulation.service.user.service;

import com.markus.accumulation.api.vo.PageResult;
import com.markus.accumulation.api.vo.Response;
import com.markus.accumulation.api.vo.user.UserInfoSaveReq;
import com.markus.accumulation.api.vo.user.UserPageRequest;
import com.markus.accumulation.api.vo.user.dto.UserInfoDTO;

import java.util.List;

/**
 * @author: markus
 * @date: 2024/4/14 3:14 PM
 * @Description: 用户服务类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface IUserService {

    void saveUserInfo(UserInfoSaveReq userInfoSaveReq);

    void updateUserInfo(UserInfoSaveReq userInfoSaveReq);

    void deleteUserInfo(Long userId);

    Response<UserInfoDTO> queryUserInfoByUserId(Long userId);

    Response<List<UserInfoDTO>> queryUserInfosByUsername(String username);

    Response<PageResult<UserInfoDTO>> findPage(UserPageRequest userPageRequest);
}
