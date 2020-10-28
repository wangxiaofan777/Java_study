package com.wxf.proxy.interceptor;

import java.lang.reflect.Method;

/**
 * 自定义拦截器 3
 *
 * @author WangMaoSong
 * @since 2020-10-28 20:22:57
 */
public class Interceptor3 implements Interceptor{
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【拦截器3】的before方法");
        return true;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【拦截器3】的around方法");
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【拦截器3】的after方法");
    }
}
