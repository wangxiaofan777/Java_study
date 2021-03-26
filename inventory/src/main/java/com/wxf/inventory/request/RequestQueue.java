package com.wxf.inventory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class RequestQueue {

    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>(100);

    private RequestQueue() {
    }

    private static class Instance {
        private static RequestQueue queue;

        static {
            queue = new RequestQueue();
        }

        public static RequestQueue getInstance() {
            return queue;
        }
    }

    public static RequestQueue getInstance() {
        return Instance.getInstance();
    }

    /**
     * 添加一个内存队列
     *
     * @param queue
     */
    public void add(ArrayBlockingQueue<Request> queue) {
        queues.add(queue);
    }

    /**
     * 获取内存队列的数量
     *
     * @return
     */
    public int queueSize() {
        return queues.size();
    }

    /**
     * 获取内存队列
     *
     * @param index
     * @return
     */
    public ArrayBlockingQueue<Request> getQueue(int index) {
        return queues.get(index);
    }
}
