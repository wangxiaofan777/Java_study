package com.wxf.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理
 *
 * @author WangXiaofan777
 * @since 2020-10-27 22:33:23
 */
public class CglibProxyExample implements MethodInterceptor {

    /**
     * 生成代理对象
     *
     * @param clazz Class类
     * @return Class类的CGLIB代理对象
     */
    public Object getProxy(Class clazz) {
        //CGLIB enhancer增强类对象
        Enhancer enhancer = new Enhancer();
        //设置增强类
        enhancer.setSuperclass(clazz);
        //定义代理逻辑对象，要求当前对象实现MethodInterceptor方法
        enhancer.setCallback(this);
        return enhancer.create();
    }


    /**
     * 代理逻辑方法
     *
     * @param proxy       代理对象
     * @param method      方法
     * @param args        方法参数
     * @param methodProxy 方法代理
     * @return 代理逻辑返回
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用真实对象前");
        Object result = methodProxy.invokeSuper(proxy, args);
        System.out.println("调用真实对象后");
        return result;
    }
}
