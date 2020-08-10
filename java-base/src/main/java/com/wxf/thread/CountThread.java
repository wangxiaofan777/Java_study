package com.wxf.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 多线程累加实现
 */
public class CountThread implements Runnable {
    private AtomicInteger m;
    private CountDownLatch countDownLatch;

    public CountThread(AtomicInteger m, CountDownLatch countDownLatch) {
        this.m = m;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        m.getAndIncrement();
        countDownLatch.countDown();
//        System.out.println(m);
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            /*new Thread(() -> {
                atomicInteger.getAndIncrement();
                countDownLatch.countDown();
            }).start();*/
            new Thread(new CountThread(atomicInteger, countDownLatch)).start();
        }
        countDownLatch.await();

        System.out.println(atomicInteger.get());
    }

}
