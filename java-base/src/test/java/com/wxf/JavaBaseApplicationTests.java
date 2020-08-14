package com.wxf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@Configuration
class JavaBaseApplicationTests {

    @Value("${com.wxf.name}")
    public String value;


    @Test
    void contextLoads() {

        System.out.println(value);
    }

}
