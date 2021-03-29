package com.wxf.model.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 *
 * @author WangXiaofan777
 * @since 2020-10-26 22:29:29
 */
public class ProductList extends Observable {

    //产品列表
    private List<String> productList = new ArrayList<>();

    //唯一实例
    private static ProductList instance = null;

    //构造方法私有化
    private ProductList() {
    }

    /**
     * 取得唯一实例
     *
     * @return 返回产品唯一实例
     */
    public static ProductList getInstance() {
        if (instance == null) {
            instance = new ProductList();
            instance.productList = new ArrayList<>();
        }
        return instance;
    }


    /**
     * 增加观察者(电商接口)
     *
     * @param observer 观察者
     */
    public void addProductListObServer(Observer observer) {
        this.addObserver(observer);
    }

    public void addProduct(String newProduct) {
        productList.add(newProduct);
        System.out.println("产品列表新增了产品：" + newProduct);
        //设置被观察者对象发送变化
        this.setChanged();
        //通知观察者，并传递新产品
        this.notifyObservers(newProduct);
    }


}
