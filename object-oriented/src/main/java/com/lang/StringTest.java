package com.lang;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class StringTest {
    @Test
    public void cons() {
        String s1 = new String();
        String s2 = new String("111");

    }
    @Test
    public void cons3() {
        char[] arr = {'h', 'a', 'l', 'o'};
        String s = new String(arr);

        Assert.assertEquals("halo", s);
    }

    @Test
    public void cons4() {
        char[] arr = {'h', 'a', 'l', 'o'};
        String s = new String(arr, 1, 2);

        Assert.assertEquals("al", s);
    }

    @Test
    public void cons5() {
        int[] arr = {96, 97, 98, 99, 100};
        String s = new String(arr, 1, 2);

        Assert.assertEquals("ab", s);
    }

    @Test
    public void cons6() {
        byte[] arr = {96, 97, 98, 99, 100};
        String s = new String(arr, 0, 1, 2);

        Assert.assertEquals("ab", s);
    }

    @Test
    public void cons7() throws UnsupportedEncodingException {
        byte[] arr = {97, 98, 99, 100};
        String s = new String(arr, 0, arr.length, "UTF-8");

        Assert.assertEquals("abcd", s);
    }

    @Test
    public void cons8() {
        byte[] arr = {97, 98, 99, 100};
        String s = new String(arr, 0, arr.length, Charset.defaultCharset());

        Assert.assertEquals("abcd", s);
    }

    @Test
    public void cons9() {
        byte[] arr = {97, 98, 99, 100};
        String s = new String(arr, 0, arr.length);

        Assert.assertEquals("abcd", s);
    }

    @Test
    public void cons10() {
        StringBuilder sb = new StringBuilder();
        sb.append("as");
        String s = new String(sb);

        Assert.assertEquals("as", s);
    }

    @Test
    public void charAt() {
        String s = new String("abc");
        char c = s.charAt(1);

        Assert.assertEquals('b', c);
    }

    @Test
    public void getChars() {
        String s = new String("abc");
        char[] arr = new char[6];
        s.getChars(1, 3, arr, 0);

        Assert.assertEquals('b', arr[0]);
        Assert.assertEquals('c', arr[1]);
    }

    @Test
    public void getBytes() {
        String s = new String("abc");
        byte[] arr = new byte[6];
        s.getBytes(1, 3, arr, 0);

        Assert.assertEquals(98, arr[0]);
        Assert.assertEquals(99, arr[1]);
    }
    @Test
    public void getBytes1() throws UnsupportedEncodingException {
        String s = new String("abc");
        byte[] bytes = s.getBytes("UTF-8");

        Assert.assertEquals(97, bytes[0]);
        Assert.assertEquals(98, bytes[1]);
        Assert.assertEquals(99, bytes[2]);
    }
    @Test
    public void equals() {
        boolean b = "as".equals("as");
        boolean c = "as1".equals("a1s");

        Assert.assertTrue(b);
        Assert.assertFalse(c);
    }
    @Test
    public void contentEquals() {
        StringBuilder sb = new StringBuilder("asd");
        boolean b = "asd".contentEquals(sb);

        Assert.assertTrue(b);
    }
    @Test
    public void equalsIgnoreCase() {
        String a = new String("asd");
        String b = new String("ASD");
        boolean c = a.equalsIgnoreCase(b);

        Assert.assertTrue(c);
    }
    @Test
    public void compareTo() {
        int i = "aada".compareTo("aadb");
        int j = "aad".compareTo("aadb");

        Assert.assertEquals(-1, i);
        Assert.assertEquals(-1, i);
    }
    @Test
    public void compareToIgnoreCase() {
        int b = "aab".compareToIgnoreCase("Aab");
        int d = "zab".compareToIgnoreCase("Aab");

        Assert.assertEquals(0, b);
        Assert.assertEquals(25, d);
    }
    @Test
    public void regionMatches() {
        boolean b = "asas".regionMatches(true, 0, "AsAs", 0, 4);
        Assert.assertTrue(b);
    }
    @Test
    public void startsWith() {
        boolean a = "asdasd".startsWith("asd", 0);
        Assert.assertTrue(a);
    }
    @Test
    public void endsWith() {
        boolean a = "1111rk".endsWith("rk");
        Assert.assertTrue(a);
    }
    @Test
    public void hashCode1() {
        int a = "ab".hashCode();
    }
    @Test
    public void indexOf() {
        int a = "aab".indexOf("b");

        Assert.assertEquals(2, a);
    }
    @Test
    public void lastIndexOf() {
        int a = "11111ba111a11".lastIndexOf("ba");

        Assert.assertEquals(5, a);
    }
    @Test
    public void substring() {
        String a = "123456".substring(2);

        Assert.assertEquals("3456", a);
    }
    @Test
    public void concat() {
        String a = "123456".concat("789");

        Assert.assertEquals("123456789", a);
    }

    @Test
    public void replace() {
        String a = "1234561".replace('1', 'a');

        Assert.assertEquals("a23456a", a);
    }
    @Test
    public void split() {
        String[] split = "1111#dddd#sdf".split("#");

        Assert.assertEquals("1111", split[0]);
        Assert.assertEquals("dddd", split[1]);
        Assert.assertEquals("sdf", split[2]);

    }
    @Test
    public void join() {
        String[] arr = {"12", "as", "f"};
        String a = String.join("#", arr);

        Assert.assertEquals("12#as#f", a);
    }
    @Test
    public void toLowerCase() {
        String s = "AbaB".toLowerCase();

        Assert.assertEquals("abab", s);
    }

    @Test
    public void toUpperCase() {
        String s = "AbaB".toUpperCase();

        Assert.assertEquals("ABAB", s);
    }

    @Test
    public void trim() {
        String s = "  Ab  aB  ".trim();

        Assert.assertEquals("Ab  aB", s);
    }

}
