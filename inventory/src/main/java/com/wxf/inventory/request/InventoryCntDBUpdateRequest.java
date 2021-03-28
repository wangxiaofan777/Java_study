package com.wxf.inventory.request;


import com.wxf.inventory.entity.ProductInventoryCnt;
import com.wxf.inventory.service.ProductInventoryCntService;

/**
 * 比如说商品发生了交易，那么就要修改对应的库存
 *
 * <p>
 * 此时就会发送请求，要求修改库存，那么这个可能就是所谓的 data update request，数据更新请求
 * <p>
 * cache aside pattern
 * <p>
 * （1）删除缓存
 * （2）更新数据库
 */
public class InventoryCntDBUpdateRequest implements Request {

    private ProductInventoryCnt productInventoryCnt;

    private ProductInventoryCntService productInventoryCntService;

    public InventoryCntDBUpdateRequest(ProductInventoryCnt productInventoryCnt, ProductInventoryCntService productInventoryCntService) {
        this.productInventoryCnt = productInventoryCnt;
        this.productInventoryCntService = productInventoryCntService;
    }

    @Override
    public void process() {
        // 删除缓存
        this.productInventoryCntService.removeInventoryCnt(productInventoryCnt);
        // 更新数据库
        this.productInventoryCntService.updateInventoryCnt(productInventoryCnt);
    }

    @Override
    public Integer getProductId() {
        return productInventoryCnt.getProductId();
    }

    @Override
    public boolean isForceRefresh() {
        return false;
    }
}
