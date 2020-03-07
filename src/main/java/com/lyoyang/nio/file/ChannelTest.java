package com.lyoyang.nio.file;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 获取通道方法
 * 1. getChannel()方法
 * 通过本地IO
 * FileInputStream/FileOutPutStream
 * RandomAccessFile
 *网路IO
 * Socket
 * ServerSocket
 * DataGramSocket
 * 2. JDK1.7 NIO2 对各个通道提供了静态方法open()
 * 3. JDK1.7工具类Files的newByteChannel()方法
 */
public class ChannelTest {


    @Test
    public void test1() {
        FileInputStream in = null;
        FileOutputStream out = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            in = new FileInputStream("/tmp/1.txt");
            out = new FileOutputStream("/tmp/2.txt");
            inChannel = in.getChannel();
            outChannel = out.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1) {
                //切换成读取数据的模式
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outChannel.close();
            inChannel.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用直接缓存区完成文件的复制
     */
    @Test
    public void test2() throws Exception {
        FileChannel inChannel = FileChannel.open(Paths.get("/tmp/1.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/tmp/3.txt"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        byte[] bytes = new byte[inMappedBuffer.limit()];
        inMappedBuffer.get(bytes);
        outMappedBuffer.put(bytes);
        inChannel.close();
        outChannel.close();
    }

    /**
     * 通道之间的数据传输
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        long beginTime = System.currentTimeMillis();
        System.out.println("开始时间：" + beginTime);
        FileChannel inChannel = FileChannel.open(Paths.get("/tmp/1.box"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/tmp/2.box"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        inChannel.transferTo(0, inChannel.size(), outChannel);
//        outChannel.transferFrom(inChannel, 0, inChannel.size());
        System.out.println("消耗时间：" + (System.currentTimeMillis() - beginTime));
    }
}

