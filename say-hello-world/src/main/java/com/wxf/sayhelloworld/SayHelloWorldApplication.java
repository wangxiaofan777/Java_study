package com.wxf.sayhelloworld;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
public class SayHelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SayHelloWorldApplication.class, args);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/sayHello")
    @HystrixCommand(fallbackMethod = "sayHelloFallback")
    public String sayHello(String name) {
        return "hello," + name + " from port:" + port;
    }

    public String sayHelloFallback(String name) {
        return "error, " + name;
    }
}
