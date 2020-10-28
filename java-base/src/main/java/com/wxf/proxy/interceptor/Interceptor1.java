package com.wxf.proxy.interceptor;

import java.lang.reflect.Method;

/**
 * 自定义拦截器 1
 *
 * @author WangMaoSong
 * @since 2020-10-28 20:22:57
 */
public class Interceptor1 implements Interceptor {
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【拦截器1】的before方法");
        return true;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【拦截器1】的around方法");
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【拦截器1】的after方法");
    }
}
