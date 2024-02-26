package com.markus.spring.boot.extendsion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
@SpringBootApplication
public class SpringApplicationStarter {
  /**
   * Spring Boot 启动时的几个扩展点：
   *  1. org.springframework.boot.SpringApplication#bootstrapRegistryInitializers
   *  2. org.springframework.boot.SpringApplication#initializers
   *  3. org.springframework.boot.SpringApplication#listeners
   *  4. org.springframework.boot.SpringApplication#callRunners(org.springframework.context.ApplicationContext, org.springframework.boot.ApplicationArguments)
   *
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringApplicationStarter.class, args);
  }
}
