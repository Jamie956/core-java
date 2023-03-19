package gc;

/**
 * 测试新生代 Minor GC
 * 内存分配：新生代 10MB，老年代10MB，Eden:Survivor=8:1
 *
 * 测试步骤：
 * cd path/to/src
 * javac -encoding UTF-8 gc/MinorGC.java
 * java -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 gc.MinorGC
 *
 * 测试结果：
 * Heap
 *  PSYoungGen      total 9216K, used 7456K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 *   eden space 8192K, 91% used [0x00000000ff600000,0x00000000ffd48038,0x00000000ffe00000)
 *   from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
 *   to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
 *  ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 *   object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
 *  Metaspace       used 2637K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 280K, capacity 386K, committed 512K, reserved 1048576K
 *
 */
public class MinorGCTest {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        // 出现一次Minor GC
        allocation4 = new byte[4 * _1MB];
    }
}
