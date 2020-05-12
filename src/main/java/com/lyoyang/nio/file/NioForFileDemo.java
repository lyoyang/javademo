package com.lyoyang.nio.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.netty.util.internal.StringUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.MessageFormat;
import java.util.List;

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



    @Test
    public void testRedData() throws IOException {
//        List<String> strings = Files.readLines(new File("D://pos_20190812.txt"), Charsets.UTF_8);
//        String tmpStr = strings.get(1);
//        String[] split = tmpStr.split(",");
        System.out.println(Math.abs(-0.234D));
    }
}
