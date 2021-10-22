package com.jamie;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Data
public class Person implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
    private String name;

    public Person() {
        System.out.println("构造对象");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("xml设置变量name");
    }

    //自定义的初始化函数
    public void myInit() {
        System.out.println("执行bean自定义init-method");
    }

    //自定义的销毁方法
    public void myDestroy() {
        System.out.println("执行bean自定义destroy-method");
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("实现DisposableBean的destroy");
    }

    @Override
    public void afterPropertiesSet() {
        // TODO Auto-generated method stub
        System.out.println("实现InitializingBean的afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("实现ApplicationContextAware的setApplicationContext");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("实现BeanFactoryAware的setBeanFactory");
    }

    @Override
    public void setBeanName(String beanName) {
        // TODO Auto-generated method stub
        System.out.println("实现BeanNameAware的setBeanName");
    }
}