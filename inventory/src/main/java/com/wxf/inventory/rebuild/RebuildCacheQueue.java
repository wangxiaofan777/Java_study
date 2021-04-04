package com.wxf.inventory.rebuild;

import com.wxf.inventory.entity.ProductInfo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 缓存重建队列
 */
public class RebuildCacheQueue {

    private ArrayBlockingQueue<ProductInfo> productInfos = new ArrayBlockingQueue<>(100);

    /**
     * 添加产品信息到队列中
     *
     * @param productInfo
     */
    public void add(ProductInfo productInfo) {
        try {
            productInfos.put(productInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取产品数据
     *
     * @param productId
     * @return
     */
    public ProductInfo take() {
        try {
            return productInfos.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class RebuildCacheQueueInstance {
        private static RebuildCacheQueue rebuildCacheQueue;

        static {
            rebuildCacheQueue = new RebuildCacheQueue();
        }

        public static RebuildCacheQueue getInstance() {
            return rebuildCacheQueue;
        }
    }

    public static RebuildCacheQueue getRebuildCacheQueue() {
        return RebuildCacheQueueInstance.getInstance();
    }
}
