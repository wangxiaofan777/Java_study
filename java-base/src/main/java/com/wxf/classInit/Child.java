package com.wxf.classInit;

/**
 * 测试调用父子静态变量是否会导致对象的初始化
 *
 * @since 2020-10-14 09:30:39
 */
public class Child extends Parent {

    public static int y = 20;

    public Child() {
        System.out.println("Child class has been initialized!");
    }


}
