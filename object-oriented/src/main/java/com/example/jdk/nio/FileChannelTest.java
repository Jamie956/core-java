package com.example.jdk.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelTest {

    @Test
    public void open() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.READ);
    }

    @Test
    public void read() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.READ);
        ByteBuffer bb = ByteBuffer.allocate(12);
        int read = c.read(bb);
    }

    @Test
    public void read2() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.READ);
        ByteBuffer bb1 = ByteBuffer.allocate(4);
        ByteBuffer bb2 = ByteBuffer.allocate(4);
        ByteBuffer bb3 = ByteBuffer.allocate(4);
        ByteBuffer bb4 = ByteBuffer.allocate(4);
        ByteBuffer[] bb = {bb1, bb2, bb3, bb4};
        c.read(bb, 1, 2);
    }

    @Test
    public void read3() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.READ);
        ByteBuffer bb1 = ByteBuffer.allocate(4);
        ByteBuffer bb2 = ByteBuffer.allocate(4);
        ByteBuffer bb3 = ByteBuffer.allocate(4);
        ByteBuffer bb4 = ByteBuffer.allocate(4);
        ByteBuffer[] bb = {bb1, bb2, bb3, bb4};
        c.read(bb);
    }

    @Test
    public void write() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.put(new byte[]{97, 98, 99, 100});
        bb.clear();

        c.write(bb);
    }

    @Test
    public void write2() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        ByteBuffer bb1 = ByteBuffer.allocate(4);
        ByteBuffer bb2 = ByteBuffer.allocate(4);
        bb1.put(new byte[]{97, 98, 99, 100});
        bb2.put(new byte[]{97, 98, 99, 100});
        bb1.clear();
        bb2.clear();
        ByteBuffer[] bb = {bb1, bb2};
        c.write(bb, 0, 2);
    }

    @Test
    public void position() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        c.read(ByteBuffer.allocate(1));
        long p = c.position();
    }

    @Test
    public void position2() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        c.position(2);
        long p = c.position();
    }

    @Test
    public void size() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        long s = c.size();
    }

    @Test
    public void truncate() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        c.truncate(3);
    }

    @Test
    public void force() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        c.force(false);
    }

    @Test
    public void transferTo() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        FileChannel c2 = FileChannel.open(Paths.get("resources/source_cp"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        c.transferTo(0, 3, c2);
    }

    @Test
    public void transferFrom() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        FileChannel c2 = FileChannel.open(Paths.get("resources/source_cp"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        c2.transferFrom(c, 0, 3);
    }

    @Test
    public void read4() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        ByteBuffer bb = ByteBuffer.allocate(12);
        c.read(bb, 1);
    }

    @Test
    public void write3() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        ByteBuffer bb = ByteBuffer.allocate(3);
        bb.put(new byte[]{99, 100, 101});
        bb.clear();
        c.write(bb, 0);
    }

    @Test
    public void map() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        MappedByteBuffer map = c.map(FileChannel.MapMode.READ_ONLY, 0, 3);
    }

    @Test
    public void lock() throws IOException {
        FileChannel c = FileChannel.open(Paths.get("resources/source"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        FileLock lock = c.lock(0, 3, true);
    }


}
