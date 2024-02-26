package com.markus.spring.boot.extendsion;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
public class MarkusContextStartingApplicationListener implements ApplicationListener<ApplicationStartingEvent> {
  @Override
  public void onApplicationEvent(ApplicationStartingEvent event) {
    System.out.println("MarkusContextStartingApplicationListener");
  }
}
