package com.markus.accumulation.api.vo;

import com.markus.accumulation.api.vo.constants.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: markus
 * @date: 2024/4/14 2:42 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @ApiModelProperty(value = "状态码，0 表示正常返回，其他表示异常返回", required = true, example = "0")
    private int code;
    @ApiModelProperty(value = "正确返回时为 ok，其他为错误描述信息", required = true, example = "ok")
    private String message;

    public static Status newStatus(int code, String message) {
        return new Status(code, message);
    }

    public static Status newStatus(StatusEnum status, Object... messages) {
        String message;
        if (messages.length > 0) {
            message = String.format(status.getMessage(), messages);
        } else {
            message = status.getMessage();
        }
        return new Status(status.getCode(), message);
    }
}
