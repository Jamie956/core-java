package gc;

/**
 * 测试 finalize 调用次数，对象的自救
 * 结论：finalize 只会调一次，调用时可以挽救对象
 */
public class FinalizeEscapeGCTest {
    public static FinalizeEscapeGCTest SAVE_HOOK = null;

    public static void isAlive(Object o) {
        if (o != null) {
            System.out.println("yes, i am still alive :)");
        } else {
            System.out.println("no, i am dead :(");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        //GC时重新指向实例
        FinalizeEscapeGCTest.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGCTest();

        //对象设空，触发GC
        SAVE_HOOK = null;
        System.gc();
        //sleep 一会，等待调GC finalize
        Thread.sleep(500);
        // 对象在 final 被挽救了
        isAlive(SAVE_HOOK);

        SAVE_HOOK = null;
        //GC 不会再调 finalize
        System.gc();
        Thread.sleep(500);
    }
}