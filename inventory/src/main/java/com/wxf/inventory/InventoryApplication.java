package com.wxf.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;


/**
 * 注解ServletComponentScan需要和WebListener配合使用
 */
@EnableCaching  // 开启缓存
@SpringBootApplication
@ServletComponentScan // 开启Servlet扫描
@MapperScan("com.wxf.inventory.mapper")
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

}
