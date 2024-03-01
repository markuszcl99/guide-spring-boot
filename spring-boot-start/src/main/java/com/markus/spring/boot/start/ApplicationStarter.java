package com.markus.spring.boot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/3/1
 * @Description:
 */
@SpringBootApplication
public class ApplicationStarter {
  /**
   * SpringBoot 启动原理:
   *    1. 初始化 SpringApplication 实例
   *      1.1 指定 ResourceLoader（如果有的话）
   *      1.2 指定 primarySource (对应 ApplicationStarter Class)
   *      1.3 指定 web 应用类型 (org.springframework.boot.WebApplicationType : NONE SERVLET REACTIVE)
   *      1.3 获取系统的初始化器 (框架 & 用户自定义)
   *        1.3.1 BootstrapRegistryInitializer
   *        1.3.2 ApplicationContextInitializer
   *      1.4 获取监听器 (框架 & 用户自定义)
   *        1.4.1 ApplicationListener
   *      1.5 指定系统应用的主类 (当前线程调用栈 main 函数对应的类)
   *    2. 执行 SpringApplication#run(args) 方法
   *      2.1 创建启动器上下文
   *      2.2 获取 SpringApplicationRunListener 集合
   *      2.3 将 "应用开始启动" 事件通知给 SpringApplicationRunListener 监听器
   *      2.4 Environment 相关事项处理
   *        2.4.1 创建 ConfigurableEnvironment
   *        2.4.2 配置 ConfigurableEnvironment，包括 主函数运行时参数、监听器提供的参数以及默认参数等
   *        2.4.3 打印 Banner (不打印、打印到 logger 日志、打印到控制台)
   *          2.4.3.1 Banner 可以自定义(图片 Banner and 文案 Banner)，默认为 SpringBanner
   *            两个配置 spring.banner.location | spring.banner.image.location
   *
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(ApplicationStarter.class, args);
  }
}
