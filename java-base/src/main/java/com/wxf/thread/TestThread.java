package com.wxf.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Executors.newWorkStealingPool();

        System.gc();

        List<Integer> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.stream().forEach((t)-> System.out.println(t));
    }
}
