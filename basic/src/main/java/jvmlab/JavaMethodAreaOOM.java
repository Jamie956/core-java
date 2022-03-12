package jvmlab;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.misc.Unsafe;

import java.lang.reflect.Method;

/**
 * 测试 CGLib 使得方法区OOM
 *
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * Java8后无永久代，不会抛异常
 *
 * -XX:MetaspaceSize=12M -XX:MaxMetaspaceSize=12M
 * 抛出元数据区OOM，说明Java8不再使用方法区，Class 放在元空间
 */
public class JavaMethodAreaOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }
}
