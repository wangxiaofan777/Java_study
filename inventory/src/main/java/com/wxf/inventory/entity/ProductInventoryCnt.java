package com.wxf.inventory.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品库存entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventoryCnt {

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 库存数量
     */
    private Long inventoryCnt;

}
