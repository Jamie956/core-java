

| NAME              | DESC                                                         | TODO                         |
| ----------------- | ------------------------------------------------------------ | ---------------------------- |
| algo              | Sort, Data struct, LeetCode                                  | /to maven                    |
| aspectj-demo      | native aspectj                                               | /Review                      |
| cglib-demo        | cglib demo                                                   | /integrate aspectj and cglib |
| compile-processor | 编译java时检测代码的插件                                     | /Documentation hows using    |
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



# compile-processor

```java
/**
 * 编译 Class 时作为插件检查 Java 代码
 */
// 可以用"*"表示支持所有Annotations
@SupportedAnnotationTypes("*")
// 只支持JDK 6的Java代码
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NameCheckProcessor extends AbstractProcessor {
    private NameChecker nameChecker;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                nameChecker.checkNames(element);
            }
        }
        return false;
    }

    /**
     * 检查代码规范
     */
    static class NameChecker {
        private final Messager messager;
        NameCheckScanner nameCheckScanner = new NameCheckScanner();

        NameChecker(ProcessingEnvironment processingEnv) {
            this.messager = processingEnv.getMessager();
        }

        public void checkNames(Element element) {
            nameCheckScanner.scan(element);
        }

        private class NameCheckScanner extends ElementScanner6<Void, Void> {
            /**
             * 检查Java类
             */
            @Override
            public Void visitType(TypeElement e, Void p) {
                scan(e.getTypeParameters(), p);
                checkCamelCase(e, true);
                super.visitType(e, p);
                return null;
            }

            /**
             * 检查方法命名
             */
            @Override
            public Void visitExecutable(ExecutableElement e, Void p) {
                if (e.getKind() == METHOD) {
                    Name name = e.getSimpleName();
                    if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                        messager.printMessage(WARNING, "一个普通方法 “" + name + "”不应当与类名重复", e);
                    }
                    checkCamelCase(e, false);
                }
                super.visitExecutable(e, p);
                return null;
            }

            /**
             * 检查变量命名
             */
            @Override
            public Void visitVariable(VariableElement e, Void p) {
                if (e.getKind() == ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
                    checkAllCaps(e);
                } else {
                    checkCamelCase(e, false);
                }
                return null;
            }
        }
    }
}
```



