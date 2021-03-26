package com.wxf.inventory.service;

import com.wxf.inventory.request.Request;

public interface RequestAsyncProcessorService {

    public void process(Request request);
}
