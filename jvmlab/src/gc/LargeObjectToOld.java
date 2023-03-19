package gc;

/**
 * 测试大对象直接进入老年代
 *
 * 测试VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 *
 * java -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 LargeObjectToOld
 * VM参数解释：
 *
 *
 * log:
 * Heap
 *  PSYoungGen      total 9216K, used 5827K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 8192K, 71% used [0x00000007bf600000,0x00000007bfbb0cb8,0x00000007bfe00000)
 *   from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 *   to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 *  ParOldGen       total 10240K, used 0K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   object space 10240K, 0% used [0x00000007bec00000,0x00000007bec00000,0x00000007bf600000)
 *  Metaspace       used 2972K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 328K, capacity 388K, committed 512K, reserved 1048576K
 */
public class LargeObjectToOld {
//    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        //直接分配在老年代中
//        byte[] allocation = new byte[4 * _1MB];
    }
}
