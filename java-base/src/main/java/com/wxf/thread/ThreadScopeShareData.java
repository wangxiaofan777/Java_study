package com.wxf.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程数据共享
 */
public class ThreadScopeShareData {

    private static int data = 0;

    private static Map<Thread, Integer> threadData = new HashMap();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
//                    data =  new Random().nextInt();
                int data = new Random().nextInt();

                threadData.put(Thread.currentThread(), data);
                System.out.println(Thread.currentThread().getName() + " has put data : " + data);
                new A().get();
                new B().get();
            }).start();
        }
    }

    static class A {
        public void get() {
            Integer data = threadData.get(Thread.currentThread());
//            System.out.println("A from" + Thread.currentThread() + " get data :" + ThreadScopeShareData.data);
            System.out.println("A from" + Thread.currentThread().getName() + " get data : " + data);
        }
    }

    static class B {
        public void get() {
            Integer data = threadData.get(Thread.currentThread());
//            System.out.println("B from" + Thread.currentThread() + " get data :" + ThreadScopeShareData.data);
            System.out.println("B from" + Thread.currentThread().getName() + " get data : " + data);
        }
    }
}
