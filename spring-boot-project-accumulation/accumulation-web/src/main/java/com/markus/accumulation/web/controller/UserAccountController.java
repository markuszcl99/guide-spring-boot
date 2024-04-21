package com.markus.accumulation.web.controller;

import com.markus.accumulation.api.vo.PageRequest;
import com.markus.accumulation.api.vo.PageResult;
import com.markus.accumulation.api.vo.Response;
import com.markus.accumulation.api.vo.user.UserInfoSaveReq;
import com.markus.accumulation.api.vo.user.UserPageRequest;
import com.markus.accumulation.api.vo.user.dto.UserInfoDTO;
import com.markus.accumulation.service.user.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/update")
    public Response<Void> update(@RequestBody UserInfoSaveReq userInfoSaveReq) {
        userService.updateUserInfo(userInfoSaveReq);
        return Response.ok(null);
    }

    @PostMapping("/delete")
    public Response<Void> delete(@RequestParam("userId") Long userId) {
        userService.deleteUserInfo(userId);
        return Response.ok(null);
    }

    @PostMapping("/findPage")
    public Response<PageResult<UserInfoDTO>> findPage(@RequestBody UserPageRequest userPageRequest) {
        return userService.findPage(userPageRequest);
    }

    @GetMapping("/query")
    public Response<UserInfoDTO> queryUserInfo(@RequestParam("userId") Long userId) {
        return userService.queryUserInfoByUserId(userId);
    }

    @GetMapping("/query-by-username")
    public Response<List<UserInfoDTO>> queryUserInfoByUsername(@RequestParam("username") String username) {
        return userService.queryUserInfosByUsername(username);
    }
}
