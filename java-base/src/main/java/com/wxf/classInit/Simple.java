package com.wxf.classInit;

import java.util.Random;

/**
 *
 * 每个包中的类，都只会加载一次
 *
 *
 * 类初始化：
 *      1.调用静态变量
 *      2.静态方法
 *      3.使用反射
 *      4.new 创建对象
 *      5.父子类间的关系：
 *          1）.调用父静态变量时，只会初始化父类
 *          2).调用子静态变量时，父子都会被初始化，先初始化父类，再初始化子类
 *      6.Main方法所在类
 *      7.调用需要运算的常量
 *
 * 注意：常量不能使类发生初始化
 *
 * @author WangMaoSong
 * @since 2020-10-14 14:07:25
 */
public class Simple {

    public static int x = 0;

    public static final int CONSTANCE_X = 10;

    public static final int CONSTANCE_Y = new Random(1000).nextInt();

    static {
        System.out.println("Simple has been initialized!");
    }

    public static void printX() {
        System.out.println(x);
    }


}
