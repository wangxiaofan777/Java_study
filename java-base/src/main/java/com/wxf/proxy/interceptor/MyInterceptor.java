package com.wxf.proxy.interceptor;

import java.lang.reflect.Method;

/**
 * 自定义拦截器
 *
 * @author WangMaoSong
 * @since 2020-10-28 19:36:21
 */
public class MyInterceptor implements Interceptor {
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("反射方法前逻辑");
        return false;
    }

    @Override
    public boolean around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("反射方法后逻辑");
        return false;
    }

    @Override
    public boolean after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("取代了被代理对象的方法");
        return false;
    }
}
