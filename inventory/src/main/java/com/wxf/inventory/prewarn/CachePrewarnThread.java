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

                if ("".equals(taskidStatus)) {
                    String productidList = zkSession.getNodeData("/task-hot-product-list-" + taskid);
                    JSONArray productidJSONArray = JSONArray.parseArray(productidList);

                    for (int i = 0; i < productidJSONArray.size(); i++) {
                        Long productId = productidJSONArray.getLong(i);
                        String productInfoJSON = "{\"id\": " + productId + ", \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1, \"modifiedTime\": \"2017-01-01 12:00:00\"}";
                        ProductInfo productInfo = JSONObject.parseObject(productInfoJSON, ProductInfo.class);
                        cacheService.saveProductInfo2LocalCache(productInfo);
                        cacheService.saveProductInfo2RedisCache(productInfo);
                    }

                    zkSession.setNodeData(taskidStatusLockPath, "success");
                }

                zkSession.releaseDistributedLock(taskidStatusLockPath);

                zkSession.releaseDistributedLock(taskidLockPath);
            }
        }
    }
}
