package com.wxf.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品信息
 * @author WangXiaofan777
 */
@Data
@AllArgsConstructor
public class ProductInfo implements Serializable {

    private Long id;

    private String name;

    private Integer price;
}
