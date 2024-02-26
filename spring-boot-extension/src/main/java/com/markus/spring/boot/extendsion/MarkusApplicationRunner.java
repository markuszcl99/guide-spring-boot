package com.markus.spring.boot.extendsion;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
@Component
public class MarkusApplicationRunner implements ApplicationRunner {
  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("MarkusApplicationRunner");
  }
}
