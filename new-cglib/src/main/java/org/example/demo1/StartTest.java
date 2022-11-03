package org.example.demo1;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * // The class file of agent class is stored in the local disk, which can be decompiled to view the source code
 * System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/zzs/temp");
 */
public class StartTest{
    @Test
    public void simpleTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback(NoOp.INSTANCE);
        PersonService p = (PersonService) enhancer.create();
        String tim = p.sayHello("Tim");
    }

    @Test
    public void fixedValueTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) () -> "Hello Tom!");

        PersonService proxy = (PersonService) enhancer.create();
        //didn't invoke proxy method, only return fixed value
        String res = proxy.sayHello(null);

        assertEquals("Hello Tom!", res);
    }

    @Test
    public void interceptorTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                //will not invoke target method, only return value
                return "Hello Tom!";
            } else {
                //invoke target method
                return proxy.invokeSuper(obj, args);
            }
        });
        PersonService proxy = (PersonService) enhancer.create();

        String hello = proxy.sayHello(null);
        assertEquals("Hello Tom!", hello);

        int lengthOfName = proxy.lengthOfName("Mary");
        assertEquals(4, lengthOfName);
    }

    @Test
    public void interceptorTest2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback(new MyMethodInterceptor());
        PersonService proxy = (PersonService) enhancer.create();

        String hello = proxy.sayHello("Tom");
        assertEquals("Hello Tom", hello);
    }

    @Test
    public void beanCreatorTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BeanGenerator beanGenerator = new BeanGenerator();

        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();

        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "some string value set by a cglib");

        Method getter = myBean.getClass().getMethod("getName");
        assertEquals("some string value set by a cglib", getter.invoke(myBean));
    }
}
