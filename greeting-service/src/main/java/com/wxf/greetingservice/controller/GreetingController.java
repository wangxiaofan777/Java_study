package com.wxf.greetingservice.controller;

import com.wxf.greetingservice.service.GreetingService;
import com.wxf.greetingservice.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  /*  @Autowired
    private GreetingService greetingService;*/
    @Autowired
    private SayHelloService sayHelloService;

    /*@GetMapping(value = "/greeting")
    public String greeting(@RequestParam String name) {
        return greetingService.greeting(name);
    }*/

    @GetMapping(value = "/sayHello")
    public String sayHello(@RequestParam String name) {
        return sayHelloService.sayHello(name);
    }

}