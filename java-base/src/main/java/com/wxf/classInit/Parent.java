package com.wxf.classInit;

/**
 * 测试调用父子静态变量是否会导致对象的初始化
 *
 * @since 2020-10-14 09:30:39
 */
public class Parent {

    public static int x = 10;

    static {
        System.out.println("Parent has been initialized!");
    }

    public Parent() {
        System.out.println("Parent constructor has been initialized!");
    }

}
