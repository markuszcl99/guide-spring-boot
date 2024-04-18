package com.markus.accumulation.core.util;

import com.github.pagehelper.PageInfo;
import com.markus.accumulation.api.vo.PageRequest;
import com.markus.accumulation.api.vo.PageResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author: markus
 * @date: 2024/4/17 11:32 PM
 * @Description: 分页工具类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class PageUtils {
    public static <T, R> PageResult<R> getPageResult(PageInfo<T> pageInfo, Function<List<T>, List<R>> function) {
        PageResult<R> pageResult = new PageResult<>();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(function.apply(pageInfo.getList()));
        return pageResult;
    }
}
