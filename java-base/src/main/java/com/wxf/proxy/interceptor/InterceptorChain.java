package com.wxf.proxy.interceptor;

import com.wxf.proxy.HelloWorld;
import com.wxf.proxy.impl.HelloWorldImpl;

/**
 * 责任链模式
 *
 * @author WangMaoSong
 * @since 2020-10-28 20:25:49
 */
public class InterceptorChain {

    public static void main(String[] args) {
        HelloWorld proxy1 = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),
                "com.wxf.proxy.interceptor.Interceptor1");
        HelloWorld proxy2 = (HelloWorld) InterceptorJdkProxy.bind(proxy1,
                "com.wxf.proxy.interceptor.Interceptor2");
        HelloWorld proxy3 = (HelloWorld) InterceptorJdkProxy.bind(proxy2,
                "com.wxf.proxy.interceptor.Interceptor3");
        proxy3.sayHelloWorld();
    }
}
