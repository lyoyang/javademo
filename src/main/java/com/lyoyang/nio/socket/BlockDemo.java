package com.lyoyang.nio.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockDemo {


    @Test
    public void client() throws IOException, URISyntaxException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel readChannel = FileChannel.open(Paths.get("F:\\picture\\1.jpg"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (readChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }
        socketChannel.shutdownOutput();
        int len;
        while ((len = socketChannel.read(buffer)) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, len));
            buffer.clear();
        }
        readChannel.close();
        socketChannel.close();
    }


    @Test
    public void server() throws IOException, URISyntaxException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9898));
        SocketChannel socketChannel = serverSocketChannel.accept();
        FileChannel outChannel = FileChannel.open(Paths.get("F:\\picture\\2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        //返回数据给客户端
        buffer.put("服务端接收数据成功".getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        outChannel.close();
        socketChannel.close();
        serverSocketChannel.close();
    }


}
