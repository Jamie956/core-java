package jvmlab.gc;

/**
 * 打印GC日志的参数
 * JDK9之前：
 *      查看GC基本信息：-XX:+PrintCGC
 *      查看GC详细信息：-XX:+PrintGCDetails
 *      查看GC前后的堆、方法区可用容量变化：-XX:+PrintHeapAtGC
 *      查看GC过程中用户线程并发时间以及停顿的时间：-XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime
 *      查看收集器 Ergonomics 机制自动调节的相关信息：-XX:+PrintAdaptiveSizePolicy
 *      查看熬过收集后剩余对象的年龄分布信息：-XX:+PrintTenuringDistribution
 *
 * JDK9之后：
 *      查看GC基本信息：-Xlog:gc
 *      查看GC详细信息：-Xlog:gc*
 *      查看GC前后的堆、方法区可用容量变化：-Xlog:gc+heap=debug
 *      查看GC过程中用户线程并发时间以及停顿的时间：-Xlog:safepoint
 *      查看收集器 Ergonomics 机制自动调节的相关信息：-Xlog:gc+ergo*=trace
 *      查看熬过收集后剩余对象的年龄分布信息：-Xlog:gc+age=trace
 */
public class GCLog {
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        objA = null;
        System.gc();
    }
}
