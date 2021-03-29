package com.wxf.inventory.service.impl;

import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.service.CacheService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 商品信息缓存Service实现类
 *
 * @author WangXiaofan777
 * @since 2021-03-29 18:06:26
 */
@Service(value = "cacheService")
public class CacheServiceImpl implements CacheService {

    private static final String CACHE_NAME = "local";

    @Override
    @CachePut(value = CACHE_NAME, key = "'key_' + #productInfo.id")
    public ProductInfo saveLocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'key_' + #id")
    public ProductInfo getLocalCache(Long id) {
        return null;
    }
}
