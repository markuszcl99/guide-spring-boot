package com.markus.accumulation.web.config;

import com.markus.accumulation.api.exception.AccumulationException;
import com.markus.accumulation.api.vo.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: markus
 * @date: 2024/4/16 11:18 PM
 * @Description: 全局异常处理
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccumulationException.class)
    public Response<String> handleAccumulationException(AccumulationException exception) {
        return Response.fail(exception.getStatus());
    }
}
