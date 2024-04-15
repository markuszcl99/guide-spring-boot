package com.markus.accumulation.api.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: markus
 * @date: 2024/4/15 11:15 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
public class BaseDTO {

    @ApiModelProperty(value = "业务主键")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后编辑时间")
    private Date updateTime;
}
