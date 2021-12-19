package com.jamie.design.pattern.singleton;

/**
 * 使用枚举来实现单实例控制会更加简洁，而且JVM从根本上提供保障，
 * 绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式。
 */
public enum Singleton {
    /**
     * 定义一个枚举的元素，它就代表了Singleton的一个实例。
     */
    Instance,
    Hi
}
