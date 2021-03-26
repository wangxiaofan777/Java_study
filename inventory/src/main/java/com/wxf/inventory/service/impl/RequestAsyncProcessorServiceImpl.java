package com.wxf.inventory.service.impl;

import com.wxf.inventory.request.InventoryCntDBUpdateRequest;
import com.wxf.inventory.request.ProductInventoryCntReloadRequest;
import com.wxf.inventory.request.Request;
import com.wxf.inventory.request.RequestQueue;
import com.wxf.inventory.service.RequestAsyncProcessorService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 请求异步处理的Service实现
 */
@Service(value = "requestAsyncProcessorServiceImpl")
public class RequestAsyncProcessorServiceImpl implements RequestAsyncProcessorService {

    @Override
    public void process(Request request) {
        try {
            Integer productId = request.getProductId();
            RequestQueue requestQueue = RequestQueue.getInstance();
            Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();

            if (request instanceof InventoryCntDBUpdateRequest) {
                // 如果是一个更新数据库的请求，那么就将那个productId设置为true
                flagMap.put(productId, true);
            } else if (request instanceof ProductInventoryCntReloadRequest) {
                // 如果是刷新缓存的请求，那么就判断，如果标识不为空，而且是true，就说嘛之前有一个这个商品的数据请求
                Boolean flag = flagMap.get(productId);
                if (flag != null && flag) {
                    flagMap.put(productId, false);
                }

                // 如果是缓存刷新的请求，而且发现标识不为空，并且是false，那说明之前已经有一个数据库更新+一个缓存刷新请求了
                if (flag != null && !flag) {
                    // 对于这种请求，直接过滤掉，不需要放到后面的内存队列中去了
                    return;
                }
            }
            // 做请求的路由，根据每个请求的商品ID，路由到对应的队列去
            ArrayBlockingQueue<Request> queue = getRouteQueue(productId);
            queue.put(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取路由到的内存队列
     *
     * @param productId 商品ID
     * @return 内存队列
     */
    private ArrayBlockingQueue<Request> getRouteQueue(Integer productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();
        // 获取ProductId的hash值
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        // 对hash值进行取模，将hash值路由到指定的内存队列中
        int index = (requestQueue.queueSize() - 1) & hash;
        return requestQueue.getQueue(index);
    }
}
