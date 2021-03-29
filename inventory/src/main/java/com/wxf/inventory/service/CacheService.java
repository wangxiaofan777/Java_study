package com.wxf.inventory.service;

import com.wxf.inventory.entity.ProductInfo;


/**
 * 商品信息缓存Service
 *
 * @author WangXiaofan777
 * @since 2021-03-29 18:06:26
 */
public interface CacheService {

    /**
     * 将商品信息保存到缓存中
     *
     * @param productInfo
     * @return
     */
    ProductInfo saveLocalCache(ProductInfo productInfo);


    /**
     * 从本地缓存获取商品信息
     *
     * @param id
     * @return
     */
    ProductInfo getLocalCache(Long id);


}
