package com.example;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before");
        //注意这里的方法调用，不是用反射
        Object object = proxy.invokeSuper(obj, args);
        System.out.println("after");
        return object;
    }
}
