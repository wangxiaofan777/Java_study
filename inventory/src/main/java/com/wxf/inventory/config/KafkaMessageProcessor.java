package com.wxf.inventory.config;


import com.alibaba.fastjson.JSONObject;
import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.service.CacheService;
import com.wxf.inventory.zk.ZookeeperSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Kafka消息处理器
 *
 * @author WangXiaofan777
 * @since 2021-03-30 22:21:22
 */
@Slf4j
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

        // 获取分布式锁
        ZookeeperSession zookeeperSession = ZookeeperSession.getZookeeperSession();
        zookeeperSession.acquireDistributedLock(productId);
        ProductInfo existProductInfo = this.cacheService.getProductInfo2RedisCache(productId);

        if (existProductInfo != null) {
            Date existProductInfoModifyTime = existProductInfo.getModifyTime();
            Date currentModifyTime = productInfo.getModifyTime();

            if (existProductInfoModifyTime.before(currentModifyTime)) {
                log.warn("");
                return;
            }
        } else {
            log.warn("缓存数据为空");
        }

        cacheService.saveProductInfo2RedisCache(productInfo);
        zookeeperSession.releaseDistributedLock(productId);
    }

    private void processShopInfoChangeMessage(JSONObject msgJsonObject) {
        Long productId = msgJsonObject.getLong("shopId");
        // 模拟
        ProductInfo productInfo = new ProductInfo();
        cacheService.saveProductInfo2LocalCache(productInfo);
        cacheService.saveProductInfo2RedisCache(productInfo);

    }
}
