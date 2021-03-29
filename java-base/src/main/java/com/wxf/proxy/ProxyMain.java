package com.wxf.proxy;

import com.wxf.proxy.impl.HelloWorldImpl;

/**
 * 动态代理测试类
 *
 * @author WangXiaofan777
 * @since 2020-10-27 22:19:18
 */
public class ProxyMain {

    public static void main(String[] args) {
//        testJdkProxy();
        testCGLIBProxy();
    }

    /**
     * 测试CGLIB代理
     */
    public static void testCGLIBProxy() {
        CglibProxyExample cglibProxyExample = new CglibProxyExample();
        HelloWorld object = (HelloWorld) cglibProxyExample.getProxy(HelloWorldImpl.class);
        object.sayHelloWorld();
    }

    /**
     * 测试JDK代理
     */
    public static void testJdkProxy() {
        JdkProxyExample jdkProxyExample = new JdkProxyExample();
        //绑定关系，因为挂载接口HelloWorld下，所以声明代理对象HelloWorld proxy
        HelloWorld proxy = (HelloWorld) jdkProxyExample.bind(new HelloWorldImpl());
        //注意，此时HelloWorld对象已经是一个代理对象，它会进入代理对象的逻辑方法invoke里
        proxy.sayHelloWorld();
    }
}
