package com.wxf.instance;

/**
 * 懒汉式单例
 *
 * @since 2020-10-14 10:14:20
 */
public class LHan {

    private static LHan instance = null;

    //构造方法是优化
    private LHan() {

    }

    public static LHan getInstance() {
        if (instance == null)
            instance = new LHan();
        return instance;
    }
}
