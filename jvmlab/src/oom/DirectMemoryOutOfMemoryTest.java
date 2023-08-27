package oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 不断申请直接内存导致溢出
 *
 * VM 参数 -XX:+HeapDumpOnOutOfMemoryError -Xmx20M -XX:MaxDirectMemorySize=10M
 * -XX:MaxDirectMemorySize=10M 限制直接内存大小
 * -XX:+HeapDumpOnOutOfMemoryError 导出OOM快照
 * -Xmx20M 限制堆空间
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
