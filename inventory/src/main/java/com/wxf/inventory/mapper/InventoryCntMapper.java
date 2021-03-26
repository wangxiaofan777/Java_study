package com.wxf.inventory.mapper;

import com.wxf.inventory.entity.ProductInventoryCnt;
import org.apache.ibatis.annotations.Param;

public interface InventoryCntMapper {
    void updateInventoryCnt(ProductInventoryCnt productInventoryCnt);

    ProductInventoryCnt findProductInventoryCnt(@Param("produceId") Integer produceId);
}
