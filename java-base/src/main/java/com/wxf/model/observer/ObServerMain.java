package com.wxf.model.observer;

/**
 * 观察者模式测试
 *
 * @author WangXiaofan777
 * @since 2020-10-26 23:06:33
 */
public class ObServerMain {

    public static void main(String[] args) {
        JDObServer jdObServer = new JDObServer();
        TBObServer tbObServer = new TBObServer();
        ProductList instance = ProductList.getInstance();
        instance.addObserver(jdObServer);
        instance.addObserver(tbObServer);
        instance.addProduct("新增产品123");
    }
}
