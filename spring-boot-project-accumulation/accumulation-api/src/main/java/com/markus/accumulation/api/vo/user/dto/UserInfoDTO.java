package com.markus.accumulation.api.vo.user.dto;

import com.markus.accumulation.api.model.entity.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: markus
 * @date: 2024/4/15 11:17 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
@ApiModel("用户基础实体对象")
@Accessors(chain = true)
public class UserInfoDTO extends BaseDTO {

    private String username;
    private Integer age;
}
