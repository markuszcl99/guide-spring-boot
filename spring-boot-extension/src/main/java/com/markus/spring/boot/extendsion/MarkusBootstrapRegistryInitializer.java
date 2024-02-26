package com.markus.spring.boot.extendsion;

import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
public class MarkusBootstrapRegistryInitializer implements BootstrapRegistryInitializer {
  @Override
  public void initialize(BootstrapRegistry registry) {
    System.out.println("MarkusBootstrapRegistryInitializer");
  }
}
