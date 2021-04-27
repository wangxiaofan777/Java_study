package com.wxf.inventory.prewarn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxf.inventory.config.SpringContextUtil;
import com.wxf.inventory.entity.ProductInfo;
import com.wxf.inventory.service.CacheService;
import com.wxf.inventory.zk.ZookeeperSession;

public class CachePrewarnThread extends Thread {

    @Override
    public void run() {
        CacheService cacheService = SpringContextUtil.getBean(CacheService.class);
        ZookeeperSession zkSession = ZookeeperSession.getZookeeperSession();

        // 获取storm taskid列表
        String taskidList = zkSession.getNodeData("/taskid-list");

        System.out.println("【CachePrwarmThread获取到taskid列表】taskidList=" + taskidList);

        if (taskidList != null && !"".equals(taskidList)) {
            String[] taskidListSplited = taskidList.split(",");
            for (String taskid : taskidListSplited) {
                String taskidLockPath = "/taskid-lock-" + taskid;

                boolean result = zkSession.acquireFastFailedDistributedLock(taskidLockPath);
                if (!result) {
                    continue;
                }

                String taskidStatusLockPath = "/taskid-status-lock-" + taskid;
                zkSession.acquireDistributedLock(taskidStatusLockPath);

                String taskidStatus = zkSession.getNodeData("/taskid-status-" + taskid);
                System.out.println("【CachePrewarmThread获取task的预热状态】taskid=" + taskid + ", status=" + taskidStatus);

                if ("".equals(taskidStatus)) {
                    String productidList = zkSession.getNodeData("/task-hot-product-list-" + taskid);
                    System.out.println("【CachePrewarmThread获取到task的热门商品列表】productidList=" + productidList);
                    JSONArray productidJSONArray = JSONArray.parseArray(productidList);

                    for (int i = 0; i < productidJSONArray.size(); i++) {
                        Long productId = productidJSONArray.getLong(i);
                        String productInfoJSON = "{\"id\": " + productId + ", \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1, \"modifiedTime\": \"2017-01-01 12:00:00\"}";
                        ProductInfo productInfo = JSONObject.parseObject(productInfoJSON, ProductInfo.class);
                        cacheService.saveProductInfo2LocalCache(productInfo);
                        System.out.println("【CachePrwarmThread将商品数据设置到本地缓存中】productInfo=" + productInfo);
                        cacheService.saveProductInfo2RedisCache(productInfo);
                        System.out.println("【CachePrwarmThread将商品数据设置到redis缓存中】productInfo=" + productInfo);
                    }

                    zkSession.createNode("/taskid-status-" + taskid);
                    zkSession.setNodeData("/taskid-status-" + taskid, "success");
                }

                zkSession.releaseDistributedLock(taskidStatusLockPath);

                zkSession.releaseDistributedLock(taskidLockPath);
            }
        }
    }
}
