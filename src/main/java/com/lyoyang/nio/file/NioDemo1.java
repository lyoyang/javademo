package com.lyoyang.nio.file;


import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioDemo1 {
    public static void main(String[] args) throws IOException {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("D://a.txt", "rw");
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            allocate.put("jim,how are you?".getBytes());
            channel.write(allocate);
            allocate.clear();
//            int read = channel.read(allocate);
//            while (read != -1) {
//                System.out.println("read:" + read);
//                allocate.flip();
//                while (allocate.hasRemaining()) {
//                    System.out.println((char) allocate.get());
//                }
//                allocate.clear();
//                read = channel.read(allocate);
//            }
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void readData() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("D://a.txt"));
            byte[] buf = new byte[1024];
            int read = in.read(buf);
            while (read != -1) {
                System.out.println(new String(buf));
                read = in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
