package com.example.jdk.lang;

import org.junit.Assert;
import org.junit.Test;

public class IntegerTest {
    @Test
    public void intMaxMin() {
        // Integer size 4 byte(4*8 bit), positive number value range 0 ~ 2^31, negative number value range 2^31 ~ 2^32
        // 1000 0000 0000 0000 0000 0000 0000 0000
        Assert.assertEquals(0x80000000, Integer.MIN_VALUE);
        // 0111 1111 1111 1111 1111 1111 1111 1111
        Assert.assertEquals(0x7fffffff, Integer.MAX_VALUE);
    }

    @Test
    public void toStringWithRadix() {
        // Integer to string number as specific radix, return value may have minus sign
        Assert.assertEquals("10", Integer.toString(16, 16));
        Assert.assertEquals("-10", Integer.toString(-16, 16));
        Assert.assertEquals("1000", Integer.toString(8, 2));
        Assert.assertEquals("-1000", Integer.toString(-8, 2));
        Assert.assertEquals("10", Integer.toString(10, 9999999));
    }

    @Test
    public void toUnsignedString() {
        // Integer to string number in the specific radix, return unsign value
        Assert.assertEquals("1100", Integer.toUnsignedString(12, 2));
        Assert.assertEquals("100", Integer.toUnsignedString(4, 2));
        Assert.assertEquals("10", Integer.toUnsignedString(2, 2));
        //1111 1111 1111 1111 1111 1111 1111 1111
        Assert.assertEquals("11111111111111111111111111111111", Integer.toUnsignedString(-1, 2));
        Assert.assertEquals("11111111111111111111111111111110", Integer.toUnsignedString(-2, 2));

        Assert.assertEquals("c", Integer.toUnsignedString(12, 16));
        Assert.assertEquals("ffffffff", Integer.toUnsignedString(-1, 16));
        Assert.assertEquals("fffffffe", Integer.toUnsignedString(-2, 16));
    }

    @Test
    public void toHexString() {
        // Integer to unsigned number string in the 16 radix
        Assert.assertEquals("f", Integer.toHexString(15));
        Assert.assertEquals("fffffff1", Integer.toHexString(-15));
        Assert.assertEquals(Integer.toUnsignedString(-15, 16), Integer.toHexString(-15));
    }

    @Test
    public void toOctalString() {
        // Integer to unsigned number string in the 8 radix
        Assert.assertEquals("1", Integer.toOctalString(1));
        Assert.assertEquals("7", Integer.toOctalString(7));
        Assert.assertEquals("10", Integer.toOctalString(8));
        Assert.assertEquals("11", Integer.toOctalString(9));

        // 11 111 111 111 111 111 111 111 111 111 111 (2 radix) == 37777777777 (8 radix)
        Assert.assertEquals("37777777777", Integer.toOctalString(-1));
        Assert.assertEquals("37777777771", Integer.toOctalString(-7));
        Assert.assertEquals("37777777770", Integer.toOctalString(-8));
        Assert.assertEquals("37777777767", Integer.toOctalString(-9));
    }

    @Test
    public void toBinaryString() {
        String s = Integer.toBinaryString(15);
    }



    @Test
    public void toUnsignedString1() {
        String s = Integer.toUnsignedString(15);
        String a = Integer.toUnsignedString(-15);
    }

    @Test
    public void parseInt() {
        int i = Integer.parseInt("123", 10);
        int j = Integer.parseInt("-123", 10);
        int a = Integer.parseInt("1111", 2);
    }

    @Test
    public void parseUnsignedInt() {
        int i = Integer.parseUnsignedInt("123", 10);
        int a = Integer.parseUnsignedInt("1111", 2);
    }

    @Test
    public void valueOf() {
        Integer i = Integer.valueOf("1111", 2);
    }

    @Test
    public void valueOf1() {
        Integer i = Integer.valueOf("1111");
    }

    @Test
    public void valueOf2() {
        Integer i = Integer.valueOf(122);
        Integer j = Integer.valueOf(128);
    }

    @Test
    public void Integer() {
        Integer i = new Integer(5);
    }

    @Test
    public void Integer1() {
        Integer i = new Integer("67");
    }

    @Test
    public void hashCode1() {
        Integer i = new Integer("67");
        int c = i.hashCode();
    }

    @Test
    public void getInteger() {
        Integer i = Integer.getInteger("sun.arch.data.model");
    }

    @Test
    public void compareTo() {
        Integer a = new Integer(67);
        Integer b = new Integer(1);
        int r = a.compareTo(b);
    }

    @Test
    public void compareUnsigned() {
        int a = Integer.compareUnsigned(-2, 1);
        int b = Integer.compareUnsigned(2, 1);
    }

    @Test
    public void toUnsignedLong() {
        long a = Integer.toUnsignedLong(-2);
    }

    @Test
    public void bitTwiddling() {
        int a = Integer.SIZE;
        int b = Integer.BYTES;
        int c = Integer.highestOneBit(12);
        int d = Integer.lowestOneBit(12);
        int e = Integer.numberOfLeadingZeros(12);
        int f = Integer.rotateLeft(-2, 2);

    }


}
