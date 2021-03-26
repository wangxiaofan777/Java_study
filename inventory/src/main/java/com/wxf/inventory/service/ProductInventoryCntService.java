package com.wxf.inventory.service;

import com.wxf.inventory.entity.ProductInventoryCnt;

public interface ProductInventoryCntService {

    void updateInventoryCnt(ProductInventoryCnt productInventoryCnt);

    void removeInventoryCnt(ProductInventoryCnt productInventoryCnt);

    ProductInventoryCnt findProductInventoryCnt(Integer produceId);

    void setProductInventoryCnt(ProductInventoryCnt productInventoryCnt);

    ProductInventoryCnt getProductInventoryCntCache(Integer ProductId);

}
