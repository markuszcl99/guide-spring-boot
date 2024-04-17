package com.markus.accumulation.core.util;

import com.github.pagehelper.PageInfo;
import com.markus.accumulation.api.vo.PageRequest;
import com.markus.accumulation.api.vo.PageResult;

/**
 * @author: markus
 * @date: 2024/4/17 11:32 PM
 * @Description: 分页工具类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class PageUtils {
    public static <T> PageResult<T> getPageResult(PageRequest pageRequest, PageInfo<T> pageInfo) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageResult.getTotalPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
