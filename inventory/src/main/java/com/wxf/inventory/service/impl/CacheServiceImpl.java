package com.wxf.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.entity.ShopInfo;
import com.wxf.inventory.service.CacheService;
import com.wxf.inventory.service.RedisService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品信息缓存Service实现类
 *
 * @author WangXiaofan777
 * @since 2021-03-29 18:06:26
 */
@Service(value = "cacheService")
public class CacheServiceImpl implements CacheService {

    private static final String CACHE_NAME = "local";

    @Resource
    private RedisService redisService;

    @Override
    @CachePut(value = CACHE_NAME, key = "'key_' + #productInfo.id")
    public ProductInfo saveLocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'key_' + #id", unless = "#result == null ")
    public ProductInfo getLocalCache(Long id) {
        return null;
    }

    @Override
    @CachePut(value = CACHE_NAME, key = "'product_info_' + #productInfo.id")
    public ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'product_info_' + #id", unless = "#result == null ")
    public ProductInfo getProductInfo2LocalCache(Long id) {
        return null;
    }

    @Override
    public ProductInfo saveProductInfo2RedisCache(ProductInfo productInfo) {
        String key = "product_info_" + productInfo.getId();
        redisService.set(key, JSONObject.toJSONString(productInfo));
        return productInfo;
    }

    @Override
    public ProductInfo getProductInfo2RedisCache(Long id) {
        String key = "product_info_" + id;
        String value = redisService.get(key);
        return JSONObject.parseObject(value, ProductInfo.class);
    }

    @Override
    public ShopInfo saveShopInfo2RedisCache(ShopInfo shopInfo) {
        String key = "shop_info_" + shopInfo.getId();
        redisService.set(key, JSONObject.toJSONString(shopInfo));
        return shopInfo;
    }

    @Override
    public ShopInfo getShopInfo2RedisCache(Long id) {
        String key = "shop_info_" + id;
        String value = redisService.get(key);
        return JSONObject.parseObject(value, ShopInfo.class);
    }

    @Override
    @CachePut(value = CACHE_NAME, key = "'shop_info_'+ #shopInfo.id")
    public ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo) {
        return shopInfo;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'shop_info_'+ #id", unless = "#result == null ")
    public ShopInfo getShopInfo2LocalCache(Long id) {
        return null;
    }
}
