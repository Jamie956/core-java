package com.example.inner_class;

public class MethodInnerClass {
    public void test() {
        // 局部内部类
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
