package com.wxf.instance;

/**
 * 双检索单例
 *
 * @since 2020-10-14 10:16:07
 */
public class DoubleCheck {

    private static DoubleCheck instance = null;

    //构造方法私有化
    private DoubleCheck() {

    }

    public static DoubleCheck getInstance() {
        if (instance == null) {
            synchronized (DoubleCheck.class) {
                if (instance == null)
                    instance = new DoubleCheck();
            }
        }
        return instance;
    }
}
