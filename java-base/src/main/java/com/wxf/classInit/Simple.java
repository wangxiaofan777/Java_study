package com.wxf.classInit;

/**
 * 类初始化：
 *      1.调用静态变量
 *      2.静态方法都会使
 *      3.使用反射
 *
 * @author WangMaoSong
 * @since 2020-10-14 14:07:25
 */
public class Simple {

    public static int x = 0;

    static {
        System.out.println("Simple has been initialized!");
    }

    public static void printX() {
        System.out.println(x);
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println(Simple.x);
//        Simple.printX();

        System.out.println(Class.forName("com.wxf.classInit.Simple"));
    }
}
