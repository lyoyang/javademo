package com.lyoyang.nio;

import io.netty.util.internal.StringUtil;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: yangbing
 * @Date: 2019/5/20 9:53
 * @Description:
 */
public class NioForFileDemo {



    @Test
    public void testRead() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D://tmp.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        int read = channel.read(buffer);
        while (read != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println(buffer.getChar());
            }
            buffer.clear();
            read = channel.read(buffer);
        }
        randomAccessFile.close();
    }






}
