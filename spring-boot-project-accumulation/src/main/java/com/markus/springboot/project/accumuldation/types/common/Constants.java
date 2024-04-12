package com.markus.springboot.project.accumuldation.types.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: markus
 * @date: 2024/4/12 11:24 PM
 * @Description: 常量
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Constants {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知失败"),
        ;

        private String code;
        private String message;
    }
}
