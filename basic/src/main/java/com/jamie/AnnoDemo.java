package com.jamie;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnoDemo {
    /**
     * 定义一个注解
     * Target ElementType.TYPE 作用对象：类、接口、枚举类
     * Target ElementType.METHOD 作用对象：方法
     * Retention RetentionPolicy.RUNTIME 编译时写到class，虚拟机运行时创建
     * Retention RetentionPolicy.SOURCE 编译时移除
     * Retention RetentionPolicy.CLASS 编译时写到class，虚拟机运行时不创建
     */
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Role {
        //默认方法
        String value();
    }

    @Role(value = "admin")
    static class Student {
    }

    @Role(value = "user")
    static class Teacher {
    }


    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(annoValue(student));

        Teacher teacher = new Teacher();
        System.out.println(annoValue(teacher));
    }

    /**
     * 获取注解的值
     */
    public static String annoValue(Object o) {
        Role role = o.getClass().getAnnotation(Role.class);
        return role.value();
    }
}

