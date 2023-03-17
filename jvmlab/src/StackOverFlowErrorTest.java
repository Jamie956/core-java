/**
 * 测试方法栈溢出
 * <p>
 * 设置参数限制栈空间，运行 class 的方式
 * 1）IDEA 设置 VM options：-Xss160k
 * 2）直接 java 命令执行：java -Xss160k StackOverFlowErrorTest
 */
public class StackOverFlowErrorTest {
    private int stackLength = 1;

    // 方法递归不断压栈
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
