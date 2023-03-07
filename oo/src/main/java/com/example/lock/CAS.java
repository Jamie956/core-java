package com.example.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CAS {
    int i = 0;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        CAS o = new CAS();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " call");
            try {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                Unsafe unsafe = (Unsafe) theUnsafe.get(Unsafe.class);
                long offset = unsafe.objectFieldOffset(o.getClass().getDeclaredField("i"));
                Thread.sleep(1000);

                boolean result = unsafe.compareAndSwapInt(o, offset, 0, 1);
                System.out.println(name +" CAS result: "+ result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // 多线程修改共享资源，compare and swap 判断资源是否被修改过
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
