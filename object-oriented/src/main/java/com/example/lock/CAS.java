package com.example.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

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
