package com.markus.spring.boot.extendsion;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
public class MarkusContextStartedApplicationListener implements ApplicationListener<ApplicationStartedEvent> {
  @Override
  public void onApplicationEvent(ApplicationStartedEvent event) {
    System.out.println("MarkusContextStartedApplicationListener");
  }
}
