package com.markus.accumulation.api.exception;

import com.markus.accumulation.api.vo.Status;
import com.markus.accumulation.api.vo.constants.StatusEnum;
import lombok.Getter;

/**
 * @author: markus
 * @date: 2024/4/14 2:40 PM
 * @Description: 项目自定义异常
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class AccumulationException extends RuntimeException {

    @Getter
    private Status status;

    public AccumulationException(Status status) {
        this.status = status;
    }

    public AccumulationException(int code, String msg) {
        this.status = Status.newStatus(code, msg);
    }

    public AccumulationException(StatusEnum statusEnum, Object... args) {
        this.status = Status.newStatus(statusEnum, args);
    }
}
