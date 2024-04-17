package com.markus.accumulation.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author: markus
 * @date: 2024/4/17 11:26 PM
 * @Description: 分页返回结果
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;

    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据
     */
    private List<T> content;
}
