package com.markus.accumulation.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: markus
 * @date: 2024/4/17 11:23 PM
 * @Description: 分页请求
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;
}
