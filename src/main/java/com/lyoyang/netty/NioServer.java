package com.lyoyang.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: yangbing
 * @Date: 2019/3/26 20:28
 * @Description:
 */
public class NioServer {

    public static void server(int port) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            serverSocket.bind(inetSocketAddress);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
            for(;;) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if(next.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel)next.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, msg.duplicate());
                        System.out.println("Accepted Connection from:" + client);
                    }
                    if(next.isWritable()) {
                        SocketChannel client = (SocketChannel) next.channel();
                        ByteBuffer attachment = (ByteBuffer)next.attachment();
                        while (attachment.hasRemaining()) {
                            if(client.write(attachment) == 0) {
                                break;
                            }
                        }
                        client.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        server(8089);
    }

}
