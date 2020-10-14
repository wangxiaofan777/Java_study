package com.wxf.classInit;

/**
 * 测试调用父子静态变量是否会导致对象的初始化
 *
 * 结论：调用父静态变量时，只会初始化父类
 *      调用子静态变量时，父子都会被初始化，先初始化父类，再初始化子类
 *      每个包中的类，都只会加载一次
 *
 * @since 2020-10-14 09:30:39
 */
public class Main {

    public static void main(String[] args) {
//        System.out.println(Child.x);
        System.out.println(Child.y);
        System.out.println("==============");
    }
}
