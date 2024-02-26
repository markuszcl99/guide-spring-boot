package com.markus.spring.boot.extendsion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
@Component
public class MarkusCommandLineRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    System.out.println("MarkusCommandLineRunner");
  }
}
