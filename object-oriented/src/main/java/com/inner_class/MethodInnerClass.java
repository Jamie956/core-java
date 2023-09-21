package com.inner_class;

public class MethodInnerClass {
    public void foo() {
        // 局部内部类
        class InnerClass {
            private String name;
            final static String test = "1";

            public InnerClass(String name) {
                super();
                this.name = name;
            }

            public void say(String str) {
                System.out.println(name + ":" + str);
            }
        }
        new InnerClass("tim").say("hello");
    }
}
