package com.wxf.classInit;

import java.util.Random;

/**
 * 类初始化：
 *      1.调用静态变量
 *      2.静态方法都会使
 *      3.使用反射
 *      4.调用及静态变量
 *
 * @author WangMaoSong
 * @since 2020-10-14 14:07:25
 */
public class Simple {

    public static int x = 0;

    private static final int CONSTANCE_X = 10;

    private static final int CONSTANCE_Y = new Random(1000).nextInt();

    static {
        System.out.println("Simple has been initialized!");
    }

    public static void printX() {
        System.out.println(x);
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println(Simple.x);
//        Simple.printX();
//        System.out.println(Class.forName("com.wxf.classInit.Simple"));
        System.out.println(Simple.CONSTANCE_Y);
    }
}
