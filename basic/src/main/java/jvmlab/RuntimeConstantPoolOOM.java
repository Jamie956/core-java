package jvmlab;

import net.sf.cglib.proxy.Enhancer;

import java.util.HashSet;
import java.util.Set;

/**
 * 验证常量池是否在永久代
 * 如果常量池在永久代，就会抛出OOM，Java7以上的版本不会抛出异常，因为常量池移到堆中
 * -XX:PermSize=6M -XX:MaxPermSize=6M
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
