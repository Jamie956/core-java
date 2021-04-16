package com.jamie.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//ElementType.TYPE 作用对象：类、接口、枚举类
//ElementType.METHOD 作用对象：方法
@Target({ElementType.TYPE, ElementType.METHOD})
//写到class，虚拟机运行时创建
@Retention(RetentionPolicy.RUNTIME)
//定义一个注解
public @interface Role {
    //默认方法
    String value();
}