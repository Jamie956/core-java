package com.cat.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                // 执行目标对象方法
                method.invoke(target, args);
                System.out.println("after");
                return null;
            }
        });
    }

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        System.out.println(realSubject.getClass());
        Subject subject = (Subject) new ProxyFactory(realSubject).getProxyInstance();
        System.out.println(subject.getClass());
        subject.request();
    }
}