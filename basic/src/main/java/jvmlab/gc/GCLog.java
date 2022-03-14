package jvmlab.gc;

/**
 * 打印GC日志的参数
 * JDK9之前：
 *      查看GC基本信息：-XX:+PrintCGC
 *      查看GC详细信息：-XX:+PrintGCDetails
 *
 * JDK9之后：
 *      查看GC基本信息：-Xlog:gc
 *      查看GC详细信息：-Xlog:gc*
 */
public class GCLog {
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        System.gc();
    }
}
