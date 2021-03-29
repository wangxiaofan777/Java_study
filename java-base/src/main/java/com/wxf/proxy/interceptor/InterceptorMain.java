package com.wxf.proxy.interceptor;

import com.wxf.proxy.HelloWorld;
import com.wxf.proxy.impl.HelloWorldImpl;

/**
 * Interceptor测试方法
 *
 * @author WangXiaofan777
 * @since 2020-10-28 20:06:46
 */
public class InterceptorMain {

    public static void main(String[] args) {
        HelloWorld helloWorld = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),
                "com.wxf.proxy.interceptor.MyInterceptor");
        helloWorld.sayHelloWorld();
    }
}
