package com.lyoyang.nio.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Iterator;

public class NoBlockingDemo {


    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getLocalHost(), 9088));
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put((LocalDate.now().toString() + "\n" + "hello, this is a test").getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        try {
            while (true) {
                if (socketChannel.read(buffer) > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String resp = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("client get response:" + resp);
                    socketChannel.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel sChannel = ServerSocketChannel.open();
        sChannel.configureBlocking(false);
        sChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(),9088));
        Selector selector = Selector.open();
        sChannel.register(selector, SelectionKey.OP_ACCEPT);
        //获取选择器上已经就绪的事件
        while (selector.select() > 0) {
            //获取当前选择器中所有注册的选择键（已就绪的监听事件）
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //获取准备就绪的事件
                SelectionKey sk = iterator.next();
                iterator.remove();
                //若接收就绪,则获取客户端连接
                if(sk.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) sk.channel();
                    SocketChannel socketChannel = ssc.accept();
                    //切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if(sk.isReadable()) {
                    SocketChannel accept = (SocketChannel) sk.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    if (accept.read(buffer) > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String request = new String(bytes, StandardCharsets.UTF_8);
                        System.out.println("the server receive data:" +request);
                        buffer.clear();
                        writeResponse(accept);
                    }
                }
            }
        }
    }

    private void writeResponse(SocketChannel socketChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("server receive data success!!!".getBytes());
        buffer.flip();
        socketChannel.write(buffer);
    }
}
