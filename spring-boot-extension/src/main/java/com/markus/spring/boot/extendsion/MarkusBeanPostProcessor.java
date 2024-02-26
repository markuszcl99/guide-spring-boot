package com.markus.spring.boot.extendsion;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
@Component
public class MarkusBeanPostProcessor implements BeanPostProcessor {
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("[bean name : " + beanName + "] MarkusBeanPostProcessor#postProcessBeforeInitialization...");
    return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("[bean name : " + beanName + "] MarkusBeanPostProcessor#postProcessAfterInitialization...");
    return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }
}
