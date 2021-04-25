package com.wxf.inventory.controller;

import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.entity.ShopInfo;
import com.wxf.inventory.prewarn.CachePrewarnThread;
import com.wxf.inventory.response.Response;
import com.wxf.inventory.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 商品本地缓存Controller
 *
 * @author WangXiaofan777
 * @since 2021-03-29 18:06:26
 */
@Validated
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

    @GetMapping(value = "/getProductInfo")
    public ProductInfo getProductInfo(Long productId) {
        ProductInfo productInfo = this.cacheService.getProductInfo2RedisCache(productId);
        if (productInfo == null)
            productInfo = this.cacheService.getProductInfo2LocalCache(productId);

        if (productInfo == null) {
            // 需要从数据库中获取
            productInfo = new ProductInfo(1L, "ceshi", 13, new Date());

        }
        return productInfo;
    }

    @GetMapping(value = "/getShopInfo")
    public ShopInfo getShopInfo(Long productId) {
        ShopInfo shopInfo = this.cacheService.getShopInfo2RedisCache(productId);

        if (shopInfo == null)
            shopInfo = this.cacheService.getShopInfo2LocalCache(productId);

        if (shopInfo == null) {
            // 需要从数据库中获取
            shopInfo = new ShopInfo(1L);
        }
        return shopInfo;
    }

    @GetMapping("/preWarmCache")
    public void preWarmCache() {
        new CachePrewarnThread().start();
    }
}
