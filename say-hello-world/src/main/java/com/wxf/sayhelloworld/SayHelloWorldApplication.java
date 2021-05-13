package com.wxf.sayhelloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class SayHelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SayHelloWorldApplication.class, args);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/sayHello")
    public String sayHello(String name) {
        return "hello," + name + " from port:" + port;
    }
}
