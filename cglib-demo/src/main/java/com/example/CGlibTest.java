package com.example;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.NoOp;
import org.junit.Assert;
import org.junit.Test;

public class CGlibTest {
    @Test
    public void noCallbackProxyTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(NoOp.INSTANCE);
        TargetObject o = (TargetObject) enhancer.create();
        o.request();
    }

    @Test
    public void generateDebugClassTest() {
        String location = CGlibTest.class.getResource("").getPath().replaceAll("%20", " ") + "debugging/";
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, location);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(NoOp.INSTANCE);
        TargetObject o = (TargetObject) enhancer.create();
        o.request();
    }

    @Test
    public void fixedValueTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback((FixedValue) () -> "Hello Tom!");

        TargetObject o = (TargetObject) enhancer.create();
        //didn't invoke proxy method, only return fixed value
        String res = o.request();
        Assert.assertEquals("Hello Tom!", res);
    }

    @Test
    public void interceptorTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new MyInterceptor());

        TargetObject o = (TargetObject) enhancer.create();
        o.request();
    }
}