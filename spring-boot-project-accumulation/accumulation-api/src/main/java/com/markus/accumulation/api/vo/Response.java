package com.markus.accumulation.api.vo;

import com.markus.accumulation.api.vo.constants.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: markus
 * @date: 2024/4/14 2:57 PM
 * @Description: 统一响应
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -1234567890L;

    @ApiModelProperty(value = "返回结果说明", required = true)
    private Status status;
    @ApiModelProperty(value = "返回结果", required = true)
    private T data;

    public Response() {

    }

    public Response(Status status) {
        this.status = status;
    }

    public Response(StatusEnum statusEnum) {
        this.status = Status.newStatus(statusEnum);
    }

    public Response(T data) {
        this.status = Status.newStatus(StatusEnum.SUCCESS);
        this.data = data;
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(data);
    }

    public static <T> Response<T> fail(StatusEnum statusEnum, Object... args) {
        return new Response<>(Status.newStatus(statusEnum, args));
    }

    public static <T> Response<T> fail(Status status) {
        return new Response<>(status);
    }
}
