package com.markus.accumulation.api.vo.constants;

import lombok.Getter;

/**
 * 异常码规范：
 * xxx - xxx - xxx
 * 业务 - 状态 - code
 * <p>
 * 业务取值
 * - 100 全局
 * - 200 xxx
 * - 300 xxx
 * - 400 xxx
 * <p>
 * 状态：基于http status的含义
 * - 4xx 调用方问题
 * - 5xx 服务内部问题
 * <p>
 * code: 具体的业务code
 *
 * @author: markus
 * @date: 2024/4/14 2:51 PM
 * @Description: 状态枚举
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Getter
public enum StatusEnum {

    SUCCESS(0, "OK"),
    FORBID_ERROR(100_403_001, "无权限"),
    CACHE_READ_EXCEPTION(100_500_001, "读取缓存时出现异常,缓存 key 为 %s"),
    ;


    private int code;
    private String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
