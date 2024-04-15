package com.markus.accumulation.service.user.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markus.accumulation.api.model.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: markus
 * @date: 2024/4/14 3:29 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_info", autoResultMap = true)
public class UserInfoDO extends BaseDO {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;
    /**
     * 用户年龄
     */
    private Integer age;
}
