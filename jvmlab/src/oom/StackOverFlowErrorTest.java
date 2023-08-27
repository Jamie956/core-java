package oom;

/**
 * VM参数 -Xss161k
 * -Xss161k：限制栈内存空间
 *
 * Exception in thread "main" java.lang.StackOverflowError
 */
public class StackOverFlowErrorTest {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverFlowErrorTest oom = new StackOverFlowErrorTest();
        try {
            oom.stackLeak();
        } catch (StackOverflowError e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
