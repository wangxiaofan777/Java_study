package com.wxf.damon;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程测试类
 *
 * @author WangMaoSong
 * @since 2020-11-03 14:15:29
 */
public class DamonThread {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("this thread is running !!!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //设置为守护线程
//        thread.setDaemon(true);
        thread.start();

        System.out.println("Main thread is end");

    }
}
