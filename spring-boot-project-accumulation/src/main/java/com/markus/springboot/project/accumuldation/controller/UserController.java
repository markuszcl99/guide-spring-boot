package com.markus.springboot.project.accumuldation.controller;

import com.markus.springboot.project.accumuldation.controller.dto.UserRequestDTO;
import com.markus.springboot.project.accumuldation.service.UserService;
import com.markus.springboot.project.accumuldation.types.common.Constants;
import com.markus.springboot.project.accumuldation.types.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: markus
 * @date: 2024/4/12 11:15 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Response<String> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.add(userRequestDTO);
        return Response.<String>builder()
                .code(Constants.ResponseCode.SUCCESS.getCode())
                .message(Constants.ResponseCode.SUCCESS.getMessage())
                .build();
    }
}
