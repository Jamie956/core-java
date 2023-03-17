package oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 不断申请直接内存导致溢出
 * java -XX:+HeapDumpOnOutOfMemoryError -Xmx20M -XX:MaxDirectMemorySize=10M DirectMemoryOutOfMemoryTest
 */
public class DirectMemoryOutOfMemoryTest {
    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);

        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024);
        }
    }
}
