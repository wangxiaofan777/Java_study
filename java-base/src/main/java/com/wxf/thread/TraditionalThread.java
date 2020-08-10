package com.wxf.thread;


/**
 * 子线程循环10次，接着主线程循环100次，
 * 接着又回到子线程循环十次，接着再回到主线程又循环100次，如此循环50次
 */
public class TraditionalThread {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub(i);

            }
        }).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }
    }
}

/**
 * 将要竞争的资源放入到一个类中，才可以进行资源控制
 */
class Business {

    private boolean lock = true;

    public synchronized void sub(int i) {
        while (!lock) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int j = 0; j <= 10; j++) {
            System.out.println("第" + i + "子循环" + j);
        }
        this.lock = false;
        this.notify();
    }

    public synchronized void main(int i) {
        while (lock) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 100; j++) {
            System.out.println("第" + i + "主循环" + j);
        }
        this.lock = true;
        this.notify();
    }
}