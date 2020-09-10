package com.wxf.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Fork-Join
 *
 * @author WangMaoSong
 * @date {DATE} 16:35
 */

public class ForkJoinThread implements Runnable {

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool(1);

    private String name;

    public ForkJoinThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + ": is excuted...");
    }

    public static void main(String[] args) {
        System.out.println("可用CPU数："+Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < 10; i++) {
            ForkJoinThread t1 = new ForkJoinThread("Thread name:" + i);
            new Thread(t1).start();
        }

        try {
            forkJoinPool.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            forkJoinPool.shutdown();
        }
    }
}
