package com.lyoyang.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioDemo1 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D://a.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.put("jim,how are you?".getBytes());
        channel.write(allocate);
        allocate.clear();
        int read = channel.read(allocate);
        while (read != -1) {
            System.out.println("read:" + read);
            allocate.flip();
            while (allocate.hasRemaining()) {
                System.out.println((char) allocate.get());
            }
            allocate.clear();
            read = channel.read(allocate);
        }
        randomAccessFile.close();
    }
}
