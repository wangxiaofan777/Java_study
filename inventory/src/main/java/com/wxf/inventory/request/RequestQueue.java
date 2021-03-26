package com.wxf.inventory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class RequestQueue {

    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>(10);

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

    public void add(ArrayBlockingQueue<Request> queue) {
        queues.add(queue);
    }
}
