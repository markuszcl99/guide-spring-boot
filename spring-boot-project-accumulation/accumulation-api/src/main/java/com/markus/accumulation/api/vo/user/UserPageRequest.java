package com.markus.accumulation.api.vo.user;

import com.markus.accumulation.api.vo.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: markus
 * @date: 2024/4/17 11:39 PM
 * @Description: 用户分页查询 请求
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPageRequest extends PageRequest {
    private String username;
}
