package com.wxf.propertytest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class IndexController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping(value = "/")
    public String index() {
        return this.appName;
    }
}
