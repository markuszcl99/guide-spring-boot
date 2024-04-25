package com.markus.accumulation.core.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: markus
 * @date: 2024/4/25 10:59 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Component
public class CustomerFilterRegister implements InitializingBean {

    @Value("${custom.log.filter.level:ERROR}")
    private String level;
    @Value("${custom.log.filter.prefix:''}")
    private String prefix;

    @Override
    public void afterPropertiesSet() {
        // 初始化 自定义日志 filter
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        loggerContext.addFilter(new CustomerFilter(prefix, getLevel(level), Filter.Result.NEUTRAL, Filter.Result.NEUTRAL));
    }

    private static Level getLevel(String level) {
        switch (level.toUpperCase()) {
            case "WARN":
                return Level.WARN;
            case "ERROR":
            default:
                return Level.ERROR;
        }
    }
}
