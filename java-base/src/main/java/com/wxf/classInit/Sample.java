package com.wxf.classInit;


/**
 * 类加载中变量初始化
 *
 * @author WangMaoSong
 * @since 2020-10-14 14:40:41
 */
public class Sample {

    private Sample() {

    }

    public static int x = 0;
    public static int y;

    static {
        x++;
        y++;
    }

    private static Sample instance = new Sample();

    public static Sample getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Sample.getInstance().x);
        System.out.println(Sample.getInstance().y);
    }
}
