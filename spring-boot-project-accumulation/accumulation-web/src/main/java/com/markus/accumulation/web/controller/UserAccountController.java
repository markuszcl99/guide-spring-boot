package com.markus.accumulation.web.controller;

import com.markus.accumulation.api.vo.PageRequest;
import com.markus.accumulation.api.vo.PageResult;
import com.markus.accumulation.api.vo.Response;
import com.markus.accumulation.api.vo.user.UserInfoSaveReq;
import com.markus.accumulation.api.vo.user.UserPageRequest;
import com.markus.accumulation.api.vo.user.dto.UserInfoDTO;
import com.markus.accumulation.service.user.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: markus
 * @date: 2024/4/15 11:11 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RestController
@RequestMapping("/user/account")
public class UserAccountController {

    @Resource
    private IUserService userService;

    @PostMapping("/register")
    public Response<Void> register(@RequestBody UserInfoSaveReq userInfoSaveReq) {
        userService.saveUserInfo(userInfoSaveReq);
        return Response.ok(null);
    }

    @PostMapping("/findPage")
    public Response<PageResult<UserInfoDTO>> findPage(@RequestBody UserPageRequest userPageRequest) {
        return userService.findPage(userPageRequest);
    }
}
