package com.wxf.thread;

import java.util.concurrent.TimeUnit;

/**
 * Join测试
 *
 * @author WangMaoSong
 * @date {DATE} 16:11
 */
public class JoinThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Join执行...");
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            JoinThread joinThread = new JoinThread();
            Thread thread = new Thread(joinThread);
            thread.join(10000);
            thread.start();

            System.out.println("Main方法执行....");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
