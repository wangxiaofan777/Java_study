package com.wxf.propertytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertyTestApplicationTests {

    @Value("spring.application.name")
    private static final String appName = null;

    @Test
    void contextLoads() {
        System.out.println(appName);
    }

}
