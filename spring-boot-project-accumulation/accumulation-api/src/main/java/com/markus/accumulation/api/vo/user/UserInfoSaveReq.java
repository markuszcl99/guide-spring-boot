package com.markus.accumulation.api.vo.user;

import lombok.Data;

/**
 * @author: markus
 * @date: 2024/4/14 3:15 PM
 * @Description: 用户详情保存请求携带信息
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
public class UserInfoSaveReq {
    private Long userId;
    private String username;
    private Integer age;
}
