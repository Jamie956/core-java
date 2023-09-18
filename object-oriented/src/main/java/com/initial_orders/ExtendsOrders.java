package com.initial_orders;

// 父子类代码块和构造函数的执行顺序
public class ExtendsOrders {
    public static void main(String[] args) {
        //静态代码块只加载一次
        new ChildOrders();
        System.out.println("-----------------再次创建子类-----------------");
        new ChildOrders();
    }
}

class ParentOrders {
    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类代码块");
    }

    ParentOrders() {
        System.out.println("父类构造函数");
    }
}

class ChildOrders extends ParentOrders {
    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类代码块");
    }

    public ChildOrders() {
        System.out.println("子类构造函数");
    }
}
