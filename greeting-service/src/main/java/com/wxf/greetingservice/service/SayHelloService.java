package com.wxf.greetingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hello-world")
public interface SayHelloService {

    @GetMapping(value = "/sayHello")
    public String sayHello(@RequestParam(value = "name") String name);
}
