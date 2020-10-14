package com.wxf.instance;

/**
 * 静态内部类单例
 *
 * @since 2020-10-14 10:33:35
 */
public class Singleton {

    private static Singleton instance = null;

    //静态内部类
    private static class SingletonHolder {
        private static final Singleton SINGLETON = new Singleton();
    }

    //构造方法是优化
    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonHolder.SINGLETON;
    }
}
