package com.keyword_synchronized;

public class SyncObject {

    private static final Runnable run = () -> {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    private Runnable runWithSync = () -> {
        //对象锁
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable runWithSync2 = () -> {
        //对象锁
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable syncClass = () -> {
        //类锁
        synchronized (SyncObject.class) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    //相当于类锁
    public static synchronized void staticSync() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void staticSync2() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程、无锁，线程乱序执行
     */
    public static void noSyncTest() {
        new Thread(run).start();
        new Thread(run).start();
    }

    /**
     * 多线程执行 同一实例的 同一个带this锁方法，顺序执行
     */
    public void thisSyncTest() {
        new Thread(runWithSync).start();
        new Thread(runWithSync).start();
    }

    /**
     * 多线程执行 不同实例的 同一个带锁的方法，乱序执行
     */
    public static void thisSync2Test() {
        new Thread(new SyncObject().runWithSync).start();
        new Thread(new SyncObject().runWithSync).start();
    }

    /**
     * 多线程执行 同一个实例的 带锁方法和不带锁方法，乱序执行
     */
    public void thisSyncNoSync() {
        new Thread(run).start();
        new Thread(runWithSync).start();
    }

    /**
     * 多线程执行 同一实例的 带锁的不同方法，顺序执行
     */
    public void twoThisSync() {
        new Thread(runWithSync).start();
        new Thread(runWithSync2).start();
    }

    /**
     * 多线程执行 带锁的静态方法，顺序执行
     * 类锁/全局锁
     */
    public void staticSyncTest() {
        new Thread(() -> SyncObject.staticSync()).start();
        new Thread(() -> SyncObject.staticSync2()).start();
    }

    /**
     * 多线程执行 实例的带锁方法 和静态带锁方法，乱序执行
     */
    public void thisSyncStaticSyncTest() {
        SyncObject x = new SyncObject();
        new Thread(runWithSync2).start();
        new Thread(() -> SyncObject.staticSync()).start();
    }

    /**
     * 类锁，顺序执行
     */
    public void syncClassTest() {
        new Thread(new SyncObject().syncClass).start();
        new Thread(new SyncObject().syncClass).start();
    }

    public static void main(String[] args) {
//        noSyncTest();
//        new SyncObject().thisSyncTest();
//        thisSync2Test();
//        new SyncObject().thisSyncNoSync();
//        new SyncObject().twoThisSync();
//        new SyncObject().staticSyncTest();
//        new SyncObject().thisSyncStaticSyncTest();
        new SyncObject().twoThisSync();
    }
}
