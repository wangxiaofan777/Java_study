package com.wxf.inventory.service;

import com.wxf.inventory.request.Request;

public interface RequestAsyncProcessorService {

    void process(Request request);
}
