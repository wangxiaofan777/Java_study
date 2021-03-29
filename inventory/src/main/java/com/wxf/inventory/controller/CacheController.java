package com.wxf.inventory.controller;

import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.response.Response;
import com.wxf.inventory.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品本地缓存Controller
 *
 * @author WangXiaofan777
 * @since 2021-03-29 18:06:26
 */
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    /**
     * 将商品信息保存到缓存中
     *
     * @param productInfo
     * @return
     */
    @GetMapping(value = "/saveLocalCache")
    public String saveLocalCache(ProductInfo productInfo) {
        this.cacheService.saveLocalCache(productInfo);
        return Response.SUCCESS;
    }


    /**
     * 从本地缓存获取商品信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getLocalCache")
    public ProductInfo getLocalCache(Long id) {
        return this.cacheService.getLocalCache(id);
    }

}
