package com.markus.accumulation.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: markus
 * @date: 2024/4/20 1:02 PM
 * @Description: JSON 工具
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class JsonUtil {
    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public static <T> T toObj(String str, Class<T> clazz) {
        try {
            return jsonMapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static <T> String toStr(T t) {
        try {
            return jsonMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
