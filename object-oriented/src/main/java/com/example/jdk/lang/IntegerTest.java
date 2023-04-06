package com.example.jdk.lang;

import org.junit.Test;

public class IntegerTest {
    @Test
    public void var() {
        int a = Integer.MIN_VALUE;
        int b = Integer.MAX_VALUE;
        Class<Integer> c = Integer.TYPE;
        String d = Integer.toString(12, 2);
        String e = Integer.toString(-12, 2);
    }

    @Test
    public void toUnsignedString() {
        String s = Integer.toUnsignedString(12, 2);
        String a = Integer.toUnsignedString(-12, 10);
    }

    @Test
    public void toHexString() {
        String s = Integer.toHexString(15);
    }

    @Test
    public void toOctalString() {
        String s = Integer.toOctalString(15);
    }

    @Test
    public void toBinaryString() {
        String s = Integer.toBinaryString(15);
    }

    @Test
    public void toString1() {
        String s = Integer.toString(15);
        String a = Integer.toString(-15);
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
