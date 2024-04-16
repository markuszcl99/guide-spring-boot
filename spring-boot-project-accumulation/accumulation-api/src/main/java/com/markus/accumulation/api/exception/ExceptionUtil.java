package com.markus.accumulation.api.exception;

import com.markus.accumulation.api.vo.constants.StatusEnum;

/**
 * @author: markus
 * @date: 2024/4/14 2:40 PM
 * @Description: 异常工具类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ExceptionUtil {

    public static AccumulationException of(StatusEnum statusEnum, Object... args) {
        return new AccumulationException(statusEnum, args);
    }
}
