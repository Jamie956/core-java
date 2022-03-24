import org.junit.Test;

import java.lang.reflect.Field;

public class Demo1 {
    @Test
    public void t1() throws NoSuchFieldException, IllegalAccessException {
        String s = new String("abc");

        //添加N 行代码，保证 String s = new String("abc") 的s引用的指向不变，最终输出变成abcd
        Field value = s.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(s, "abcd".toCharArray());

        System.out.println(s);
    }

    @Test
    public void t2() {
        //堆 String 对象，创建了2个对象
        String s1 = new String("abc");
        //字符串常量对象
        String s2 = "abc";

        //s1 == s2?
        System.out.println(s1 == s2);

        //先检查常量池，如果不存在就添加到常量池，并返回
        //如果常量池有就返回
        String s3 = s1.intern();
        //s2 == s3?
        System.out.println(s2 == s3);
    }

    @Test
    public void t3() {
        Integer i1 = 100;
        Integer i2 = 100;
        //[-128,127] 范围的Integer从cache获取 integer，时相同的对象
        //i1==i2?
        System.out.println(i1 == i2);

        Integer i3 = 128;
        Integer i4 = 128;
        //范围之外不是同一个对象
        //i3==i4?
        System.out.println(i3 == i4);
    }

    /**
     * String, StringBuilder, StringBuffer 区别
     */
    @Test
    public void t4() {
        //创建了 abc 字符串对象 和 abcd 字符串对象
        String s = "abc";
        s = "abcd";

        //StringBuilder 线程不安全
        StringBuilder stringBuilder = new StringBuilder();

        //StringBuffer 线程安全
        StringBuffer stringBuffer = new StringBuffer();
    }

    @Test
    public void t5() {
        String s1 = new StringBuilder("ax").append("xa").toString();
        //JDK6 intern() 把首次遇到的字符串实例复制到永久代的字符串常量池
        //JDK6 之后 不需要拷贝到永久代，引用的是同一个字符串
        //true，
        System.out.println(s1 == s1.intern());
        //常量池原来就存在java 字符串
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2 == s2.intern());
    }

}
