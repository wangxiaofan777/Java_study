package com.wxf.inventory.thread;

import com.wxf.inventory.request.Request;

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
                Request take = this.queue.take();
                take.process();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return true;
    }
}
