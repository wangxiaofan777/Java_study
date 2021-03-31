package com.wxf.inventory.config;


import com.alibaba.fastjson.JSONObject;
import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka消息处理器
 *
 * @author WangXiaofan777
 * @since 2021-03-30 22:21:22
 */
@Component
public class KafkaMessageProcessor {

    @Autowired
    private CacheService cacheService;

    @KafkaListener(topics = "test")
    public void onMessage(String msg) {
        JSONObject msgJsonObject = JSONObject.parseObject(msg);
        String serviceId = msgJsonObject.getString("serviceId");
        if ("productService".equals(serviceId)) {
            this.processProductInfoChangeMessage(msgJsonObject);
        } else if ("shopService".equals(serviceId)) {
            this.processShopInfoChangeMessage(msgJsonObject);
        }

    }

    private void processProductInfoChangeMessage(JSONObject msgJsonObject) {
        Long productId = msgJsonObject.getLong("productId");
        // 模拟
        ProductInfo productInfo = new ProductInfo();
        cacheService.saveProductInfo2LocalCache(productInfo);
        cacheService.saveProductInfo2RedisCache(productInfo);

    }

    private void processShopInfoChangeMessage(JSONObject msgJsonObject) {
        Long productId = msgJsonObject.getLong("shopId");
        // 模拟
        ProductInfo productInfo = new ProductInfo();
        cacheService.saveProductInfo2LocalCache(productInfo);
        cacheService.saveProductInfo2RedisCache(productInfo);

    }
}
