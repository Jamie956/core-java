package com.example;

public class ClassInnerClass {
    public static void main(String[] args) {
        // 创建是不需要依赖于外围类的对象
        InnerStaticClass o = new InnerStaticClass();
        String b = o.b;

        // 外部类访问内部类
        InnerNonStaticClass n = new ClassInnerClass().new InnerNonStaticClass();
    }

    public int i = 1;
    public void ofoo() {}

    static class InnerStaticClass {
        // 允许有static属性、方法
        public static String a = "a";
        public String b = "b";

        public static void foo1(){
            // 不能使用任何外围类的非static成员变量和方法
            // Non-static field 'i' cannot be referenced from a static context
//            i = 1;
        }
        public void foo2(){}
    }

    class InnerNonStaticClass {
        // Inner classes cannot have static declarations
//        public static String a = "a";
        public String b = "b";
        // final static 放常量池，可以定义
        public final static String c = "c";

        // Inner classes cannot have static declarations
//        public static void foo1(){}
        public void foo2(){
            // 访问外部类的全局变量
            i = 2;
            // 访问外部类的方法
            ofoo();
        }
    }

    // 局部内部类
    public void test() {
        class InnerClass {
            private String name;
            final static String test = "1";
            public InnerClass(String name) {
                super();
                this.name = name;
            }
            public void say(String str) {
                System.out.println(name+":"+str);
            }
        }
        new InnerClass("test").say("hello");
    }

}
