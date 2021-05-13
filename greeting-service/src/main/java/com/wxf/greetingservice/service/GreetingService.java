package com.wxf.greetingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {

    /*@Autowired
    private RestTemplate restTemplate;

    public String greeting(String name) {
        return this.restTemplate.getForObject("http://SERVICE-HELLO-WORLD/hello?name=" + name, String.class);
    }*/
}
