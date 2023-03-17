package oom;

/**
 * 不断创建线程导致内存溢出异常
 * java -Xss2M StackOutOfMemoryTest
 */
public class StackOutOfMemoryTest {
    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeafByThread() {
        while (true) {
            new Thread(() -> dontStop()).start();
        }
    }

    public static void main(String[] args) {
        StackOutOfMemoryTest oom = new StackOutOfMemoryTest();
        oom.stackLeafByThread();
    }
}
