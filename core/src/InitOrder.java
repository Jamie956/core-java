import org.junit.Test;

public class InitOrder {
    /**
     * 执行顺序
     * 父类静态代码块
     * 子类静态代码块
     * 父类代码块
     * 父类构造函数
     * 子类代码块
     * 子类构造函数
     *
     * 静态代码块只加载一次
     */
    @Test
    public void testOrder() {
        new InitOrderB();
        System.out.println("==静态代码块只加载一次==");
        new InitOrderB();
    }
}

class InitOrderA {
    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类代码块");
    }

    InitOrderA() {
        System.out.println("父类构造函数");
    }
}

class InitOrderB extends InitOrderA {
    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类代码块");
    }

    public InitOrderB() {
        System.out.println("子类构造函数");
    }
}
