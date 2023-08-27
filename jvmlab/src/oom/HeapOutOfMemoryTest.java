package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM参数 -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m -Xmx20m：限制堆大小
 * +HeapDumpOnOutOfMemoryError：出现内存溢出异常时导出当前内存堆快照
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
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
