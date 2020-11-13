package com.wxf.interrupt;

/**
 * interrupt测试
 *
 * @author WangMaoSong
 * @since 2020-11-13 09:20:56
 */

import java.util.concurrent.TimeUnit;

public class Interrupt {

    public static void main(String[] args) {
        System.out.println(Thread.interrupted());

        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().isInterrupted());
        }
    }
}
