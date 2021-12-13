
# 单例模式

定义：在当前进程中，通过单例模式创建的类有且只有一个实例



特点：

- 在Java应用中，单例模式能保证在一个JVM中，该对象只有一个实例存在
- 构造器必须是私有的，外部类无法通过调用构造器方法创建该实例
- 没有公开的set方法，外部类无法调用set方法创建该实例
- 提供一个公开的get方法获取唯一的这个实例



好处

- 某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销
- 省去了new操作符，降低了系统内存的使用频率，减轻GC压力
- 系统中某些类，如spring里的controller，控制着处理流程，如果该类可以创建多个的话，系统完全乱了
- 避免了对资源的重复占用



**为什么不用静态方法而用单例模式**？

两者其实都能实现我们加载的最终目的，但是他们一个是基于对象，一个是面向对象的，就像我们不面向对象也能解决问题一样，面向对象的代码提供一个更好的编程思想。

如果一个方法和他所在类的实例对象无关，那么它就应该是静态的，反之他就应该是非静态的。如果我们确实应该使用非静态的方法，但是在创建类时又确实只需要维护一份实例时，就需要用单例模式了。



## 饿汉式

初始化创建

- 场景：热点数据预加载

```java
package com.jamie.design.pattern.singleton;

/**
 * 饿汉式
 */
public class EagerSingleton {
    private final static EagerSingleton INSTANCE = new EagerSingleton();

    /**
     * 私有构造，外部类无法创建实例
     */
    private EagerSingleton() {
    }

    /**
     * 静态方法get
     */
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}

```





## 懒汉式

使用时创建



### 线程不安全版本

```java
package com.jamie.design.pattern.singleton;

/**
 * 懒汉式：线程不安全版本，不加锁有线程安全问题
 */
public class LazySingleton1 {
    private static LazySingleton1 INSTANCE;

    private LazySingleton1() {
    }

    public static LazySingleton1 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton1();
        }
        return INSTANCE;
    }
}

```



### 加锁版本

```java
package com.jamie.design.pattern.singleton;

/**
 * 懒汉式：加锁
 */
public class LazySingleton2 {
    private static LazySingleton2 INSTANCE;

    private LazySingleton2() {
    }

    public static synchronized LazySingleton2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton2();
        }
        return INSTANCE;
    }
}

```



### 双检锁版本

```java
package com.jamie.design.pattern.singleton;

/**
 * 懒汉式：双检锁
 */
public class LazySingleton3 {
    private static LazySingleton3 INSTANCE;

    private LazySingleton3() {
    }

    public static LazySingleton3 getInstance() {
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (INSTANCE == null) {
            //同步块，线程安全的创建实例
            synchronized (LazySingleton3.class) {
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if (INSTANCE == null) {
                    //在Java指令中创建对象和赋值操作是分开进行的，也就是说instance = new Singleton();语句是分两步执行的。
                    //会有指令重排问题
                    INSTANCE = new LazySingleton3();
                }
            }
        }
        return INSTANCE;
    }
}
```



### 实例内存可见版本

```java
package com.jamie.design.pattern.singleton;

/**
 * 懒汉式：双检锁+实例内存可见
 */
public class LazySingleton4 {
    //通过volatile修饰的变量，不会被线程本地缓存，所有线程对该对象的读写都会第一时间同步到主内存，从而保证多个线程间该对象的准确性
    private volatile static LazySingleton4 INSTANCE;

    private LazySingleton4() {
    }

    public static LazySingleton4 getInstance() {
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (INSTANCE == null) {
            //同步块，线程安全的创建实例
            synchronized (LazySingleton4.class) {
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton4();
                }
            }
        }
        return INSTANCE;
    }
}
```



### 静态内部类版本

```java
package com.jamie.design.pattern.singleton;

/**
 * 懒汉式：静态内部类
 * 来维护单例的实现，JVM内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的。
 * 这样当我们第一次调用getInstance的时候，JVM能够帮我们保证instance只被创建一次，并且会保证把赋值给instance的内存初始化完毕
 */
public class LazySingleton5 {
    /* 私有构造方法，防止被实例化 */
    private LazySingleton5() {
        System.out.println("LazySingleton5 被创建了");
    }

    /* 此处使用一个内部类来维护单例 */
    private static class SingletonFactory {
        private static LazySingleton5 instance = new LazySingleton5();
    }

    /* 获取实例 */
    public static LazySingleton5 getInstance() {
        return SingletonFactory.instance;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getInstance();
    }
}

```



## 单例最佳实践

```java
package com.jamie.design.pattern.singleton;

/**
 * 使用枚举来实现单实例控制会更加简洁，而且JVM从根本上提供保障，绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式。
 */
public enum Singleton {
    /**
     * 定义一个枚举的元素，它就代表了Singleton的一个实例。
     */
    Instance,
    Hi
}

```


