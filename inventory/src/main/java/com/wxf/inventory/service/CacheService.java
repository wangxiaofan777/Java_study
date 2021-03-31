package com.wxf.inventory.service;

import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.entity.ShopInfo;


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


    /**
     * 保存商品信息到本地缓存
     *
     * @param productInfo
     * @return
     */
    ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo);

    /**
     * 从本地缓存获取商品信息
     *
     * @param id
     * @return
     */
    ProductInfo getProductInfo2LocalCache(Long id);

    /**
     * 保存商品信息到Redis缓存
     *
     * @param productInfo
     * @return
     */
    ProductInfo saveProductInfo2RedisCache(ProductInfo productInfo);

    /**
     * 从Redis缓存获取商品信息
     *
     * @param id
     * @return
     */
    ProductInfo getProductInfo2RedisCache(Long id);


    /**
     * 保存店铺信息到Redis缓存
     *
     * @param shopInfo
     * @return
     */
    ShopInfo saveShopInfo2RedisCache(ShopInfo shopInfo);

    /**
     * 从Redis缓存获取店铺信息
     *
     * @param id
     * @return
     */
    ShopInfo getShopInfo2RedisCache(Long id);

    /**
     * 保存商铺信息到本地缓存
     *
     * @param shopInfo
     * @return
     */
    ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo);

    /**
     * 从本地缓存获取商铺信息
     *
     * @param id
     * @return
     */
    ShopInfo getShopInfo2LocalCache(Long id);
}
