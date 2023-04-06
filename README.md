

# Overview

| NAME              | DESC                                                         |
| ----------------- | ------------------------------------------------------------ |
| algo              | Sort, Data struct, LeetCode                                  |
| aspectj-demo      | native aspectj                                               |
| cglib-demo        | cglib demo                                                   |
| compile-processor | 编译java时检测代码的插件                                     |
| design-pattern    | 设计模式                                                     |
| elastic           | elastic client api test                                      |
| encrypt           | JDK自带的加密/对称加密/非对称加密/数字摘要                   |
| excel             | apach poi excel api demo                                     |
| fastjson-demo     |                                                              |
| freemarker-demo   |                                                              |
| hadoop-mr         |                                                              |
| hive-udf          | hive user defined function generate snow ID, compile in one jar by maven |
| jackson           |                                                              |
| jvmlab            | JVM GC, Stack and Memory test                                |
| kafka             | kafka client demo                                            |
| maven             | maven plugin test                                            |
| multi-task        | 模拟多线程处理任务                                           |
| mybatis           | mybatis test demo                                            |
| netty-start       |                                                              |
| oo                | 面向对象特性                                                 |
| utils             | date, string.. utils                                         |
| word-pdf          | office word convert to pdf, office word convert to html      |
| zk                | zookeeper demo                                               |
|                   |                                                              |
|                   |                                                              |





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



## Abstract

```java
public abstract class KeywordAbstract {
    // abstract class 可以定义变量
    private String s;
    // abstract class 可以定义静态变量
    private static String b;

    // abstract class 可以定义构造方法
    KeywordAbstract() {}

    // abstract class 可以定义抽象方法
    // 如果一个类有抽象方法，就一定是抽象类
    abstract void foo();

    // abstract class 可以定义具体方法
    public void concrete() {}

    // abstract class 可以定义静态方法
    public static void concreteS() {}

    public static void main(String[] args) {
        // abstract class 不能实例化
//        KeywordAbstract o = new KeywordAbstract();
    }
}
```



## Final

不能继承 final 修饰的类

```java
// 不能继承 final 修饰的类
public class ExtendFinalClass
//        extends KeywordFinalClass
{ }
```



final 修饰的类

```java
// final 修饰的类
public final class KeywordFinalClass {
    // final 类的非final 全局变量可以修改，没有被隐式修饰为final
    public String s = "aa";

    // final 类的非final 方法 隐式指定为 final
    public void foo() {}

    public static void main(String[] args) {
        KeywordFinalClass o = new KeywordFinalClass();
        o.s = "update";
        System.out.println(o.s);
    }
}
```



父类 final 方法

```java
// 父类 final 方法
public class KeywordFinalMethod extends KeywordFinalMethodParent{
    // 父类 final 方法不能被重写
//    public void foo() {}
}

class KeywordFinalMethodParent {
    public final void foo() {
    }
}
```



final 修饰变量

```java
// final 修饰变量
public class KeywordFinalVar {
    public final String a = "a";
    public final Object[] arr = {1,2};

    public static void main(String[] args) {
        KeywordFinalVar o = new KeywordFinalVar();
        // final 修饰的基本数据类型不可修改
//        o.a = "aaa";

        // final 修饰的引用类型，对象堆内存的值可变
        o.arr[0] = 3;
        System.out.println(o.arr[0]);

        // final 修饰的引用类型，地址不可变
        Object[] arr2 = {5,6};
//        o.arr = arr2;
    }
}
```



## Interface

接口

```java
public interface InterfaceDefined {
    // 接口不能实例化，没有构造方法
//    InterfaceDefined ();

    void foo();

    // public 方法没有方法体
//    void bar() {};

    public void foo1();

    // default 同一个包的类可访问
    default void foo3() {}

}
```



实现接口

```java
// implements 接口需要实现接口的全部方法
public class implementInterface implements InterfaceDefined {

    @Override
    public void foo() {

    }

    @Override
    public void foo1() {

    }

    @Override
    public void foo3() {

    }
}
```



## Lock



CAS

```java
// CAS 模拟乐观锁
public class CAS {
    int count = 0;

    // unsafe CAS 在多线程下同时修变量count，只有内存值与 expected 值相等的线程能成功修改 count，即取得了锁
    public boolean cas(int expected, int x) {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafe.get(Unsafe.class);
            long offset = unsafe.objectFieldOffset(this.getClass().getDeclaredField("count"));
            // expected: 当前值的预期值，如果内存的值不是预期值，说明被其他线程修改了，就返回 false
            // x: 要修改成的值
            return unsafe.compareAndSwapInt(this, offset, expected, x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean lock() {
        return cas(0, 1);
    }

    public int getCount() {
        return count;
    }

    public boolean isLock() {
        return count != 0;
    }

    public boolean release() {
        return cas(1, 0);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        CAS o = new CAS();

        Runnable runnable = () -> {
            boolean success = o.lock();
            System.out.printf("thread=%s, count=%s, success=%s%n", Thread.currentThread().getName(), o.getCount(), success);
        };

        // 多线程修改共享资源，cas判断资源是否被修改过
        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }

    }
}
```



ReentrantLock 测试锁重入

```java
// ReentrantLock 测试锁重入
public class EnterLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " call");
            try {
                lock.lock();
                System.out.printf("1 tread=%s, holdCount=%s%n", name, lock.getHoldCount());
                // 一个线程获取某个对象的锁，可再次获取此对象的锁
                lock.lock();
                System.out.printf("2 tread=%s, holdCount=%s%n", name, lock.getHoldCount());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                lock.unlock();
            }
        };

        new Thread(runnable).start();
    }
}
```



ReentrantLock 测试独占锁/悲观锁

```java
// ReentrantLock 测试独占锁/悲观锁
public class ExcludeLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.printf("thread=%s, before get lock%n", name);
            try {
                lock.lock();
                System.out.printf("thread=%s, get lock%n", name);

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.printf("thread=%s, release lock%n", name);

            }
        };

        // 线程独占资源，其他线程访问这个资源时阻塞，直到资源被线程释放其他线程才能竞争资源
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
```



公平锁按先后获取锁

```java
// 公平锁按先后获取锁
public class FairLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(finalI + " before get lock");
                    lock.lock();
                    System.out.println(finalI + " get lock");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }
}
```



非公平锁，先来的未必先抢到锁

```java
// 非公平锁，先来的未必先抢到锁
public class NonFairLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(false);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(finalI + " before get lock");
                    lock.lock();
                    System.out.println(finalI + " get lock");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }
}
```



测试读写锁，多线程可以同时获取读锁，写锁只能被线程独占

```java
// 测试读写锁，多线程可以同时获取读锁，写锁只能被线程独占
public class ReadWriteLock {
    public static void main(String[] args) {
        //Suspend Thread
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        Runnable readTask = () -> {
            //Suspend Thread
            ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
            //Suspend Thread
            readLock.lock();
            try {
                for (int i = 1; i < 11; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " read " + i + "s");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        };

        Runnable writeTask = () -> {
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
            //Suspend Thread
            writeLock.lock();
            try {
                for (int i = 1; i < 11; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " write " + i + "s");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        };
        
        new Thread(readTask).start();
        new Thread(readTask).start();
        new Thread(readTask).start();

        new Thread(writeTask).start();
        new Thread(writeTask).start();
        new Thread(writeTask).start();
    }
}
```



共享锁，多个线程可以同时获取锁

```java
// 共享锁，多个线程可以同时获取锁
public class ShareLock {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        Runnable runnable = () -> {
            try {
                semaphore.acquire();
                for (int i = 1; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " read " + i + "s");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
```



简单自旋锁

```java
// 简单自旋锁
public class SpinLock {
    /**
     * 使用Owner Thread作为同步状态
     */
    private AtomicReference<Thread> sign = new AtomicReference<>();

    /**
     * reentrant count of a thread, no need to be volatile
     */
    public void lock() throws InterruptedException {
        Thread t = Thread.currentThread();
        // 自旋
        while (!sign.compareAndSet(null, t)) {
            System.out.println(t.getName() + " spin");
            Thread.sleep(1000);
        }
    }

    public void unlock() {
        Thread t = Thread.currentThread();
        sign.compareAndSet(t, null);
    }

    public static void main(String[] args) {
        SpinLock lock = new SpinLock();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            try {
                lock.lock();
                System.out.println(name + " obtain lock");
                for (int i = 1; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(name + " doing task " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(name + " unlock");
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
```



非静态内部类特性

```java
public class DefinedInnerNonStaticClass {
    public static void main(String[] args) {
        // 外部类访问内部类
        InnerNonStaticClass n = new DefinedInnerNonStaticClass().new InnerNonStaticClass();
    }

    public int i = 1;
    public void ofoo() {}

    class InnerNonStaticClass {
        // 非静态内部类不能有静态变量
//        public static String a = "a";
        public String b = "b";
        // 非静态内部类可以定义 final static，放常量池
        public final static String c = "c";

        // 非静态内部类不能有静态方法
//        public static void foo1(){}

        // 非静态内部类访问外部类的非静态变量
        public int df = i;

        public void foo2(){
            // 非静态内部类访问外部类非静态方法
            ofoo();
        }
    }
}
```



静态内部类特性

```java
// 静态内部类特性
public class DefinedInnerStaticClass {

    public static void main(String[] args) {
        // 可以直接创建静态内部类，不需要先创建外部类
        InnerStaticClass o = new InnerStaticClass();
        String b = o.b;
    }

    public int i = 1;
    public void ofoo() {}

    static class InnerStaticClass {
        // 静态内部类可以有静态全局变量
        public static String a = "a";
        public String b = "b";
        // 静态内部类不能使用任何外部类的非静态变量
//        public int d = i;

        // 静态内部类可以有静态方法
        public static void foo1(){
        }

        // 静态内部类可以有非静态方法
        public void foo2(){}
    }
}
```



局部内部类

```java
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
```



todo ColorEnum