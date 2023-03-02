package com.example;

public class SuperObject {
    public static void main(String[] args) {
        new ChildS().bar();
    }
}

class ParentS {
    public String s = "str";
    public void foo() {
        System.out.println("parent");
    }
}

class ChildS extends ParentS{
    public ChildS() {
        // 实例化父类
        super();
    }

    public void bar() {
        // super 表示父类对象，可以调父类属性和方法
        System.out.println(super.s);
        super.foo();
    }
}