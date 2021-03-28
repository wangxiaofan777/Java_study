package com.wxf.inventory.request;

public interface Request {

    void process();

    Integer getProductId();

    boolean isForceRefresh();
}
