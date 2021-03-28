package com.wxf.inventory.thread;

import com.wxf.inventory.request.InventoryCntDBUpdateRequest;
import com.wxf.inventory.request.ProductInventoryCntReloadRequest;
import com.wxf.inventory.request.Request;
import com.wxf.inventory.request.RequestQueue;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程
 */
public class RequestProcessorThread implements Callable<Boolean> {

    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            while (true) {
                // ArrayBlockingQueue
                // Blocking就是说明：队列满了或者空的，那么在操作的时候都会阻塞住的
                Request request = this.queue.take();

                // 是否强制刷新
                boolean forceRefresh = request.isForceRefresh();
                if (forceRefresh) {
                    Integer productId = request.getProductId();
                    RequestQueue requestQueue = RequestQueue.getInstance();
                    Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();

                    if (request instanceof InventoryCntDBUpdateRequest) {
                        // 如果是一个更新数据库的请求，那么就将那个productId设置为true
                        flagMap.put(productId, true);
                    } else if (request instanceof ProductInventoryCntReloadRequest) {
                        // 如果是刷新缓存的请求，那么就判断，如果标识不为空，而且是true，就说嘛之前有一个这个商品的数据请求
                        Boolean flag = flagMap.get(productId);

                        // flag 是null
                        if (flag == null)
                            flagMap.put(productId, false);


                        if (flag != null && flag) {
                            flagMap.put(productId, false);
                        }

                        // 如果是缓存刷新的请求，而且发现标识不为空，并且是false，那说明之前已经有一个数据库更新+一个缓存刷新请求了
                        if (flag != null && !flag) {
                            // 对于这种请求，直接过滤掉，不需要放到后面的内存队列中去了
                            return true;
                        }
                    }
                }

                request.process();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
