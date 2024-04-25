package com.markus.accumulation.core.log;

import com.markus.accumulation.core.cache.RedisClient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

/**
 * @author: markus
 * @date: 2024/4/25 10:43 PM
 * @Description: 自定义过滤器
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Plugin(name = "CustomerFilter", category = "Core", elementType = "filter")
public class CustomerFilter extends AbstractFilter {
    private final Level interceptLevel;
    private final String LOGGER_NAME_PREFIX;

    public CustomerFilter(String loggerNamePrefix, Level level, Result onMatch, Result onMismatch) {
        super(onMatch, onMismatch);
        this.interceptLevel = level;
        this.LOGGER_NAME_PREFIX = loggerNamePrefix;
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
        if (needReport(logger, level)) {
            int i = 0;
        }
        return super.filter(logger, level, marker, msg, t);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
        if (needReport(logger, level)) {
            int i = 0;
        }
        return super.filter(logger, level, marker, msg, t);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        if (needReport(logger, level)) {
            int i = 0;
        }
        return super.filter(logger, level, marker, msg, params);
    }

//    @PluginFactory
//    public static CustomerFilter createFilter(@PluginAttribute(value = "level", defaultString = "WARN") Level level,
//                                              @PluginAttribute(value = "onMatch", defaultString = "NEUTRAL") Filter.Result onMatch,
//                                              @PluginAttribute(value = "onMatch", defaultString = "DENY") Filter.Result onMismatch) {
//
//        return new CustomerFilter(level, onMatch, onMismatch);
//    }

    private boolean needReport(Logger logger, Level level) {
        return logger.getName().startsWith(LOGGER_NAME_PREFIX)
                && level.isMoreSpecificThan(interceptLevel);
    }
}
