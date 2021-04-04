package com.wxf.inventory;

import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InventoryApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Test
    void saveProductInfo2LocalCache() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(1L);
        productInfo.setName("ceshi");
        productInfo.setPrice(1111);
        cacheService.saveProductInfo2LocalCache(productInfo);
    }

    @Test
    void getProductInfo2LocalCache() {
        ProductInfo productInfo = cacheService.getProductInfo2LocalCache(1L);
        System.out.println(productInfo);
    }

}
