package com.example.jdk.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class HeapByteBufferTest {

    @Test
    public void allocate() {
        ByteBuffer b = ByteBuffer.allocate(1024);
        int c = b.capacity();
        int l = b.limit();
        int p = b.position();
    }

    @Test
    public void wrap() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);

        byte[] array = b.array();
    }

    @Test
    public void slice() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        ByteBuffer s = b.slice();
    }

    @Test
    public void duplicate() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        ByteBuffer s = b.duplicate();
    }

    @Test
    public void asReadOnlyBuffer() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        ByteBuffer s = b.asReadOnlyBuffer();
    }

    @Test
    public void get() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        byte c1 = b.get();
        byte c2 = b.get();
        byte c3 = b.get();
    }

    @Test
    public void put() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        byte bt = 100;
        b.put(bt);
    }

    @Test
    public void get2() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        byte c1 = b.get(0);
        byte c2 = b.get(1);
        byte c3 = b.get(3);
    }

    @Test
    public void put2() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        byte bt = 100;
        b.put(3, bt);
    }

    @Test
    public void get3() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);

        byte[] dst = new byte[12];
        ByteBuffer c = b.get(dst, 0, 3);
    }

    @Test
    public void put3() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);

        byte[] bs2 = {100, 101, 102, 103};
        ByteBuffer b2 = ByteBuffer.wrap(bs2, 0, 4);

        b.put(b2);
    }

    @Test
    public void put4() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        byte[] bs2 = {100, 101, 102, 103};
        b.put(bs2, 1, 2);
    }

    @Test
    public void put5() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        byte[] bs2 = {100, 101, 102, 103};
        b.put(bs2);
    }

    @Test
    public void array() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        byte[] c = b.array();
    }

    @Test
    public void arrayOffset() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        int c = b.arrayOffset();
    }

    @Test
    public void compact() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        byte a = b.get();
        b.compact();
    }

    @Test
    public void isDirect() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        boolean c = b.isDirect();
    }

    @Test
    public void toString1() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        String s = b.toString();
    }

    @Test
    public void equals() {
        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);

        byte[] bs2 = {96, 97, 98, 99};
        ByteBuffer b2 = ByteBuffer.wrap(bs2, 0, 4);
//        b2.get();

        boolean eq = b.equals(b2);
    }

    @Test
    public void compareTo() {
        byte[] as = {96, 97, 98, 99};
        ByteBuffer a = ByteBuffer.wrap(as, 0, 4);

        byte[] bs = {96, 97, 98, 99};
        ByteBuffer b = ByteBuffer.wrap(bs, 0, 4);
        b.get();

        int c = a.compareTo(b);
    }

    @Test
    public void order() {
        byte[] as = {100, 96, 97, 98, 99};
        ByteBuffer a = ByteBuffer.wrap(as, 0, 4);

        ByteOrder o = a.order();
    }

    @Test
    public void getChar() {
        byte[] as = {100, 96, 97, 98, 99};
        ByteBuffer a = ByteBuffer.wrap(as, 0, 4);
        char c = a.getChar();
    }

    @Test
    public void putChar() {
        byte[] as = {0, 0, 0, 0, 0};
        ByteBuffer a = ByteBuffer.wrap(as);
        a.putChar('牛');

        char c = a.getChar(0);
    }

    @Test
    public void getChar2() {
        byte[] as = {0, 114, 91, 0, 0, 0};
        ByteBuffer a = ByteBuffer.wrap(as);
        char c = a.getChar(1);
    }

    @Test
    public void putChar2() {
        byte[] as = {0, 0, 0, 0, 0, 0};
        ByteBuffer a = ByteBuffer.wrap(as);
        a.putChar(1, '牛');
    }

    @Test
    public void asCharBuffer() {
        byte[] as = {114, 91, 0, 98};
        ByteBuffer a = ByteBuffer.wrap(as);
        CharBuffer cb = a.asCharBuffer();
    }


}
