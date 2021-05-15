package com.wxf.reactiveservice;

import com.wxf.reactiveservice.reactive.GreetingClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveServiceApplication.class, args);

        System.out.println(new GreetingClient().getResult());
    }

}
