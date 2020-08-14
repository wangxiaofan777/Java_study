package com.wxf.thread;

import java.util.Random;

/**
 * 线程数据共享
 */
public class ThreadLocalTest {

    static ThreadLocal<Integer> x = new ThreadLocal();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
//                    data =  new Random().nextInt();
                int data = new Random().nextInt();

                x.set(data);
                System.out.println(Thread.currentThread().getName() + " has put data : " + data);
                new A().get();
                new B().get();
            }).start();
        }
    }

    static class A {
        public void get() {
            Integer data = x.get();
//            System.out.println("A from" + Thread.currentThread() + " get data :" + ThreadScopeShareData.data);
            System.out.println("A from" + Thread.currentThread().getName() + " get data : " + data);
        }
    }

    static class B {
        public void get() {
            Integer data = x.get();
//            System.out.println("B from" + Thread.currentThread() + " get data :" + ThreadScopeShareData.data);
            System.out.println("B from" + Thread.currentThread().getName() + " get data : " + data);
        }
    }
}
