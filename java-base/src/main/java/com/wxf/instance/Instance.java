package com.wxf.instance;

import java.util.Random;

/**
 * 饿汉式单例
 *
 * @since 2020-10-14 09:56:29
 */
public class Instance {

    private static Instance instance = new Instance();

    public static final int INSTANCE = new Random().nextInt();


    //构造方法私有化
    private Instance() {
        System.out.println("Instance has been initialized!!!");
    }

    public static Instance getInstance() {
        return instance;
    }
}
