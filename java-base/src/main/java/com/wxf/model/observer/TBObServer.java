package com.wxf.model.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * TB观察者
 *
 * @author WangMaoSong
 * @since 2020-10-26 23:05:17
 */
public class TBObServer implements Observer {
    @Override
    public void update(Observable o, Object product) {
        String newProduct = String.valueOf(product);
        System.out.println("发送新产品【"+newProduct+"】，同步到TB商城");
    }
}
