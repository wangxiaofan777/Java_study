package com.wxf.inventory.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * EhCache缓存配置类
 *
 * @author WangXiaofan777
 * @since 2021-03-29 15:05:12
 */
@Component
@Configuration
public class CacheConfiguration {

    /*@Bean
    public EhCacheManagerFactoryBean ehCacheFactoryBean() {
        EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("/cache/ehcache.xml"));
        ehCacheFactoryBean.setShared(true);
        return ehCacheFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehcacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
    }*/
}
