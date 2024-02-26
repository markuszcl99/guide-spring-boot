package com.markus.spring.boot.extendsion;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
public class MarkusApplicationContextInitializer implements ApplicationContextInitializer {
  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    System.out.println("MarkusApplicationContextInitializer");
  }
}
