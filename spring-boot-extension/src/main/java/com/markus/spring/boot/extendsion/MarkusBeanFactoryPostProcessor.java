package com.markus.spring.boot.extendsion;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/26
 * @Description:
 */
@Component
public class MarkusBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    System.out.println("MarkusBeanFactoryPostProcessor statistics bean definition count is : "
        + configurableListableBeanFactory.getBeanDefinitionCount());
  }
}
