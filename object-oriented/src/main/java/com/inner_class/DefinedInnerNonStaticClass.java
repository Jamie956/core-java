package com.inner_class;


public class DefinedInnerNonStaticClass {
    public static void main(String[] args) {
        // 外部类访问内部类
        InnerNonStaticClass n = new DefinedInnerNonStaticClass().new InnerNonStaticClass();
    }

    public int outerNonStaticVar = 1;
    public static int outerStaticVar = 1;
    public void outerNonStaticMethod() {}
    public static void outerStaticMethod() {}

    // 非静态内部类
    class InnerNonStaticClass {
        // 不能定义静态变量
//        public static int innerStaticVar = 1;
        public int innerNonStaticVar = 1;
        // 可以定义 final static 变量，放常量池
        public final static int innerFinalStaticVar = 1;

        // 可以访问外部类的非静态变量
        public int innerVar = outerNonStaticVar;

        // 不能定义静态方法
//        public static void innerStaticMethod(){}

        public void innerNonStaticMethod(){
            // 可以访问外部类非静态方法
            outerNonStaticMethod();
        }
    }
}
