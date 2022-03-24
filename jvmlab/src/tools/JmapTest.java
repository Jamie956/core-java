package tools;

/**
 * 内存映像工具：jmap [ option ] vmid
 * jmap -dump:format=b,file=eclipse.bin 8888
 * 导出堆快照后，使用 jhat 命令分析：jhat eclipse.bin
 */
public class JmapTest {
    public static void main(String[] args) throws InterruptedException {
        int _1MB = 1024 * 1024;
        while (true) {
            Thread.sleep(500);
            byte[] bigSize = new byte[2 * _1MB];
        }
    }
}
