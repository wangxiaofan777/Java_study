package com.wxf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Configuration
public class JavaBaseApplication {



    public static void main(String[] args) {
        SpringApplication.run(JavaBaseApplication.class, args);
    }

}
