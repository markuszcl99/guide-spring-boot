package com.markus.springboot.starter.redis.config;

import com.markus.springboot.starter.redis.annotation.LRedis;
import com.markus.springboot.starter.redis.reflect.LRedisFactoryBean;
import com.markus.springboot.starter.redis.util.SimpleMetadataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.beans.Introspector;
import java.util.List;


/**
 * @author: markus
 * @date: 2024/4/19 10:28 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
@EnableConfigurationProperties(LRedisProperties.class)
public class LRedisRegisterAutoConfiguration implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(LRedisRegisterAutoConfiguration.class);

    @Autowired
    private LRedisProperties properties;


    @Bean
    @ConditionalOnMissingBean
    public Jedis jedis() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);

        JedisPool jedisPool = new JedisPool(config, properties.getHost(), properties.getPort());
        return jedisPool.getResource();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public static class LRedisRegister implements BeanFactoryAware, ImportBeanDefinitionRegistrar {

        private BeanFactory beanFactory;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            try {
                if (!AutoConfigurationPackages.has(this.beanFactory)) {
                    return;
                }


                List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
                String basePackage = StringUtils.collectionToCommaDelimitedString(packages);

                String packageSearchPath = "classpath*:" + basePackage.replace(".", "/") + "/**/*.class";

                ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
                Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

                for (Resource resource : resources) {
                    MetadataReader metadataReader = new SimpleMetadataReader(resource, ClassUtils.getDefaultClassLoader());
                    // 判断该类是否有注解
                    LRedis annotation = Class.forName(metadataReader.getClassMetadata().getClassName()).getAnnotation(LRedis.class);
                    if (annotation == null) continue;

                    ScannedGenericBeanDefinition beanDefinition = new ScannedGenericBeanDefinition(metadataReader);
                    String beanName = Introspector.decapitalize(ClassUtils.getShortName(beanDefinition.getBeanClassName()));

                    beanDefinition.setResource(resource);
                    beanDefinition.setSource(resource);
                    beanDefinition.setScope("singleton");
                    beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
                    beanDefinition.setBeanClass(LRedisFactoryBean.class);

                    BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(beanDefinition, beanName);
                    registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());
                }

            } catch (Exception exception) {
                logger.error("exception", exception);
            }
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }
    }

    @Configuration
    @Import(LRedisRegister.class)
    public static class MapperScannerRegistrarNotFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {

        }
    }
}
