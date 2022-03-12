package jvmlab;

import sun.misc.Unsafe;

import java.lang.reflect.Field;


/**
 * 直接内存溢出，抛出OOM
 * -XX:+HeapDumpOnOutOfMemoryError -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);

        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024);
        }
    }
}
