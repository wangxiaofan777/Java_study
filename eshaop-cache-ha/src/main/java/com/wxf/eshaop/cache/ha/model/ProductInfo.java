package com.wxf.eshaop.cache.ha.model;

import lombok.Data;

/**
 * 商品信息
 */
@Data
public class ProductInfo {

    private Long id;
    private String name;
    private Double price;
    private String pictureList;
    private String specification;
    private String service;
    private String color;
    private String size;
    private Long shopId;
    private String modifyTime;
    private Long cityId;
    private String cityName;


}
