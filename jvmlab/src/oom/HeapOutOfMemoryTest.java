package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 不断创建新实例导致堆空间不足 OutOfMemoryError
 * java -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError HeapOutOfMemoryTest
 */
public class HeapOutOfMemoryTest {
    static class OOMObject{}
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
