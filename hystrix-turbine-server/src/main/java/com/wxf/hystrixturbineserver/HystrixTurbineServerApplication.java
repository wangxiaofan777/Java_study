package com.wxf.hystrixturbineserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@SpringBootApplication
public class HystrixTurbineServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineServerApplication.class, args);
    }

}
