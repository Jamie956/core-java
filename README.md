

# 概览

| NAME              | DESC                                                         | TODO                      |
| ----------------- | ------------------------------------------------------------ | ------------------------- |
| algo              | Sort, Data struct, LeetCode                                  | /to maven                 |
| aspectj-demo      | native aspectj                                               | /Review                   |
| cglib-demo        | cglib demo                                                   | /integrate new cglib      |
| compile-processor | 编译java时检测代码的插件                                     | /Documentation hows using |
| design-pattern    | 设计模式                                                     | /                         |
| elastic           | elastic client api test                                      | /                         |
| encrypt           | JDK自带的加密/对称加密/非对称加密/数字摘要                   | /new test class           |
| excel             | apach poi excel api demo                                     | /                         |
| fastjson-demo     |                                                              | /                         |
| freemarker-demo   |                                                              | /                         |
| hadoop-mr         |                                                              | /                         |
| hive-udf          | hive user defined function generate snow ID, compile in one jar by maven | /                         |
| jackson           |                                                              | /                         |
| jvmlab            | JVM GC, Stack and Memory test                                | /                         |
| kafka             | kafka client demo                                            | /                         |
| maven             | maven plugin test                                            |                           |
| multi-task        | 模拟多线程处理任务                                           |                           |
| mybatis           | mybatis test demo                                            |                           |
| netty-start       |                                                              |                           |
| oo                | 面向对象特性                                                 |                           |
| utils             | date, string.. utils                                         |                           |
| word-pdf          | office word convert to pdf, office word convert to html      |                           |
| zk                | zookeeper demo                                               |                           |
|                   |                                                              |                           |
|                   |                                                              |                           |





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
                return null;
            }

            /**
             * 检查方法命名
             */
            @Override
            public Void visitExecutable(ExecutableElement e, Void p) {
                super.visitExecutable(e, p);
                return null;
            }

            /**
             * 检查变量命名
             */
            @Override
            public Void visitVariable(VariableElement e, Void p) {
                return null;
            }
        }
    }
}
```



# encrypt

JDK自带的加密/对称加密/非对称加密/数字摘要



```java
public void desTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
  String rawData = "tomcat";
  String key = "njkasdgh";
  // 算法
  String algorithm = "DES";
  String transformation = "DES";

  SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
  Cipher cipher = Cipher.getInstance(transformation);
  cipher.init(Cipher.ENCRYPT_MODE, keySpec);
  byte[] encryptBytes = cipher.doFinal(rawData.getBytes(StandardCharsets.UTF_8));

  cipher.init(Cipher.DECRYPT_MODE, keySpec);
  byte[] decryptBytes = cipher.doFinal(encryptBytes);
  String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

  Assert.assertEquals(rawData, decryptStr);
}
```



# jvmlab



测试方法栈溢出

```java
private int stackLength = 1;

// 方法递归不断压栈
public void stackLeak() {
  stackLength++;
  stackLeak();
}
```



不断创建线程导致内存溢出异常

```java
public void stackLeafByThread() {
  while (true) {
    new Thread(() -> dontStop()).start();
  }
}
```



不断创建新实例导致堆空间不足

```java
static class OOMObject{}
public static void main(String[] args) {
  List<OOMObject> list = new ArrayList<>();
  while (true) {
    list.add(new OOMObject());
  }
}
```



不断申请直接内存导致溢出

```java
Field unsafeField = Unsafe.class.getDeclaredFields()[0];
unsafeField.setAccessible(true);

Unsafe unsafe = (Unsafe) unsafeField.get(null);
while (true) {
  unsafe.allocateMemory(1024 * 1024);
}
```



# maven



打包依赖 jar

```xml
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <descriptors>
            <descriptor>src/assembly/distribution.xml</descriptor>
        </descriptors>
    </configuration>
</plugin>
```



