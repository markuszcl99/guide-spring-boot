package com.markus.spring.boot.extendsion;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
public class MarkusContextRefreshedApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    System.out.println("MarkusContextRefreshedApplicationListener");
  }
}
