package gc;

/**
 * 测试大对象直接进入老年代
 * cd path/to/src
 * javac -encoding UTF-8 gc/LargeObjectToOld.java
 * java -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 gc.LargeObjectToOld
 *
 * Heap
 *  PSYoungGen      total 9216K, used 5408K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 *   eden space 8192K, 66% used [0x00000000ff600000,0x00000000ffb48000,0x00000000ffe00000)
 *   from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
 *   to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
 *  ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 *   object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
 *  Metaspace       used 2637K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 280K, capacity 386K, committed 512K, reserved 1048576K
 */
public class LargeObjectToOld {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        //直接分配在老年代中
        byte[] allocation = new byte[4 * _1MB];
    }
}
