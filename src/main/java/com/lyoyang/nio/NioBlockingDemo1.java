package com.lyoyang.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioBlockingDemo1 {
    
    
    
    @Test
    public void client() throws IOException {
        SocketChannel cChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel readChannel = FileChannel.open(Paths.get("/tmp/1.jpg"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (readChannel.read(buffer) != -1) {
            buffer.flip();
            cChannel.write(buffer);
            buffer.clear();
        }
        readChannel.close();
        readChannel.close();
    }
    
    
    @Test
    public void server() throws IOException {
        ServerSocketChannel sChannel = ServerSocketChannel.open();
        sChannel.bind(new InetSocketAddress(9898));
        SocketChannel accept = sChannel.accept();
        FileChannel outChannel = FileChannel.open(Paths.get("/tmp/2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (accept.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        outChannel.close();
        accept.close();
        sChannel.close();

    }
    
    
    
    
}
