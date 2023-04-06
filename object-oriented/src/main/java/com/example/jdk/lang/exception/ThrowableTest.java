package com.example.jdk.lang.exception;

import org.junit.Test;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ThrowableTest {
    @Test
    public void cons() throws Throwable {
        Throwable a = new Throwable();
        throw a;
    }

    @Test
    public void cons2() throws Throwable {
        Throwable a = new Throwable("eeeee");
        throw a;
    }

    @Test
    public void cons3() throws Throwable {
        Throwable b = new Throwable("exp b");
        Throwable a = new Throwable("exp a", b);
        throw a;
    }

    @Test
    public void cons4() throws Throwable {
        Throwable b = new Throwable("exp b");
        Throwable a = new Throwable(b);
        throw a;
    }

    @Test
    public void getMessage() throws Throwable {
        Throwable a = new Throwable("ops");
        String m = a.getMessage();
        throw a;
    }

    @Test
    public void getLocalizedMessage() throws Throwable {
        Throwable a = new Throwable("ops");
        String m = a.getLocalizedMessage();
        throw a;
    }

    @Test
    public void getCause() throws Throwable {
        Throwable b = new Throwable("exp b");
        Throwable a = new Throwable("exp a", b);
        Throwable c = a.getCause();
        throw a;
    }

    @Test
    public void initCause() throws Throwable {
        Throwable b = new Throwable("exp b");
        Throwable a = new Throwable("exp a");
        Throwable r = a.initCause(b);
        throw a;
    }

    @Test
    public void toString1() throws Throwable {
        Throwable a = new Throwable("exp a");
        String s = a.toString();
        throw a;
    }

    @Test
    public void printStackTrace() {
        Throwable a = new Throwable("exp a", new Throwable("cause b"));
        a.printStackTrace();
    }

    @Test
    public void printStackTrace2() {
        Throwable a = new Throwable("exp a", new Throwable("cause b"));
        PrintStream s = new PrintStream(System.out);
        a.printStackTrace(s);
    }

    @Test
    public void printStackTrace3() {
        Throwable a = new Throwable("exp a", new Throwable("cause b"));
        PrintWriter p = new PrintWriter(System.out);
        a.printStackTrace(p);
        p.flush();
    }

    @Test
    public void fillInStackTrace() {
        Throwable a = new Throwable("exp a", new Throwable("cause b"));
        Throwable b = a.fillInStackTrace();
        b.printStackTrace();
    }

    @Test
    public void getStackTrace() {
        Throwable a = new Throwable("exp a", new Throwable("cause b"));
        StackTraceElement[] s = a.getStackTrace();
    }

    @Test
    public void setStackTrace() {
        Throwable a = new Throwable("exp a");
        StackTraceElement s1 = new StackTraceElement("declaringClass_test", "methodName_test", "fileName_test", 3);
        StackTraceElement s2 = new StackTraceElement("declaringClass_test2", "methodName_test2", "fileName_test2", 23);
        StackTraceElement[] ss = {s1, s2};
        a.setStackTrace(ss);
        a.printStackTrace();
    }

    @Test
    public void addSuppressed() {
        Throwable a = new Throwable("exp a");
        a.addSuppressed(new Throwable("exp Suppressed"));
        a.printStackTrace();
    }

    @Test
    public void getSuppressed() {
        Throwable a = new Throwable("exp a");
        a.addSuppressed(new Throwable("exp Suppressed"));
        Throwable[] s = a.getSuppressed();
    }

}
