package com.wxf.proxy.interceptor;

import java.lang.reflect.Method;

public interface Interceptor {

    public boolean before(Object proxy, Object target, Method method, Object[] args);

    public boolean around(Object proxy, Object target, Method method, Object[] args);

    public boolean after(Object proxy, Object target, Method method, Object[] args);

}
