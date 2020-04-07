package com.lyoyang.netty;

import com.lyoyang.netty.codec.MsgPackDecoder;
import com.lyoyang.netty.codec.MsgPackEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class EchoServer {
    private int port;

    public EchoServer(int port) {
        this.port = port;
    }


    public void start() throws InterruptedException {
        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
//                    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
//                                .addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
//                                .addLast(new FixedLengthFrameDecoder(20))
//                                .addLast(new StringDecoder())
                                .addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
                                .addLast("msgDecoder", new MsgPackDecoder())
                                .addLast("frameEncoder", new LengthFieldPrepender(2))
                                .addLast("msgEncoder", new MsgPackEncoder())
                                .addLast(echoServerHandler);
                    }
        });
        try {
            ChannelFuture sync = serverBootstrap.bind(port).sync();
            System.out.println(EchoServer.class.getName() + "started and listen on " + sync.channel().localAddress());
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EchoServer echoServer = new EchoServer(8080);
        echoServer.start();
    }
}
