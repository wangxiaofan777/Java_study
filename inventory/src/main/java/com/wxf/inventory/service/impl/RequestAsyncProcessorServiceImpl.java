package com.wxf.inventory.service.impl;

import com.wxf.inventory.request.Request;
import com.wxf.inventory.request.RequestQueue;
import com.wxf.inventory.service.RequestAsyncProcessorService;
import org.springframework.stereotype.Service;

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
