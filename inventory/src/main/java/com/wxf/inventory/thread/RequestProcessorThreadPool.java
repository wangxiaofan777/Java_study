package com.wxf.inventory.thread;

import com.wxf.inventory.request.Request;
import com.wxf.inventory.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 请求处理线程池：单例
 */
public class RequestProcessorThreadPool {

    // 实际项目中线程数和队列内存大小都是可配置的
    private ExecutorService executorService = Executors.newFixedThreadPool(10);


    public RequestProcessorThreadPool() {
        RequestQueue requestQueue = RequestQueue.getInstance();
        for (int i = 0; i < 10; i++) {
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
            requestQueue.add(queue);
            executorService.submit(new RequestProcessorThread(queue));
        }
    }

    /**
     * 单例有很多种方式去实现：采用绝对安全的一种方式去实现
     * 静态内部类的方式，去初始化单例
     */
    private static class Singleton {
        private static RequestProcessorThreadPool instance;

        static {
            instance = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance() {
            return instance;
        }
    }

    /**
     * JVM的机制保证多线程并发安全
     * 内部类初始化，一定只会发生一次，不管有多少个线程去并发初始化
     *
     * @return
     */
    public static RequestProcessorThreadPool getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 便捷初始化方法
     *
     * @return
     */
    public static RequestProcessorThreadPool init() {
        return getInstance();
    }
}
