package com.lyoyang.nio.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class UDPTestDemo {

    @Test
    public void client() throws IOException {
        DatagramChannel client = DatagramChannel.open();
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello,nice to meet you".getBytes());
        buffer.flip();
        client.send(buffer, new InetSocketAddress("127.0.0.1", 9098));
        buffer.clear();
        client.close();
    }

    @Test
    public void server() throws IOException {
        DatagramChannel server = DatagramChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(9098));
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_READ);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                if(sk.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    server.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, buffer.limit()));
                    buffer.clear();
                }
                iterator.remove();
            }
        }


    }

}
