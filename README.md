

# Overview

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
| maven             | maven plugin test                                            | /checked                  |
| multi-task        | 模拟多线程处理任务                                           | /                         |
| mybatis           | mybatis test demo                                            | /                         |
| netty-start       |                                                              | /                         |
| oo                | 面向对象特性                                                 | /                         |
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



```xml
<!-- pom.xml -->
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <descriptors>
            <descriptor>src/assembly/distribution.xml</descriptor>
        </descriptors>
    </configuration>
</plugin>

<!-- distribution.xml -->
<dependencySets>
    <dependencySet>
        <useProjectArtifact>false</useProjectArtifact>
        <outputDirectory>/libs</outputDirectory>
        <scope>runtime</scope>
    </dependencySet>
</dependencySets>

```



```xml

<!-- pom.xml -->
<!-- 可以用命令执行 mvn clean assembly:single -->
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <descriptors>
            <!-- 指定 assembly 描述文件的位置 -->
            <descriptor>src/assembly/distribution.xml</descriptor>
        </descriptors>
    </configuration>
</plugin>


<!-- distribution.xml -->
<!-- 文件 README.txt、LICENSE.txt和 NOTICE.txt 打包到jar -->
<files>
    <file>
        <source>README.txt</source>
        <outputDirectory></outputDirectory>
        <filtered>true</filtered>
    </file>
    <file>
        <source>LICENSE.txt</source>
        <outputDirectory></outputDirectory>
    </file>
    <file>
        <source>NOTICE.txt</source>
        <outputDirectory></outputDirectory>
        <filtered>false</filtered>
    </file>
</files>

```



```xml
<!-- pom.xml -->            
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <descriptors>
            <descriptor>src/assembly/distribution.xml</descriptor>
        </descriptors>
    </configuration>
</plugin>

<!-- distribution.xml -->
<formats>
    <format>jar</format>
</formats>

<!-- *.txt扩展名的文件打包到jar，排除README.txt和NOTICE.txt -->
<fileSets>
    <fileSet>
        <directory>${basedir}</directory>
        <includes>
            <include>*.txt</include>
        </includes>
        <excludes>
            <exclude>README.txt</exclude>
            <exclude>NOTICE.txt</exclude>
        </excludes>
    </fileSet>
</fileSets>

```



```xml
<!-- 打包依赖到jar-->
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
        <!-- 打包可执行应用指定入口 -->
        <!-- java -jar target/assembly-jar-with-dep-1.0-SNAPSHOT-jar-with-dependencies.jar
                    java -jar target/assembly-jar-with-dep-1.0-SNAPSHOT.jar
                    报错：target/assembly-jar-with-dep-1.0-SNAPSHOT.jar中没有主清单属性 -->
        <archive>
            <manifest>
                <mainClass>com.cat.Application</mainClass>
            </manifest>
        </archive>
    </configuration>
    <executions>
        <execution>
            <!-- this is used for inheritance merges -->
            <id>make-assembly</id>
            <!-- bind to the packaging phase -->
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
</plugin>

```



# oo

面向对象特性



## Exception

checked exception

```java
public class CheckedException {
    public static void main(String[] args) {
        File file = new File("");
        try {
            // invoke chains: createNewFile() -> createFileExclusively()
            // -> native createFileExclusively() throws IOException

            // Exception class: IOException -> Exception -> Throwable

            // IOException checked exception: must try catch or throws
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

```



unchecked exception

```java
public class UncheckedException {
    public void foo() {


        // Exception class: IllegalArgumentException -> RuntimeException -> Exception -> Throwable

        // happened in runtime with unknown condition, parameters and ...

        throw new IllegalArgumentException();
    }
}
```



throws

```java
public class KeywordThrows {
    // throws: able to throw multiple checked exception and unchecked exception on method name
    public void foo() throws NoSuchFieldException, FileNotFoundException, IllegalArgumentException {
    }
}

```



throw

```java
public class KeywordThrow {
    // throw: throw checked exception and unchecked exception on method body

    public void foo() {
        throw new IllegalArgumentException();
    }
    public void foo2() throws FileNotFoundException {
        throw new FileNotFoundException();
    }
}

```



## Generic

类使用泛型

```java
// 类中出现的泛型T都保持同一个类型
public class GenericClass<T> {
    private T x;

    public void foo(T value) {}
    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }
}
```



接口使用泛型

```java
// 定义泛形型接口
public interface GenericInterface<T> {
    T next();
}
```



方法使用泛型

```java
public class GenericMethod {
    // 定义泛型方法
    public <T> void foo(T x) {

    }
}
```



泛型的继承关系

```java
public class GenericExtends {

    public static void main(String[] args) {
        GenericExtends o = new GenericExtends();
        o.fromArrayToList(new Integer("1"));

        List<Number> dest = new ArrayList<>();
        List<Integer> src = new ArrayList<>();
        // Number super G; Integer extends G
        copy(dest, src);
    }

    // 参数类型T，决定了返回类型 List<T>；参数泛型T 必须是Number 的子类
    public <T extends Number> List<T> fromArrayToList(T a) {
        return null;
    }

    // 参数 dest的泛型 是G 的超类，参数 src 的泛型是G 的子类，所以 dest 的泛型是 src泛型的父类
    public static <G> void copy(List<? super G> dest, List<? extends G> src) {
    }
}
```

