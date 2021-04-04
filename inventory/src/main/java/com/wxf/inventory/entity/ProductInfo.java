package com.wxf.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品信息
 * @author WangXiaofan777
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo implements Serializable {

    private Long id;

    private String name;

    private Integer price;

    private Date modifyTime;
}
