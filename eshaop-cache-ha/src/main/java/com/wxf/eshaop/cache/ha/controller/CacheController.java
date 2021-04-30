package com.wxf.eshaop.cache.ha.controller;


import com.wxf.eshaop.cache.ha.http.HttpClientUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @GetMapping(value = "/change/product")
    public String changeProduct(Long productId) {
        // 数据同步的需要通过消息队列去更新，这里是为了方便直接HTTP方式
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);
        return "success";
    }
}
