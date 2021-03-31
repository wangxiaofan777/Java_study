package com.wxf.inventory.config;


import lombok.Data;
import org.springframework.context.ApplicationContext;

/**
 * Spring上下文
 */
@Data
public class SpringContext {
//    ApplicationContextAware
    private ApplicationContext applicationContext;

}
