package com.wxf.inventory.request;

import com.wxf.inventory.entity.ProductInventoryCnt;
import com.wxf.inventory.service.ProductInventoryCntService;

/**
 * 更新产品库存数量
 */
public class ProductInventoryCntReloadRequest implements Request {

    private Integer productId;

    private ProductInventoryCntService productInventoryCntService;

    public ProductInventoryCntReloadRequest(Integer productId, ProductInventoryCntService productInventoryCntService) {
        this.productId = productId;
        this.productInventoryCntService = productInventoryCntService;
    }

    @Override
    public void process() {
        // 将数据从DB中取出来
        ProductInventoryCnt productInventoryCnt = this.productInventoryCntService.findProductInventoryCnt(productId);
        // 将数据更新到缓存中
        this.productInventoryCntService.setProductInventoryCnt(productInventoryCnt);
    }

    @Override
    public Integer getProductId() {
        return productId;
    }
}
