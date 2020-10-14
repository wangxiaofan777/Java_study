package com.wxf.classInit;

/**
 * 测试调用父子静态变量是否会导致对象的初始化
 *
 * @since 2020-10-14 09:30:39
 */
public class Parent {

    public static int x = 10;

    public Parent() {
        System.out.println("Parent class has been initialized!");
    }

}
