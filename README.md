

| NAME              | DESC                                                         | TODO                         |
| ----------------- | ------------------------------------------------------------ | ---------------------------- |
| algo              | Sort, Data struct, LeetCode                                  | /to maven                    |
| aspectj-demo      | native aspectj                                               | /Review                      |
| cglib-demo        | cglib demo                                                   | /integrate aspectj and cglib |
| compile-processor | 编译java时检测代码的插件                                     | Documentation hows using     |
| design-pattern    | 设计模式                                                     | /                            |
| elastic           | elastic client api test                                      | /                            |
| encrypt           | java 加密                                                    | new test class               |
| excel             | apach poi excel api demo                                     | /                            |
| fastjson-demo     |                                                              | new test class               |
| freemarker-demo   |                                                              | new test class               |
| hadoop-mr         |                                                              | /                            |
| hive-udf          | hive user defined function generate snow ID, compile in one jar by maven | /                            |
| jackson           |                                                              | /                            |
| jvmlab            | JVM GC, Stack and Memory test                                |                              |
| kafka             | kafka client demo                                            |                              |
| maven             | maven plugin test                                            |                              |
| multi-task        | 模拟多线程处理任务                                           |                              |
| mybatis           | mybatis test demo                                            |                              |
| netty-start       |                                                              |                              |
| oo                | 面向对象特性                                                 |                              |
| utils             | date, string.. utils                                         |                              |
| word-pdf          | office word convert to pdf, office word convert to html      |                              |
| zk                | zookeeper demo                                               |                              |
|                   |                                                              |                              |
|                   |                                                              |                              |





# algo

包含

- Sort：排序
- Data struct：树数据结构
- LeetCode



# aspectj-demo

native aspectj api demo



```java
public aspect AccountAspect {
    /*
    defined pointcut
    callWithDraw123: pointcut name, custom defined
    call(): 参数可以是目标方法 或者是 表达式
    args(): ?
    target(): ?
     */
    pointcut callWithDraw123(int amount, Account acc):
            call(boolean withdraw(int)) && args(amount) && target(acc);

    // before advice, call if pointcut true
    before(int amount, Account acc): callWithDraw123(amount, acc) {
        System.out.println("before");
    }

    // around advice, call if pointcut true
    boolean around(int amount, Account acc): callWithDraw123(amount, acc) {
        System.out.println("around");
        return proceed(amount, acc);
    }

    // after advice, call if pointcut true
    after(int amount, Account balance): callWithDraw123(amount, balance) {
        System.out.println("after");
    }
}
```



# cglib-demo

cglib api demo



```java
Enhancer enhancer = new Enhancer();
enhancer.setSuperclass(TargetObject.class);
enhancer.setCallback(new MyInterceptor());

TargetObject o = (TargetObject) enhancer.create();
o.request();
```







