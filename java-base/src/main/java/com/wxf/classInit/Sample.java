package com.wxf.classInit;


/**
 * 类加载中变量初始化
 *
 * @author WangXiaofan777
 * @since 2020-10-14 14:40:41
 */
public class Sample {

    private static Sample instance = new Sample();

    public static int x = 0;
    public static int y;

    private Sample() {
        x++;
        y++;
    }

    public static Sample getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Sample sample = Sample.getInstance();
        System.out.println(sample.x);
        System.out.println(sample.y);
    }

}
