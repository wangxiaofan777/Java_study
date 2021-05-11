package com.wxf.eshaop.cache.ha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan // 开启Servlet扫描，与WebFilter配合使用
@SpringBootApplication
public class EShopCacheHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EShopCacheHaApplication.class, args);
    }

}
