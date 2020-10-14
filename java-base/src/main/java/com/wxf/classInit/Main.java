package com.wxf.classInit;

/**
 * 测试调用父子静态变量是否会导致对象的初始化
 *
 * @since 2020-10-14 09:30:39
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println(Child.x);
//        System.out.println(Child.y);
//        System.out.println("==============");

        //new 初始化
//        new Simple();

        //调用静态变量
//        System.out.println(Simple.x);

        //调用静态方法
//        Simple.printX();

        //反射
//        Class.forName("com.wxf.classInit.Simple");


        //调用静态变量
        System.out.println(Simple.CONSTANCE_X);

        //调用需要运算的静态变量
        System.out.println(Simple.CONSTANCE_Y);
    }
}
