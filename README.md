 

[基础知识、JDK](/src/main/java/basic)

[设计模式](/src/main/java/pattern)

```
strategy 策略模式
解决多if对应不同算法处理的情况
1.定义一个策略接口，有统一处理的方法名、入参
2.每种策略对应每一个类，实现策略接口，重写方法实现策略处理方法
3.定义一个context类，负责执行策略，实例化context时传入具体的策略，定义一个execute方法执行策略
```


hive-udf    maven打包包含依赖
```
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```



# 基础

泛型

```java

/**
     * 泛型类
     */
static class Point<T> {
  public T x;

  public void setX(T x) {
    this.x = x;
  }
}

/**
     * 泛型方法
     */
public static <E> void test2(E[] x) {
}

/**
     * 泛型方法 extends
     */
public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
  T max = x;
  if (y.compareTo(max) > 0) {
    max = y;
  }
  if (z.compareTo(max) > 0) {
    max = z;
  }
  return max;
}

/**
     * 接口泛型
     */
interface Cat<V> {
  public V pr();
}
```







































