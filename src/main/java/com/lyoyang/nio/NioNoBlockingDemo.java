package com.lyoyang.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

public class NioNoBlockingDemo {


    @Test
    public void client() throws IOException {
        SocketChannel cChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9088));
        cChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            buffer.put((LocalDate.now().toString() + "\n" + scanner.next()).getBytes());
            buffer.flip();
            cChannel.write(buffer);
            buffer.clear();
        }
        cChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel sChannel = ServerSocketChannel.open();
        sChannel.configureBlocking(false);
        sChannel.bind(new InetSocketAddress(9088));
        Selector selector = Selector.open();
        sChannel.register(selector, SelectionKey.OP_ACCEPT);
        //获取选择器上已经就绪的事件
        while (selector.select() > 0) {
            //获取当前选择器中所有注册的选择键（已就绪的监听事件）
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //获取准备就绪的事件
                SelectionKey sk = iterator.next();
                //若接收就绪,则获取客户端连接
                if(sk.isAcceptable()) {
                    SocketChannel accept = sChannel.accept();
                    //切换为非阻塞模式
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                } else if(sk.isReadable()) {
                    SocketChannel accept = (SocketChannel) sk.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = accept.read(buffer)) != -1) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
                iterator.remove();
            }
        }
    }


}
