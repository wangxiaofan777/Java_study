package com.wxf.inventory.service.impl;

import com.wxf.inventory.entity.ProductInventoryCnt;
import com.wxf.inventory.mapper.InventoryCntMapper;
import com.wxf.inventory.service.ProductInventoryCntService;
import com.wxf.inventory.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 库存管理Service
 */
@Service(value = "inventoryCntService")
public class ProductInventoryCntServiceImpl implements ProductInventoryCntService {

    @Resource
    private InventoryCntMapper inventoryCntMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public void updateInventoryCnt(ProductInventoryCnt productInventoryCnt) {
        this.inventoryCntMapper.updateInventoryCnt(productInventoryCnt);
    }

    @Override
    public void removeInventoryCnt(ProductInventoryCnt productInventoryCnt) {
        String key = "product:inventory" + productInventoryCnt.getProductId();
        this.redisService.delete(key);
    }

    @Override
    public ProductInventoryCnt findProductInventoryCnt(Integer produceId) {
        return this.inventoryCntMapper.findProductInventoryCnt(produceId);
    }

    @Override
    public void setProductInventoryCnt(ProductInventoryCnt productInventoryCnt) {
        String key = "product:inventory" + productInventoryCnt.getProductId();
        this.redisService.set(key, String.valueOf(productInventoryCnt.getInventoryCnt()));
    }

    @Override
    public ProductInventoryCnt getProductInventoryCntCache(Integer ProductId) {
        String key = "product:inventory" + ProductId;
        String result = this.redisService.get(key);

        // 缓存中如果没有取到，则直接返回空
        if (result == null || "".equals(result)) {
            return null;
        }

        return new ProductInventoryCnt(ProductId, Long.valueOf(result));
    }
}
