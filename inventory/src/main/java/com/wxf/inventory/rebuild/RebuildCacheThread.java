package com.wxf.inventory.rebuild;

import com.wxf.inventory.config.SpringContextUtil;
import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.service.CacheService;
import com.wxf.inventory.zk.ZookeeperSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Date;

/**
 * 重建缓存队列线程
 */
@Slf4j
public class RebuildCacheThread implements Runnable {

    private CacheService cacheService = SpringContextUtil.getBean(CacheService.class);

    @Override
    public void run() {
        RebuildCacheQueue rebuildCacheQueue = RebuildCacheQueue.getRebuildCacheQueue();
        ProductInfo productInfo = rebuildCacheQueue.take();
        Long productId = productInfo.getId();
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
}
